package com.main.article.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.article.models.ChannelNews;
import com.main.article.repositories.ChannelNewsRepository;

import com.main.constants.AppConstants;

/*import com.apptasticsoftware.rssreader.RssReader;*/

import com.rometools.modules.mediarss.MediaEntryModule;
import com.rometools.modules.mediarss.types.MediaContent;

import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestHelloController {

	private final ChannelNewsRepository channelNewsRepo;

	@GetMapping("/test")
	public String index() {

		try {
		//	rss();
			rssRome();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Greetings from Spring Boot!";

	}
	
	/*
	 * public void rss() throws IOException { RssReader rssReader = new RssReader();
	 * List<com.apptasticsoftware.rssreader.Item> items =
	 * rssReader.read("https://feeds.bloomberg.com/markets/news.rss") .toList();
	 * System.out.println(items+""); }
	 */
	  
	 

	public void rssRome() throws IOException {

		AppConstants.CHANNELS_RSS_URLS.forEach(channelRSSURL -> {

			try {
				// String url = "https://feeds.bloomberg.com/markets/news.rss";

				try (XmlReader reader = new XmlReader(new URL(channelRSSURL))) {

					SyndFeed feed = new SyndFeedInput().build(reader);
					System.out.println(" feed title " + feed.getTitle());
					System.out.println("***********************************");

					String source = feed.getTitle();
					
					if(source!=null && source.equalsIgnoreCase("Business News"))
					{
						source ="CNBC Business News";
					}

					List<ChannelNews> channelNewItems = new ArrayList<ChannelNews>();

					for (SyndEntry entry : feed.getEntries()) {

						// iterate for each item::

						ChannelNews channelNewItem = new ChannelNews();

						channelNewItem.setSource(source);

						channelNewItem.setTitle(entry.getTitle());

						channelNewItem.setNewsUrl(entry.getLink());

						if (entry != null && entry.getDescription() != null)
							channelNewItem.setDescription(entry.getDescription().getValue());
						// channelNewItem.setNewsUrl(channelRSSURL)
						
						Date publishedDate = entry.getPublishedDate();
						
						//Instant timestamp = new Date().toInstant(); 
						
						Instant timestamp1 = publishedDate.toInstant();
						
						//convert legacy date to new Datetime, they have added instant in date to convert into new java.time package classes.
						System.out.println("instant   "+ timestamp1);
						
						if(entry.getPublishedDate()!=null)
						{
							LocalDateTime gmtlocaldate = LocalDateTime.ofInstant(timestamp1, ZoneId.of("GMT"));
							channelNewItem.setPublishDate(gmtlocaldate);
							
							Instant timestamp = new Date().toInstant();
							
							LocalDateTime currentGMTDT = LocalDateTime.ofInstant(timestamp, ZoneId.of("GMT"));
							
							Duration dt = Duration.between(gmtlocaldate, currentGMTDT);
							System.out.println(dt.toDaysPart()+" Days "+dt.toHoursPart()+ " HOURS AGO"+dt.toMinutesPart()+" MINUTES AGO");
						}
						
						LocalDateTime localdatetime = LocalDateTime.ofInstant(timestamp1, ZoneId.systemDefault());
						
						LocalDateTime gmtlocaldate = LocalDateTime.ofInstant(timestamp1, ZoneId.of("GMT"));
						System.out.println(" gmtlocaldate "+gmtlocaldate);
						
						
						System.out.println(" localdatetime date date :: "+localdatetime);
						
						System.out.println(" publishedDate "+ publishedDate);

						// ZonedDateTime zdt1 = ZonedDateTime.parse((CharSequence) publishedDate,
						//DateTimeFormatter.RFC_1123_DATE_TIME);

						System.out.println("  PUBLISH DATE :: " + publishedDate);

						System.out.println(" TITLE entry.getTitle " + entry.getTitle());
						System.out.println(" DESCREPTION entry.getDescription() " + entry.getDescription().getValue());
						System.out.println(" DATE entry.getPublishedDate() " + publishedDate);
						System.out.println(" URL entry.getlink() " + entry.getLink());

						for (com.rometools.rome.feed.module.Module module : entry.getModules()) {
							if (module instanceof MediaEntryModule) {
								MediaEntryModule media = (MediaEntryModule) module;

								MediaContent[] mediaContents = media.getMediaContents();
								if (mediaContents != null) {
									// System.out.println("media content "+mediaContents);

									for (int i = 0; i < mediaContents.length; i++) {
										if (mediaContents[i] != null && mediaContents[i].getReference() != null)
											channelNewItem.setImagePath(mediaContents[i].getReference().toString());
										// System.out.println(" getTitle "+mediaContents[i].getMetadata().getTitle());
										System.out.println(" getReference " + mediaContents[i].getReference());

									}
								}

							}
						}

						// System.out.println(entry);
						System.out.println("***********************************");

						channelNewItems.add(channelNewItem);

					}
					System.out.println("Done");

					channelNewsRepo.saveAll(channelNewItems);
				}

				// save it DB

			} catch (Exception e) {
				e.printStackTrace();
			}

		});

	}
}
