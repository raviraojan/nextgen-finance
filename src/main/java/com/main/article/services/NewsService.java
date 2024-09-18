/**
 *
 */
package com.main.article.services;

import java.lang.reflect.Type;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.main.article.dto.NewsDTO;
import com.main.article.models.ChannelNews;
import com.main.article.repositories.ChannelNewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ravirao1224
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService {

	private final ChannelNewsRepository channelNewsRepository;

	public Page<NewsDTO> getAllChannelNews(Pageable pageable) {

		Page<ChannelNews> pageNewsEntityItems = channelNewsRepository.findAll(pageable);
		System.out.println("entered into processing");
		return addTimeElapsedReturnDTO(pageNewsEntityItems, pageable);
	}

	// This method takes page with db entity, calculates TimeElapsed and creates
	// into
	// DTOs, Page
	private Page<NewsDTO> addTimeElapsedReturnDTO(Page<ChannelNews> pageNewsEntityItems, Pageable pageable) {

		List<NewsDTO> listNewsDTOs = new ArrayList<NewsDTO>();
		// Get List
		if (pageNewsEntityItems != null && pageNewsEntityItems.getContent() != null) {

			ModelMapper modelMapper = new ModelMapper();

			// get list of entity objects
			List<ChannelNews> channelNewsList = pageNewsEntityItems.stream().collect(Collectors.toList());

			// create DTO and call time elapsed
			for (ChannelNews channelNewsItem : channelNewsList) {

				NewsDTO newsDTO = modelMapper.map(channelNewsItem, NewsDTO.class);
				updateTimeElpsed(newsDTO);
				listNewsDTOs.add(newsDTO);

			}

		}
		// return new PageImpl
		return new PageImpl<>(listNewsDTOs, pageable, pageNewsEntityItems.getTotalElements());
	}

	// calculate and add time elapsed.
	//
	private void updateTimeElpsed(NewsDTO newsDTO) {

		if (newsDTO != null && newsDTO.getPublishDate() != null) {

			//We get GMT time, because we have saved in DB as GMT, and DB DateTime stores as is, without modifying
			LocalDateTime gmtlocaldate = newsDTO.getPublishDate();

			Instant timestamp = new Date().toInstant();

			LocalDateTime currentGMTDT = LocalDateTime.ofInstant(timestamp, ZoneId.of("GMT"));

			Duration dt = Duration.between(gmtlocaldate, currentGMTDT);

			StringBuilder timeElpased = new StringBuilder();

			if (dt.toDaysPart() > 0) {
				timeElpased.append(dt.toDaysPart()).append(" DAYS AGO");

			} else if (dt.toHoursPart() > 0) {
				if (dt.toHoursPart() == 0) {
					timeElpased.append(dt.toHoursPart()).append(" HOUR AGO");
				} else {
					timeElpased.append(dt.toHoursPart()).append(" HOURS AGO");
				}
			} else {
				timeElpased.append(dt.toMinutesPart()).append(" Minutes AGO");
			}

			newsDTO.setTimeElpased(timeElpased.toString());

		}
	}

}
