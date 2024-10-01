/**
 *
 */
package com.main.article.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.main.article.dto.ArticleRequestDTO;
import com.main.article.dto.ImagePathResponse;
import com.main.article.dto.InsiderTradingDTO;
import com.main.article.dto.NewsDTO;
import com.main.article.models.Article;
import com.main.article.models.ChannelNews;
import com.main.article.models.InsiderTradingModel;
import com.main.article.repositories.FinArticlesRepository;
import com.main.article.services.FinArticleService;
import com.main.article.services.InsiderTradingDataSyncService;
import com.main.article.services.InsiderTradingService;
import com.main.article.services.NewsService;

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
public class InsiderTradingController {

	private final NewsService newsService;
	
	private final InsiderTradingService insiderTradingService;

	@GetMapping(path = "/insidertrading", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<InsiderTradingDTO> getAllArticles(
			@PageableDefault(size = 50) Pageable pageable) {

		

		return insiderTradingService.getAllInsiderTradngData(pageable);
	}


}
