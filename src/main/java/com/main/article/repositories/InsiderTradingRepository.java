/**
 *
 */
package com.main.article.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.article.models.Article;
import com.main.article.models.InsiderTradingModel;


/**
 * @author ravirao1224
 *
 */
public interface InsiderTradingRepository extends JpaRepository<InsiderTradingModel, String> {

	Optional<InsiderTradingModel> findById(Long id);

}
