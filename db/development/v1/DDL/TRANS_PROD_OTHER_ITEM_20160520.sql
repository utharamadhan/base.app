CREATE TABLE trans_prod_other_item
(
  pk_trans_prod_other_item bigserial NOT NULL,
  fk_trans_prod bigint NOT NULL,
  fk_company_product bigint,
  fk_stock bigint,
  volume numeric(24,4),
  volume_in_kg numeric(24,4),
  fk_lookup_uom bigint,
  status integer NOT NULL DEFAULT 1,
  CONSTRAINT pk_trans_prod_other_item PRIMARY KEY (pk_trans_prod_other_item),
  CONSTRAINT tpoi_fk_trans_prod_tp_pk_trans_prod FOREIGN KEY (fk_trans_prod)
      REFERENCES trans_prod (pk_trans_prod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tpoi_fk_company_product_cp_pk_company_product FOREIGN KEY (fk_company_product)
      REFERENCES company_product (pk_company_product) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tpoi_fk_stock_s_pk_stock FOREIGN KEY (fk_stock)
      REFERENCES stock (pk_stock) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tpoi_fk_lookup_uom_l_pk_lookup FOREIGN KEY (fk_lookup_uom)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

DROP TABLE COMPANY_WAREHOUSE_PRODUCT;

ALTER TABLE TRANS_PROD ADD COLUMN fk_company_warehouse_main bigint NOT NULL;
ALTER TABLE TRANS_PROD ADD 
CONSTRAINT tp_fk_company_warehouse_main_cw_pk_company_warehouse FOREIGN KEY (fk_company_warehouse_main)
      REFERENCES company_warehouse (pk_company_warehouse) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE TRANS_PROD ADD COLUMN fk_company_warehouse_other bigint NOT NULL;
ALTER TABLE TRANS_PROD ADD 
CONSTRAINT tp_fk_company_warehouse_other_cw_pk_company_warehouse FOREIGN KEY (fk_company_warehouse_other)
      REFERENCES company_warehouse (pk_company_warehouse) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
      
ALTER TABLE TRANS_PROD ADD COLUMN fk_party_third_party bigint;
ALTER TABLE TRANS_PROD ADD CONSTRAINT tp_fk_party_third_party_p_pk_party FOREIGN KEY (fk_party_third_party)
      REFERENCES party (pk_party) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE TRANS_PROD ALTER COLUMN fk_stock_to DROP NOT NULL;
ALTER TABLE TRANS_PROD ALTER COLUMN plan_name DROP NOT NULL;
ALTER TABLE TRANS_PROD DROP COLUMN prod_type;
ALTER TABLE TRANS_PROD ADD COLUMN est_days int;

ALTER TABLE TRANS_PROD ADD prod_no character varying(15);