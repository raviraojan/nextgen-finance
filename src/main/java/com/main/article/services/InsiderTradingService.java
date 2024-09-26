package com.main.article.services;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.Instant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.main.article.models.InsiderTradingModel;
import com.main.article.repositories.InsiderTradingRepository;
import com.main.util.sax.InsiderData;
import com.main.util.sax.NONDERIVATIVETRANSACTION;
import com.main.util.sax.OwnershipDocument;
import com.main.util.sax.OwnershipDocumentParser;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class InsiderTradingService {

	private final InsiderTradingRepository insiderTradingRepository;

	public void mainCall() {

		try {
			HashMap<String, InsiderData> rssRome = rssRome();

			if (!rssRome.isEmpty()) {

				List<InsiderData> list = new ArrayList<InsiderData>(rssRome.values());

				if (list != null && list.size() > 0) {
					list.stream().forEach(insideritem -> {
						// call this api to get xml,
						jsoupAPIcall(insideritem);

						getDataAndNextUpdInDB(insideritem);

					});
				}

			}

			System.out.println("rssRome  " + rssRome);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getDataAndNextUpdInDB(InsiderData insideritem) {

		if (insideritem != null && insideritem.getXmlUrls() != null) {
			for (int i = 0; i < insideritem.getXmlUrls().size(); i++) {

				// got ownership doc, next put in db
				OwnershipDocument ownershipDocument = saxParsing(insideritem.getXmlUrls().get(i));

				saveInDB(ownershipDocument, insideritem);

			}
		}

	}

	// This API will create folder path(getXMLfolder) and then gets only xml files.
	private void jsoupAPIcall(InsiderData insiderData) {

		// String url =
		// "https://www.sec.gov/Archives/edgar/data/1045810/000104581024000284/0001045810-24-000284-index.htm";

		List<String> xmlUrls = new ArrayList<String>();
		Document doc;

		try {

			String xmlPath = getXMLfolder(insiderData);

			if (xmlPath != null) {

				doc = Jsoup.connect(xmlPath).userAgent("ravirao1224@gmail.com").get();
				Elements links = doc.select("a[href]");

				// print("\nLinks: (%d)", links.size());
				for (Element link : links) {

					if (link != null && link.attr("abs:href").contains("xml")) {

						xmlUrls.add(link.attr("abs:href"));

					}
				}

				insiderData.setXmlUrls(xmlUrls);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getXMLfolder(InsiderData insiderData) {

		String outStr = null;
		if (insiderData != null && insiderData.getUrl() != null && insiderData.getAccessionNo() != null) {

			outStr = insiderData.getUrl().substring(0, insiderData.getUrl().indexOf(insiderData.getAccessionNo()));

		}
		return outStr;
	}

	public HashMap<String, InsiderData> rssRome() {
		HashMap<String, InsiderData> rssMap = new HashMap<String, InsiderData>();

		try {
			String url = "https://www.sec.gov/cgi-bin/browse-edgar?action=getcurrent&CIK=&type=4&company=&dateb=&owner=only&start=0&count=100&output=atom";

			URL edgarUrl = new URL(url);

			Map<String, String> requestHeaders = new HashMap<String, String>();

			requestHeaders.put("user-agent", "ravirao1224@gmail.com");

			try (XmlReader reader = new XmlReader(edgarUrl, requestHeaders)) {

				SyndFeed feed = new SyndFeedInput().build(reader);

				for (SyndEntry entry : feed.getEntries()) {

					if (entry != null) {

						String uri = entry.getUri();

						int start = uri.indexOf("accession-number=");

						String acessionNumber = uri.substring(start + 17);

						// if acessionNumber already exits skip loop, we don't need duplicates
						// Two records will be submitted one by issuer,and other reporter, by company by
						// person sold or purchased.
						if (rssMap.containsKey(acessionNumber)) {
							continue;

						}

						InsiderData insiderData = new InsiderData();

						insiderData.setAccessionNo(acessionNumber);

						insiderData.setUrl(entry.getLink());
						if (entry.getUpdatedDate() != null)
							insiderData.setPublishedDate(entry.getUpdatedDate().toInstant());
						rssMap.put(acessionNumber, insiderData);

					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rssMap;

	}

	public OwnershipDocument saxParsing(String xmlURL) {

		OwnershipDocument ownershipDocument = null;
		try {

			// String xmlURL =
			// "https://www.sec.gov/Archives/edgar/data/1045810/000122520824008806/doc4.xml";

			// System.out.println("xmlxml xml which is being processed is:::::::::"+xmlURL);

			// Create the parser instance
			OwnershipDocumentParser ownershipDocumentParser = new OwnershipDocumentParser();

			// Parse the file
			ownershipDocument = ownershipDocumentParser.parseXml(xmlURL);

			Gson gson = new Gson();

			String json = gson.toJson(ownershipDocument);

			System.out.println("\n\n\nJSON STRING ISSSSSSSSSSSSSSS: " + json);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ownershipDocument;

	}

	public void saveInDB(OwnershipDocument ownershipDocument, InsiderData insideritem) {

		if (ownershipDocument != null && ownershipDocument.getNonDerivativeTable() != null && ownershipDocument
				.getNonDerivativeTable().getNonDerivativeTransactionOrNonDerivativeHolding() != null) {

			// for each line item in html we will create new item in DB,
			// for each non derivative item we will create one record so that we can have
			// everything in single table, otherwise, we have to create two tables.

			List<Object> nonerivativeTransactionOrDerivativeHolding = ownershipDocument.getNonDerivativeTable()
					.getNonDerivativeTransactionOrNonDerivativeHolding();

			List<InsiderTradingModel> InsiderTradingModelLst = new ArrayList<InsiderTradingModel>();

			for (Object nonDertrans : nonerivativeTransactionOrDerivativeHolding) {
				if (nonDertrans != null) {

					NONDERIVATIVETRANSACTION nonDertransDetails = (NONDERIVATIVETRANSACTION) nonDertrans;

					InsiderTradingModel indsiderModel = new InsiderTradingModel();

					indsiderModel.setIssuerName(ownershipDocument.getIssuer().getIssuerName());
					indsiderModel.setIssuerCik(ownershipDocument.getIssuer().getIssuerCik());
					indsiderModel.setIssuerTradingSymbol(ownershipDocument.getIssuer().getIssuerTradingSymbol());

					indsiderModel.setRptOwnerCik(
							ownershipDocument.getReportingOwner().get(0).getReportingOwnerId().getRptOwnerCik());
					indsiderModel.setRptOwnerName(
							ownershipDocument.getReportingOwner().get(0).getReportingOwnerId().getRptOwnerName());
					indsiderModel.setOfficerTitle(ownershipDocument.getReportingOwner().get(0)
							.getReportingOwnerRelationship().getOfficerTitle());

					indsiderModel.setIsDirector(ownershipDocument.getReportingOwner().get(0)
							.getReportingOwnerRelationship().isIsDirector());
					indsiderModel.setIsOfficer(
							ownershipDocument.getReportingOwner().get(0).getReportingOwnerRelationship().isIsOfficer());
					indsiderModel.setIsOther(
							ownershipDocument.getReportingOwner().get(0).getReportingOwnerRelationship().isIsOther());
					indsiderModel.setIsTenPercentOwner(ownershipDocument.getReportingOwner().get(0)
							.getReportingOwnerRelationship().isIsTenPercentOwner());

					indsiderModel.setSecurityTitle(nonDertransDetails.getSecurityTitle().getValue());
					indsiderModel.setTransDate(nonDertransDetails.getTransactionDate().getValue());
					indsiderModel.setTransCode(nonDertransDetails.getTransactionCoding().getTransactionCode());
					indsiderModel.setTransShares(
							nonDertransDetails.getTransactionAmounts().getTransactionShares().getValue());
					indsiderModel.setTransPricePerShare(
							nonDertransDetails.getTransactionAmounts().getTransactionPricePerShare().getValue());
					indsiderModel.setSharesOwnedAfterTrans(nonDertransDetails.getPostTransactionAmounts()
							.getSharesOwnedFollowingTransaction().getValue());

					indsiderModel.setIsTenPercentOwner(ownershipDocument.getReportingOwner().get(0)
							.getReportingOwnerRelationship().isIsTenPercentOwner());
					indsiderModel.setIsTenPercentOwner(ownershipDocument.getReportingOwner().get(0)
							.getReportingOwnerRelationship().isIsTenPercentOwner());
					indsiderModel.setIsTenPercentOwner(ownershipDocument.getReportingOwner().get(0)
							.getReportingOwnerRelationship().isIsTenPercentOwner());
					indsiderModel.setIsTenPercentOwner(ownershipDocument.getReportingOwner().get(0)
							.getReportingOwnerRelationship().isIsTenPercentOwner());
					indsiderModel.setIsTenPercentOwner(ownershipDocument.getReportingOwner().get(0)
							.getReportingOwnerRelationship().isIsTenPercentOwner());

					// this data is from first call RSS feed
					indsiderModel.setSecUrl(insideritem.getUrl());
					indsiderModel.setAccessionNumber(insideritem.getAccessionNo());
					indsiderModel.setSecUrl(insideritem.getUrl());
					InsiderTradingModelLst.add(indsiderModel);

				}
			}

			if (InsiderTradingModelLst != null && InsiderTradingModelLst.size() > 0)
				insiderTradingRepository.saveAll(InsiderTradingModelLst);

			// indsiderModel.setPublishDate( LocalDateTime.ofInstant(timestamp1,
			// ZoneId.of("GMT")));
		}

	}

}
