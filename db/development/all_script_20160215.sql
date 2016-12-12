--clear transaction
TRUNCATE trans_buy RESTART IDENTITY CASCADE;
TRUNCATE trans_buy_other_fee RESTART IDENTITY CASCADE;
TRUNCATE trans_production_other_fee RESTART IDENTITY CASCADE;
TRUNCATE trans_production_from RESTART IDENTITY CASCADE;
TRUNCATE trans_production_to RESTART IDENTITY CASCADE;
TRUNCATE trans_production RESTART IDENTITY CASCADE;
TRUNCATE trans_sell_other_fee RESTART IDENTITY CASCADE;
TRUNCATE trans_sell_detail RESTART IDENTITY CASCADE;
TRUNCATE trans_sell RESTART IDENTITY CASCADE;

--clear stock
TRUNCATE stock RESTART IDENTITY CASCADE;

TRUNCATE stock_conversion RESTART IDENTITY CASCADE;

DELETE FROM LOOKUP WHERE LOOKUP_GROUP = 'TYPE_STOCK';
insert into lookup(lookup_group,fk_lookup_parent,code,name,descr,order_no,is_active,created_by,creation_time,modified_by,modification_time)
values('TYPE_STOCK',(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'CATEGORY_STOCK' AND CODE = 'BBP' ),'GKP','Gabah Kering Panen','Gabah Kering Panen',1,true,'SYSTEM',now(),'SYSTEM',now());
insert into lookup(lookup_group,fk_lookup_parent,code,name,descr,order_no,is_active,created_by,creation_time,modified_by,modification_time)
values('TYPE_STOCK',(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'CATEGORY_STOCK' AND CODE = 'BBP' ),'GKG','Gabah Kering Giling','Gabah Kering Giling',2,true,'SYSTEM',now(),'SYSTEM',now());
insert into lookup(lookup_group,fk_lookup_parent,code,name,descr,order_no,is_active,created_by,creation_time,modified_by,modification_time)
values('TYPE_STOCK',(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'CATEGORY_STOCK' AND CODE = 'BBP' ),'BR','Beras Pecah Kulit','Beras Pecah Kulit',3,true,'SYSTEM',now(),'SYSTEM',now());
insert into lookup(lookup_group,fk_lookup_parent,code,name,descr,order_no,is_active,created_by,creation_time,modified_by,modification_time)
values('TYPE_STOCK',(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'CATEGORY_STOCK' AND CODE = 'BBP' ),'WR','Beras Putih','Beras Putih',4,true,'SYSTEM',now(),'SYSTEM',now());

insert into lookup(lookup_group,fk_lookup_parent,code,name,descr,order_no,is_active,created_by,creation_time,modified_by,modification_time)
values('TYPE_STOCK',(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'CATEGORY_STOCK' AND CODE = 'BBNP' ),'G','Gas','Gas',1,true,'SYSTEM',now(),'SYSTEM',now());
insert into lookup(lookup_group,fk_lookup_parent,code,name,descr,order_no,is_active,created_by,creation_time,modified_by,modification_time)
values('TYPE_STOCK',(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'CATEGORY_STOCK' AND CODE = 'BBNP' ),'S','Solar','Solar',2,true,'SYSTEM',now(),'SYSTEM',now());
insert into lookup(lookup_group,fk_lookup_parent,code,name,descr,order_no,is_active,created_by,creation_time,modified_by,modification_time)
values('TYPE_STOCK',(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'CATEGORY_STOCK' AND CODE = 'BBNP' ),'K','Karung','Karung',3,true,'SYSTEM',now(),'SYSTEM',now());

ALTER TABLE stock_conversion ADD COLUMN fk_lookup_proses_trans bigint NOT NULL;
alter table stock_conversion add constraint sc_fk_lookup_proses_trans_l_pk_lookup FOREIGN KEY (fk_lookup_proses_trans)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
	  
insert into stock_conversion(fk_rmu,fk_lookup_type_stock_from,fk_lookup_type_stock_to,created_by,creation_time,modified_by,modification_time,fk_lookup_proses_trans)
values(1,(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'TYPE_STOCK' AND CODE = 'GKP'),(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'TYPE_STOCK' AND CODE = 'GKG'),'SYSTEM',now(),'SYSTEM',now(),(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'PROSES_TRANS' AND CODE = 'PK'));
insert into stock_conversion(fk_rmu,fk_lookup_type_stock_from,fk_lookup_type_stock_to,created_by,creation_time,modified_by,modification_time,fk_lookup_proses_trans)
values(1,(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'TYPE_STOCK' AND CODE = 'GKG'),(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'TYPE_STOCK' AND CODE = 'BR'),'SYSTEM',now(),'SYSTEM',now(),(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'PROSES_TRANS' AND CODE = 'PG'));
insert into stock_conversion(fk_rmu,fk_lookup_type_stock_from,fk_lookup_type_stock_to,created_by,creation_time,modified_by,modification_time,fk_lookup_proses_trans)
values(1,(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'TYPE_STOCK' AND CODE = 'GKG'),(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'TYPE_STOCK' AND CODE = 'WR'),'SYSTEM',now(),'SYSTEM',now(),(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'PROSES_TRANS' AND CODE = 'PG'));
insert into stock_conversion(fk_rmu,fk_lookup_type_stock_from,fk_lookup_type_stock_to,created_by,creation_time,modified_by,modification_time,fk_lookup_proses_trans)
values(1,(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'TYPE_STOCK' AND CODE = 'BR'),(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'TYPE_STOCK' AND CODE = 'WR'),'SYSTEM',now(),'SYSTEM',now(),(SELECT pk_lookup FROM lookup WHERE LOOKUP_GROUP = 'PROSES_TRANS' AND CODE = 'PG'));

ALTER TABLE stock ADD COLUMN fk_rmu bigint;
alter table stock add constraint s_fk_rmu_rmu_pk_rmu FOREIGN KEY (fk_rmu)
      REFERENCES rmu (pk_rmu) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
	  
alter table trans_production alter column fk_lookup_method drop not null;

DROP VIEW vw_stock_produksi;
DROP VIEW vw_stock_non_produksi;
			 
ALTER TABLE trans_buy ALTER COLUMN unit_buy_fee TYPE numeric(24,4);
ALTER TABLE trans_buy ALTER COLUMN total_buy_fee TYPE numeric(24,4);
ALTER TABLE trans_buy_other_fee ALTER COLUMN fee TYPE numeric(24,4);
ALTER TABLE trans_production_other_fee ALTER COLUMN fee TYPE numeric(24,4);
ALTER TABLE trans_sell ALTER COLUMN total_fee TYPE numeric(24,4);
ALTER TABLE trans_sell_detail ALTER COLUMN unit_sell_fee TYPE numeric(24,4);
ALTER TABLE trans_sell_detail ALTER COLUMN total_sell_fee TYPE numeric(24,4);
ALTER TABLE trans_sell_other_fee ALTER COLUMN fee TYPE numeric(24,4);
ALTER TABLE stock ALTER COLUMN hpp TYPE numeric(24,4);

create or replace view vw_stock_produksi as
	select pk_stock, name, transaction_date, volume, hpp, satuan, pk_rmu from
        ((select pk_stock as pk_stock, lo.name as name, tb.buying_date as transaction_date, 
		s.volume as volume, s.hpp as hpp, loUnit.name as satuan, r.pk_rmu as pk_rmu from trans_buy tb
        inner join stock s on tb.fk_stock = s.pk_stock
	inner join lookup lo 
			join lookup loParent on lo.fk_lookup_parent = loParent.pk_lookup and loParent.code = 'BBP'
		on lo.pk_lookup = s.fk_lookup_stock_type
	inner join lookup loUnit on loUnit.pk_lookup = tb.fk_lookup_volume_unit
	inner join rmu r on r.pk_rmu = tb.fk_rmu)
	union all
	(select pk_stock as pk_stock, lo.name as name, tp.production_date as transaction_date, s.volume as volume, s.hpp as hpp, loUnit.name as satuan, r.pk_rmu as pk_rmu from trans_production_to tpt
	inner join stock s on tpt.fk_stock_to = s.pk_stock
	inner join lookup lo
			join lookup loParent on lo.fk_lookup_parent = loParent.pk_lookup and loParent.code = 'BBP'
		on lo.pk_lookup = s.fk_lookup_stock_type
	inner join lookup loUnit on loUnit.pk_lookup = tpt.fk_lookup_volume_unit
	inner join trans_production tp on tp.pk_trans_production = tpt.fk_trans_production
	inner join rmu r on r.pk_rmu = tp.fk_rmu)) a;
	
CREATE OR REPLACE VIEW vw_stock_non_produksi AS 
 SELECT b.pk_stock,
    b.name,
    b.transaction_date,
    b.volume,
    b.hpp,
    b.satuan,
	b.pk_rmu
   FROM ( SELECT s.pk_stock,
            lo.name,
            tb.buying_date AS transaction_date,
            s.volume,
            s.hpp,
            lounit.name AS satuan,
            r.pk_rmu
           FROM trans_buy tb
             JOIN stock s ON tb.fk_stock = s.pk_stock
             JOIN (lookup lo
             JOIN lookup loparent ON lo.fk_lookup_parent = loparent.pk_lookup AND loparent.code::text = 'BBNP'::text) ON lo.pk_lookup = s.fk_lookup_stock_type
             JOIN lookup lounit ON lounit.pk_lookup = tb.fk_lookup_volume_unit
             JOIN rmu r ON r.pk_rmu = tb.fk_rmu
        UNION ALL
         SELECT s.pk_stock,
            lo.name,
            tp.production_date AS transaction_date,
            s.volume,
            s.hpp,
            lounit.name AS satuan,
            r.pk_rmu
           FROM trans_production_to tpt
             JOIN stock s ON tpt.fk_stock_to = s.pk_stock
             JOIN (lookup lo
             JOIN lookup loparent ON lo.fk_lookup_parent = loparent.pk_lookup AND loparent.code::text = 'BBNP'::text) ON lo.pk_lookup = s.fk_lookup_stock_type
             JOIN lookup lounit ON lounit.pk_lookup = tpt.fk_lookup_volume_unit
             JOIN trans_production tp ON tp.pk_trans_production = tpt.fk_trans_production
             JOIN rmu r ON r.pk_rmu = tp.fk_rmu) b;

             
-- change time to date time
alter table app_parameter alter column creation_time type timestamp with time zone using date('20160215') + creation_time;
alter table app_parameter alter column modification_time type timestamp with time zone using date('20160215') + modification_time;
alter table app_role alter column creation_time type timestamp with time zone using date('20160215') + creation_time;
alter table app_role alter column modification_time type timestamp with time zone using date('20160215') + modification_time;
alter table app_user alter column creation_time type timestamp with time zone using date('20160215') + creation_time;
alter table app_user alter column modification_time type timestamp with time zone using date('20160215') + modification_time;
alter table app_user_activity alter column creation_time type timestamp with time zone using date('20160215') + creation_time;
alter table app_user_pass_hist alter column creation_time type timestamp with time zone using date('20160215') + creation_time;
alter table error alter column creation_time type timestamp with time zone using date('20160215') + creation_time;
alter table lookup alter column creation_time type timestamp with time zone using date('20160215') + creation_time;
alter table lookup alter column modification_time type timestamp with time zone using date('20160215') + modification_time;
alter table party alter column creation_time type timestamp with time zone using date('20160215') + creation_time;
alter table party alter column modification_time type timestamp with time zone using date('20160215') + modification_time;
alter table party_address alter column creation_time type timestamp with time zone using date('20160215') + creation_time;
alter table party_address alter column modification_time type timestamp with time zone using date('20160215') + modification_time;
alter table post_category alter column creation_time type timestamp with time zone using date('20160215') + creation_time;
alter table post_category alter column modification_time type timestamp with time zone using date('20160215') + modification_time;
alter table post_content alter column creation_time type timestamp with time zone using date('20160215') + creation_time;
alter table post_content alter column modification_time type timestamp with time zone using date('20160215') + modification_time;
alter table rmu alter column creation_time type timestamp with time zone using date('20160215') + creation_time;
alter table rmu alter column modification_time type timestamp with time zone using date('20160215') + modification_time;
alter table rmu_lookup alter column creation_time type timestamp with time zone using date('20160215') + creation_time;
alter table rmu_stock_location alter column creation_time type timestamp with time zone using date('20160215') + creation_time;
alter table rmu_stock_location alter column modification_time type timestamp with time zone using date('20160215') + modification_time;
alter table runtime_user_login alter column login_time type timestamp with time zone using date('20160215') + login_time;
alter table stock alter column creation_time type timestamp with time zone using date('20160215') + creation_time;
alter table stock alter column modification_time type timestamp with time zone using date('20160215') + modification_time;
alter table stock_conversion alter column creation_time type timestamp with time zone;
alter table stock_conversion alter column modification_time type timestamp with time zone;
alter table trans_buy alter column creation_time type timestamp with time zone using date('20160215') + creation_time;
alter table trans_buy alter column modification_time type timestamp with time zone using date('20160215') + modification_time;
alter table trans_production alter column creation_time type timestamp with time zone using date('20160215') + creation_time;
alter table trans_production alter column modification_time type timestamp with time zone using date('20160215') + modification_time;
alter table trans_sell alter column creation_time type timestamp with time zone using date('20160215') + creation_time;
alter table trans_sell alter column modification_time type timestamp with time zone using date('20160215') + modification_time;

CREATE OR REPLACE VIEW VW_STOCK AS
SELECT PK_STOCK, FK_RMU, CATEGORY, STOCK_NAME, VOLUME, SATUAN, HPP
FROM (
             select st.pk_stock, fk_rmu as fk_rmu, loCa.name as category, loTp.name as stock_name, volume, loUn.name as satuan, hpp 
             from stock st
             inner join lookup loCa on loCa.pk_lookup = st.fk_lookup_stock_category
             inner join lookup loTp on loTp.pk_lookup = st.fk_lookup_stock_type
             inner join lookup loUn on loUn.pk_lookup = st.fk_lookup_volume_unit
             ) a;