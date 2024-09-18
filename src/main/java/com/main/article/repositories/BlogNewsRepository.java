/**
 *
 */
package com.main.article.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import com.main.article.models.BlogNews;


/**
 * @author ravirao1224
 *
 */
public interface BlogNewsRepository extends JpaRepository<BlogNews, String> {


}
