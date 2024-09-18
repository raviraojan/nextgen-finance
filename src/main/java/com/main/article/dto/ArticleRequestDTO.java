package com.main.article.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ArticleRequestDTO {

	@JsonProperty
	private String articletitle;
	@JsonProperty
	private String html;

}

