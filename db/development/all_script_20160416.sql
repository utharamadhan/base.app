ALTER TABLE PURCHASE_ORDER DROP COLUMN VALID;
ALTER TABLE PURCHASE_ORDER ADD STATUS INTEGER NOT NULL DEFAULT 1;

ALTER TABLE GOODS_RECEIPT_NOTE DROP COLUMN VALID;
ALTER TABLE GOODS_RECEIPT_NOTE ADD STATUS INTEGER NOT NULL DEFAULT 1;

DROP TABLE COMPANY_LOOKUP CASCADE;
CREATE TABLE company_lookup
(
  pk_company_lookup bigserial NOT NULL,
  fk_company bigint NOT NULL,
  lookup_group character varying(100) NOT NULL,
  code character varying(100) NOT NULL,
  name_id character varying(100),
  name_en character varying(100),
  order_no bigint,
  fk_lookup bigint NOT NULL,
  status integer NOT NULL DEFAULT 1,
  created_by character varying(200) NOT NULL,
  creation_time timestamp with time zone NOT NULL DEFAULT now(),
  modified_by character varying(200),
  modification_time timestamp with time zone,
  CONSTRAINT pk_company_lookup PRIMARY KEY (pk_company_lookup),
  CONSTRAINT cl_fk_company_c_pk_company FOREIGN KEY (fk_company)
      REFERENCES company (pk_company) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT cl_fk_lookup_l_pk_lookup FOREIGN KEY (fk_lookup)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

ALTER TABLE LOOKUP ADD STATUS INTEGER NOT NULL DEFAULT 1;
UPDATE LOOKUP SET STATUS = 1 where IS_ACTIVE = true;
UPDATE LOOKUP SET STATUS = 0 where IS_ACTIVE = false;
ALTER TABLE LOOKUP DROP COLUMN IS_ACTIVE;

insert into lookup_group (lookup_group,group_descr, is_updateable, is_viewable)
values('CURRENCY', 'Currency',false, true);

insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('TERM_OF_PAYMENT','COD', 'Cash On Delivery (COD)','Cash On Delivery (COD)',1,1,'SYSTEM',now(),'SYSTEM',now());
insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('TERM_OF_PAYMENT','30D', '30 days after delivery', '30 days after delivery', 2,1,'SYSTEM',now(),'SYSTEM',now());
insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('TERM_OF_PAYMENT','45D', '45 days after delivery', '45 days after delivery', 3,1,'SYSTEM',now(),'SYSTEM',now());
insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('TERM_OF_PAYMENT','60D', '60 days after delivery', '60 days after delivery', 4,1,'SYSTEM',now(),'SYSTEM',now());
insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('TERM_OF_PAYMENT','90D', '90 days after delivery', '90 days after delivery', 5,1,'SYSTEM',now(),'SYSTEM',now());

insert into lookup_group (lookup_group,group_descr, is_updateable, is_viewable)
values('TERM_OF_PAYMENT', 'Term Of Payment',false, true);

insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('CURRENCY','IDR','IDR','Rupiah',1,1,'SYSTEM',now(),'SYSTEM',now());
insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('CURRENCY','USD','USD','US Dollar',2,1,'SYSTEM',now(),'SYSTEM',now());
insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('CURRENCY','EUR','EUR','Euro',3,1,'SYSTEM',now(),'SYSTEM',now());

alter table company_address add CREATED_BY character varying(255);
alter table company_address add MODIFIED_BY character varying(255);
alter table company_address add CREATION_TIME timestamp with time zone;
alter table company_address add MODIFICATION_TIME timestamp with time zone;

insert into lookup_group (lookup_group,group_descr, is_updateable, is_viewable)
values('PARTY_ROLE', 'Party Role',false, true);

insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('PARTY_ROLE','SP','Pemasok','Supplier',1,1,'SYSTEM',now(),'SYSTEM',now());
insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('PARTY_ROLE','CS','Pelanggan','Customer',2,1,'SYSTEM',now(),'SYSTEM',now());
insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('PARTY_ROLE','TR','Distributor','Transporter',3,1,'SYSTEM',now(),'SYSTEM',now());