package com.main.article.services;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.main.constants.AppConstants;


@Service
@Slf4j
@RequiredArgsConstructor
@EnableAsync
public class NewsDataSyncService {





    @Async
    public void asyncNewsData() {
    	syncNewsData();
    }




    @Transactional
    public void syncNewsData() {
    	
    	callAPIs();
    	processData();
    	saveData();
    	
    	
    }



	private void saveData() {
		// TODO Auto-generated method stub
		
	}



	private void processData() {
		// TODO Auto-generated method stub
		
	}



	private void callAPIs() {
		// TODO Auto-generated method stub
		
        AppConstants.CHANNELS_RSS_URLS.forEach(prekitReportType ->
        {
        
        	
        	
		});
		
	}



}
