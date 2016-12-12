create or replace view VW_STOCK
as
select s.pk_stock as pk_stock, 
	c.pk_company as fk_company,
	pSupp.code as supplier_code,
	pSupp.name as supplier_name,
	cp.product_code as product_code,
	cp.product_name as product_name,
	s.fk_lookup_uom as fk_lookup_uom,
	loUOM.code as uom_code,
	loUOM.name_id as uom_name,
	s.volume as volume,
	tii.total_fee as buying_price,
	s.hpp
from stock s
inner join company_product cp on cp.pk_company_product = s.fk_company_product
inner join company c on c.pk_company = cp.fk_company
inner join lookup loUOM on loUOM.pk_lookup = s.fk_lookup_uom
left join stock_history sh 
	join trans_in_item tii on tii.pk_trans_in_item = sh.fk_trans_in_item 
	join trans_in ti on ti.pk_trans_in = tii.fk_trans_in
	join party pSupp on pSupp.pk_party = ti.fk_party_third_party
on sh.fk_stock = s.pk_stock;