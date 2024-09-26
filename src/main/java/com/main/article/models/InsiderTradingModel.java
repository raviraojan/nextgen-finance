/**
 *
 */
package com.main.article.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

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

@Entity(name = "insider_trading")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsiderTradingModel extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String issuerCik;
	private String issuerName;
	private String rptOwnerName;
	private String rptOwnerCik;
	private String securityTitle;
	private String transDate;
	private String transCode;
	private BigDecimal transShares;
	private BigDecimal transPricePerShare;
	private BigDecimal sharesOwnedAfterTrans;
	private LocalDateTime publishDate;
	private String secUrl;
	private Boolean isDirector;
	private Boolean isOfficer;
	private Boolean isTenPercentOwner;
	private Boolean isOther;
	private String officerTitle;
	private String accessionNumber;
	private String issuerTradingSymbol;
	
}
