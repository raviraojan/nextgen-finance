package com.main.article.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.rometools.modules.mediarss.MediaEntryModule;
import com.rometools.modules.mediarss.types.MediaContent;

import java.net.URL;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import lombok.RequiredArgsConstructor;

import com.main.article.services.FinArticleService;
import com.main.article.services.InsiderTradingService;
import com.main.article.services.NewsService;
import com.main.util.sax.InsiderData;
import com.main.util.sax.OwnershipDocumentParser;
import com.main.util.sax.OwnershipDocument;

@RequiredArgsConstructor
@RestController
public class TestInsiderInfoController {
	
	private final InsiderTradingService insiderTradingService;

	@GetMapping("/maincalltoser")
	public void mainCallToSer() {

		try {
			
			insiderTradingService.mainCall();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@GetMapping("/insider")
	public void mainCall() {

		try {
			HashMap<String, InsiderData> rssRome = rssRome();

			if (!rssRome.isEmpty()) {

				List<InsiderData> list = new ArrayList<InsiderData>(rssRome.values());

				if (list != null && list.size() > 0) {
					list.stream().forEach(insideritem -> {
						jsoupAPIcall(insideritem);
					});
				}

			}

			System.out.println("rssRome  " + rssRome);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
						System.out.println(" vow vow vow vow ::::::::::::::::::::::::::::::::::::::::::: "
								+ link.attr("abs:href"));
					}
				}

				insiderData.setXmlUrls(xmlUrls);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String getXMLfolder(InsiderData insiderData) {

		String outStr = null;
		if (insiderData != null && insiderData.getUrl() != null && insiderData.getAccessionNo() != null) {

			outStr = insiderData.getUrl().substring(0, insiderData.getUrl().indexOf(insiderData.getAccessionNo()));

		}
		return outStr;
	}

	public static HashMap<String, InsiderData> rssRome() {
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

	@GetMapping("/saxparsing")
	public String saxParsing() {

		try {

			String xmlURL = "https://www.sec.gov/Archives/edgar/data/1045810/000122520824008806/doc4.xml";

			// Create the parser instance
			OwnershipDocumentParser ownershipDocumentParser = new OwnershipDocumentParser();

			// Parse the file
			OwnershipDocument ownershipDocument = ownershipDocumentParser.parseXml(xmlURL);

			Gson gson = new Gson();

			String json = gson.toJson(ownershipDocument);

			System.out.println("JSON STRING ISSSSSSSSSSSSSSS: " + json);
			System.out.println("\n\n\n\n\n");
			System.out.println(" ownershipDocument " + ownershipDocument);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Greetings SAX PARSING from Spring Boot!";

	}
	
	@GetMapping("/saveindb")
	public String saveInDB()
	{
		
	//	insiderTradingService.saveInDB();
		
		return "saved in dB";
		
	}

}
