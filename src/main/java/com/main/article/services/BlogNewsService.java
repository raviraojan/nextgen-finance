/**
 *
 */
package com.main.article.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.main.article.models.BlogNews;
import com.main.article.repositories.BlogNewsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ravirao1224
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BlogNewsService {

	private final BlogNewsRepository blogNewsRepository;

	public Page<BlogNews> getAllArticles(Pageable pageable) {
		Page<BlogNews> blogNews = blogNewsRepository.findAll(pageable);

		return blogNews;
	}

}
