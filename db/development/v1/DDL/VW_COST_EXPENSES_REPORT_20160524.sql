create or replace view VW_COST_EXPENSES_REPORT
as
select row_number() OVER (ORDER BY INNER_VIEW.pk_trans) AS ROWNUM_COST_EXPENSES, INNER_VIEW.* from (
select ti.pk_trans_in as pk_trans, ti.fk_company, ti.in_date as trans_date, tif.descr, tif.fee, ti.status from trans_in_fee tif
inner join trans_in ti on ti.pk_trans_in = tif.fk_trans_in
UNION ALL
select tp.pk_trans_prod as pk_trans, tp.fk_company, tp.prod_date_from as trans_date, tpf.descr, tpf.fee, tp.status from trans_prod_fee tpf
inner join trans_prod tp on tp.pk_trans_prod = tpf.fk_trans_prod
UNION ALL
select pto.pk_trans_out as pk_trans, pto.fk_company, pto.out_date as trans_date, tof.descr, tof.fee, pto.status from trans_out_fee tof
inner join trans_out pto on pto.pk_trans_out = tof.fk_trans_out)
INNER_VIEW;