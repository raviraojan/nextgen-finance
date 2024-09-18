package com.main.article.dto;



import java.time.Instant;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NewsDTO {

	private Long id;
	private String imagePath;
	private String videoPath;
	private String shortDescription;
	private String description;
	private String title;
	private String source;
	private LocalDateTime publishDate;
	private String newsUrl;
	private String timeElpased;


}
