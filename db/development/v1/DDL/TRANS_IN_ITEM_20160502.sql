CREATE TABLE trans_in_item
(
  pk_trans_in_item bigserial NOT NULL,
  fk_trans_in bigint NOT NULL,
  fk_company_product bigint,
  volume numeric(24,4),
  fk_lookup_uom bigint,
  fee numeric(24,4),
  total_fee numeric(24,4),
  CONSTRAINT pk_trans_in_item PRIMARY KEY (pk_trans_in_item),
  CONSTRAINT tii_fk_trans_in_ti_pk_trans_in FOREIGN KEY (fk_trans_in)
      REFERENCES trans_in (pk_trans_in) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tii_fk_company_product_cp_pk_company_product FOREIGN KEY (fk_company_product)
      REFERENCES company_product (pk_company_product) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT ti_fk_lookup_uom_l_pk_lookup FOREIGN KEY (fk_lookup_uom)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
