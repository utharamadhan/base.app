-- View: vw_stock_produksi

DROP VIEW vw_stock_produksi;

CREATE OR REPLACE VIEW vw_stock_produksi AS 
 SELECT a.pk_stock,
 	a.seller_name,
    a.name,
    a.transaction_date,
    a.volume,
    a.hpp,
    a.satuan,
    a.pk_rmu
   FROM ( SELECT s.pk_stock,
   			p.name AS seller_name,
            lo.name,
            tb.buying_date AS transaction_date,
            s.volume,
            s.hpp,
            lounit.name AS satuan,
            r.pk_rmu
           FROM trans_buy tb
           	 JOIN party p ON tb.fk_party_seller = p.pk_party   
             JOIN stock s ON tb.fk_stock = s.pk_stock
             JOIN (lookup lo
             JOIN lookup loparent ON lo.fk_lookup_parent = loparent.pk_lookup AND loparent.code::text = 'BBP'::text) ON lo.pk_lookup = s.fk_lookup_stock_type
             JOIN lookup lounit ON lounit.pk_lookup = tb.fk_lookup_volume_unit
             JOIN rmu r ON r.pk_rmu = tb.fk_rmu
             GROUP BY s.pk_stock,
		   			p.name,
		            lo.name,
		            tb.buying_date,
		            s.volume,
		            s.hpp,
		            lounit.name,
		            r.pk_rmu
        UNION ALL
         SELECT s.pk_stock,
         	p.name AS seller_name,   
         	lo.name,
            tp.production_date AS transaction_date,
            s.volume,
            s.hpp,
            lounit.name AS satuan,
            r.pk_rmu
           FROM trans_production_to tpt
           	 JOIN trans_production tpd ON tpd.pk_trans_production = tpt.fk_trans_production
           	 LEFT OUTER JOIN party p ON tpd.fk_party_producer = p.pk_party
             JOIN stock s ON tpt.fk_stock_to = s.pk_stock
             JOIN (lookup lo
             JOIN lookup loparent ON lo.fk_lookup_parent = loparent.pk_lookup AND loparent.code::text = 'BBP'::text) ON lo.pk_lookup = s.fk_lookup_stock_type
             JOIN lookup lounit ON lounit.pk_lookup = tpt.fk_lookup_volume_unit
             JOIN trans_production tp ON tp.pk_trans_production = tpt.fk_trans_production
             JOIN rmu r ON r.pk_rmu = tp.fk_rmu) a;

ALTER TABLE vw_stock_produksi
  OWNER TO postgres;


ALTER TABLE trans_production ADD COLUMN total_production_fee numeric(24,4);
