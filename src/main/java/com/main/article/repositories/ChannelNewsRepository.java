/**
 *
 */
package com.main.article.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import com.main.article.models.ChannelNews;


/**
 * @author ravirao1224
 *
 */
public interface ChannelNewsRepository extends JpaRepository<ChannelNews, String> {


}
