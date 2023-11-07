/**
 * 
 */
package com.main.article.services;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.main.article.dto.ArticleRequestDTO;
import com.main.article.models.Article;
import com.main.article.repositories.FinArticlesRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ravirao1224
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FinArticleService {

	private final FinArticlesRepository finArticlesRepository; 
	
	public Page<Article> getAllArticles(Pageable pageable)
	{
		Page<Article> articles = finArticlesRepository.findAll(pageable);
		
		return articles;
	}
	
	public Article submitArticle(ArticleRequestDTO artReqDTO)
	{
		ModelMapper modelMapper = new ModelMapper();
		//OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
		//We can use ModelMapper to implicitly map an order instance to a new OrderDTO
		
		Article article = new Article();
		
		//Article articleEntity = modelMapper.map(artReqDTO,article);
		
		article.setTitle(artReqDTO.getArticletitle());
		article.setDescription(artReqDTO.getHtml());
		
		return finArticlesRepository.save(article);
	}
	
	
	

}
