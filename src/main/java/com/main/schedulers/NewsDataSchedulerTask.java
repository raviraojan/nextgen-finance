package com.main.schedulers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.main.article.services.NewsDataSyncService;

@Slf4j
@Component
@RequiredArgsConstructor
public class NewsDataSchedulerTask {

	private final NewsDataSyncService newsDataSyncService;

//	@Scheduled(cron = "0 * * * * *")
//	@SchedulerLock(name = "channel_blog_news", lockAtMostFor = "29m", lockAtLeastFor = "29m")
	public void sync() {
		
		System.out.println("IS RUNNING FOR SCHEDULER");
		newsDataSyncService.asyncNewsData();

	}
}
