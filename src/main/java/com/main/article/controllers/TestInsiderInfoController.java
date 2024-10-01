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
import com.main.article.services.InsiderTradingDataSyncService;
import com.main.article.services.NewsService;
import com.main.util.sax.InsiderData;
import com.main.util.sax.OwnershipDocumentParser;
import com.main.util.sax.OwnershipDocument;

@RequiredArgsConstructor
@RestController
public class TestInsiderInfoController {
	
	private final InsiderTradingDataSyncService insiderTradingService;

	@GetMapping("/maincalltoser")
	public void mainCallToSer() {

		try {
			
			insiderTradingService.mainCall();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("/codebugTestOnly")
	public void codeDebugTestOnly() {

		try {
			InsiderData insideritem = new InsiderData();
			
			String xmlUrl ="https://www.sec.gov/Archives/edgar/data/1321655/000132165524000179/wk-form4_1727309102.xml";
			
			insiderTradingService.saveInDB(insiderTradingService.saxParsing(xmlUrl), insideritem);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
