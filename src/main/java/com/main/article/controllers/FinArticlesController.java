/**
 * 
 */
package com.main.article.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.article.dto.ArticleRequestDTO;
import com.main.article.dto.ArticleResponseDTO;
import com.main.article.models.Article;
import com.main.article.repositories.FinArticlesRepository;
import com.main.article.services.FinArticleService;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ravirao1224
 *
 */
@RestController
@Slf4j
@RequestMapping("/nextgenfin/api")
@RequiredArgsConstructor
@EnableAsync
public class FinArticlesController {

	@Autowired
	private final FinArticlesRepository finArticlesRepository;
	
	private final FinArticleService finArticleService;

	@GetMapping(path = "/articles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Article> getAllArticles(
			@PageableDefault(size = 12) @SortDefault(sort = "creationDate", direction = Sort.Direction.ASC) Pageable pageable) {

		return finArticleService.getAllArticles(pageable);
	}

	@PostMapping(path = "/sub/article", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Article submitArticle(@RequestBody ArticleRequestDTO articleRequest) {
		
		System.out.println(" getArticletitle " + articleRequest.getArticletitle());
		System.out.println("getHtml " + articleRequest.getHtml());
		System.out.println("");

		return finArticleService.submitArticle(articleRequest);
	}

}
