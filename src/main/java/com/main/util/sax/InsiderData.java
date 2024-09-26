package com.main.util.sax;

import java.time.Instant;
import java.util.List;

import lombok.Data;

@Data
public class InsiderData {
	
	private String url;
	private Instant publishedDate;
	private String accessionNo;
	private List<String> xmlUrls;
	

}
