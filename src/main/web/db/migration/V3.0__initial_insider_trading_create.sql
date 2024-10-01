use finarticles;
CREATE TABLE insider_trading (
id bigint NOT NULL AUTO_INCREMENT,
created_by varchar(255) DEFAULT NULL,
creation_date datetime(6) DEFAULT NULL,
last_modified_by varchar(255) DEFAULT NULL,
last_modified_date datetime(6) DEFAULT NULL,

issuer_cik  varchar(255) DEFAULT NULL,
issuer_name  varchar(255) DEFAULT NULL,
rpt_owner_name  varchar(255) DEFAULT NULL,
rpt_owner_cik  varchar(255) DEFAULT NULL,
security_title  varchar(255) DEFAULT NULL,
trans_date  varchar(255) DEFAULT NULL,
trans_code  varchar(50) DEFAULT NULL,
trans_shares decimal(12,3) default 0,
trans_price_per_share decimal(12,3) default 0,
shares_owned_after_trans decimal(12,3) default 0,
publish_date datetime(6) DEFAULT NULL,
sec_url  varchar(400) DEFAULT NULL,
is_director BOOLEAN,
is_officer BOOLEAN,
is_ten_percent_owner BOOLEAN,
is_other BOOLEAN,
officer_title  varchar(255) DEFAULT NULL,
accession_number  varchar(255) DEFAULT NULL,
issuer_trading_symbol  varchar(255) DEFAULT NULL,
PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;