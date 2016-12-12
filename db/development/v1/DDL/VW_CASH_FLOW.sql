-- DROP VIEW vw_cash_flow;
CREATE OR REPLACE VIEW vw_cash_flow AS 
SELECT row_number() OVER (ORDER BY OUTER_VIEW.TRANS_DATE) AS ROWNUM_CASH_FLOW, OUTER_VIEW.* FROM
(SELECT c.pk_company as fk_company, 'Penerimaan' AS source, in_no AS id, in_date AS trans_date, 
('Penerimaan ' || tii.volume || ' ' || l.name_id || ' - ' || cp.product_name) AS desc, 
main_fee AS main_fee, total_in_fee AS other_fee, 0 AS debit, (main_fee + total_in_fee) AS kredit FROM trans_in ti
inner join trans_in_item tii on tii.fk_trans_in = ti.pk_trans_in
inner join company_product cp on cp.pk_company_product = tii.fk_company_product
inner join company c on c.pk_company = cp.fk_company
inner join lookup l on l.pk_lookup = tii.fk_lookup_uom
WHERE ti.status <> 0 
UNION ALL
SELECT c.pk_company as fk_company, 'Pengolahan' AS source, prod_no AS id, prod_date_from AS trans_date, 
('Pengolahan ' || tp.volume || ' ' || l.name_id || ' - ' || cpf.product_name || ' -> ' || cpt.product_name) AS desc,
0 AS main_fee, total_fee AS other_fee, 0 AS debit, total_fee AS kredit FROM trans_prod tp 
inner join company_product cpf on cpf.pk_company_product = tp.fk_company_product_from 
inner join company_product cpt on cpt.pk_company_product = tp.fk_company_product_to 
inner join company c on c.pk_company = cpf.fk_company
inner join lookup l on l.pk_lookup = tp.fk_lookup_uom 
WHERE tp.status <> 0 
UNION ALL
SELECT c.pk_company as fk_company, 'Penjualan' AS source, out_no AS id, out_date AS trans_date, 
'Penjualan ' AS desc,
main_fee AS main_fee, total_out_fee AS other_fee, main_fee AS debit, total_out_fee AS kredit FROM trans_out tot
inner join trans_out_item toi on toi.fk_trans_out = tot.pk_trans_out 
inner join company c on c.pk_company = tot.fk_company
WHERE tot.status <> 0) OUTER_VIEW