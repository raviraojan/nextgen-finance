/**
 *
 */
package com.main.article.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.main.article.dto.ArticleRequestDTO;
import com.main.article.dto.InsiderTradingDTO;
import com.main.article.dto.NewsDTO;
import com.main.article.models.Article;
import com.main.article.models.ChannelNews;
import com.main.article.models.InsiderTradingModel;
import com.main.article.repositories.FinArticlesRepository;
import com.main.article.repositories.InsiderTradingRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ravirao1224
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InsiderTradingService {

	private final FinArticlesRepository finArticlesRepository;

	private final InsiderTradingRepository insiderTradingRepository;

	public Page<InsiderTradingDTO> getAllInsiderTradngData(Pageable pageable) {
		Page<InsiderTradingModel> insdTradngDataItems = insiderTradingRepository.findAll(pageable);

		return createNewsInsiderTradng(insdTradngDataItems, pageable);
	}



	// This method takes page with db entity, calculates TimeElapsed and creates
	// into
	// DTOs, Page
	private Page<InsiderTradingDTO> createNewsInsiderTradng(Page<InsiderTradingModel> insdTradngDataItems, Pageable pageable) {

		List<InsiderTradingDTO> InsiderTradingDTOs = new ArrayList<InsiderTradingDTO>();
		// Get List
		if (insdTradngDataItems != null && insdTradngDataItems.getContent() != null) {

			ModelMapper modelMapper = new ModelMapper();

			// get list of entity objects
			List<InsiderTradingModel> insiderTrdingItems = insdTradngDataItems.stream().collect(Collectors.toList());

			// create DTO and call time elapsed
			for (InsiderTradingModel insiderTradingmodel : insiderTrdingItems) {
				
				
				//FOR NOW CREATE ONLY SALES, PURCHASES ONLY, WILL CHECK AFTER REST OF TRANS CODES, IN NEXT ITERNATION,
				// FOR NOW DO IT IN BACKEND IN NEXT ITE MOVE IT TO FRONTEND, NO NEED TO WASTE PROCESSING.
			
				if(insiderTradingmodel.getTransCode()!=null && (insiderTradingmodel.getTransCode().equalsIgnoreCase("S") || insiderTradingmodel.getTransCode().equalsIgnoreCase("p") ))
				{
					InsiderTradingDTO insiderTradingDTO = new InsiderTradingDTO();
					
					insiderTradingDTO.setNewsUrl(insiderTradingmodel.getSecUrl());
					
					//insiderTradingDTO.setPublishDate(insiderTradingmodel.getTransDate());
					
					StringBuilder strTitle = new StringBuilder();
					
					String soldOrBought = null;
					
					if(insiderTradingmodel.getTransCode()!=null && insiderTradingmodel.getTransCode().equalsIgnoreCase("s"))
							{
						soldOrBought= "sold";
							}
					else if(insiderTradingmodel.getTransCode()!=null && insiderTradingmodel.getTransCode().equalsIgnoreCase("p"))
					{
						soldOrBought= "purchased";
					}
			
				BigDecimal price =	(insiderTradingmodel.getTransPricePerShare().multiply( insiderTradingmodel.getTransShares()));
					
					strTitle.append("Insider Trading: "+insiderTradingmodel.getRptOwnerName()+" "+soldOrBought+" Shares Worth Over $"+price+" of company "+insiderTradingmodel.getIssuerName()+" (ticker/exchange symbol is: "+insiderTradingmodel.getIssuerTradingSymbol()+")");
				
					insiderTradingDTO.setTitle(strTitle.toString());
					InsiderTradingDTOs.add(insiderTradingDTO);
				
				}
				
				

			}

		}
		// return new PageImpl
		return new PageImpl<>(InsiderTradingDTOs, pageable, insdTradngDataItems.getTotalElements());
	}

}
