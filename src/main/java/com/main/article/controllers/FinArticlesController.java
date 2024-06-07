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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.multipart.MultipartFile;

import com.main.article.dto.ArticleRequestDTO;
import com.main.article.dto.ArticleResponseDTO;
import com.main.article.dto.ImagePathResponse;
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
			@PageableDefault(size = 2) @SortDefault(sort = "creationDate", direction = Sort.Direction.ASC) Pageable pageable) {

		System.out.println("enteere into articlessssssssssssssssssssss");
		return finArticleService.getAllArticles(pageable);
	}

	@PostMapping(path = "/api/upload")
	public ResponseEntity<ImagePathResponse> uploadImages(@RequestParam("image") MultipartFile image) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("path", "http://localhost:8091/image.jpg");

		System.out.println("upload imagessssssssssss ");
		String status = null;
		ImagePathResponse img = new ImagePathResponse();
		img.setPath("http://localhost:8091/image.jpg");

		return ResponseEntity.status(HttpStatus.OK).body(img);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
		// String uploadImage = imageService.uploadImage(file);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("path", "http://localhost:4200/assets/images/Test_img.png");

		List<String> remotepaths = new ArrayList<String>();

		remotepaths.add("http://localhost:4200/assets/images/Test_img.png");
		// map.put("remotePaths", remotepaths);

		System.out.println("upload imagessssssssssss ");
		String status = null;

		return ResponseEntity.status(HttpStatus.OK).body(map);
	}

	@GetMapping(path = "/articleDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Article getArticleDetails(@RequestParam Long id) {
		System.out.println("iddddddddddd" + id);
		return finArticleService.findArticleDetails(id);
	}

	@PostMapping(path = "/sub/article", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Article submitArticle(@RequestBody ArticleRequestDTO articleRequest) {

		System.out.println(" getArticletitle " + articleRequest.getArticletitle());
		System.out.println("getHtml " + articleRequest.getHtml());
		System.out.println("");

		return finArticleService.submitArticle(articleRequest);
	}

}
