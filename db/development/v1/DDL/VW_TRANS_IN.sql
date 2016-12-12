create or replace view VW_TRANS_IN
as
select ti.pk_trans_in as pk_trans_in, 
	ti.fk_company as fk_company, 
	ti.in_date as in_date,
	psup.code as supplier_code, 
	psup.name as supplier_name, 
	cp.product_code as product_code, 
	cp.product_name as product_name, 
	source_type as source_type, 
	tii.fk_lookup_item_type as fk_lookup_item_type,
	ittype.code as item_type_code,
	loUOM.pk_lookup as fk_lookup_uom,
	loUOM.code as uom_code, 
	tii.volume as volume, 
	tii.fee as fee, 
	total_fee as total_fee,
	ti.creation_time
from trans_in ti
inner join party psup on psup.pk_party = ti.fk_party_third_party
inner join trans_in_item tii 
	join company_product cp on cp.pk_company_product = tii.fk_company_product 
	join lookup loUOM on loUOM.pk_lookup = tii.fk_lookup_uom
	join lookup ittype on ittype.pk_lookup = tii.fk_lookup_item_type
on tii.fk_trans_in = ti.pk_trans_in;
