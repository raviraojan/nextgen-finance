package com.main.article.dto;



import java.time.Instant;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class InsiderTradingDTO {

	private String description;
	private String title;
	private LocalDateTime publishDate;
	private String newsUrl;


}
