/**
 * 
 */
package com.main.article.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.main.article.models.Article;


/**
 * @author ravirao1224
 *
 */
public interface FinArticlesRepository extends JpaRepository<Article, String> {

	Optional<Article> findById(Long id);
	
}
