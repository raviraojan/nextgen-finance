/**
 *
 */
package com.main.article.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ravirao1224
 *
 */

@Entity(name = "fin_articles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String imagePath;
	private String videoPath;
	private String shortDescription;
	private String description;
	private String title;

}
