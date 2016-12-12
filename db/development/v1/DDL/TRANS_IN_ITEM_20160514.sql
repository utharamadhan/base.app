ALTER TABLE TRANS_IN_ITEM ADD COLUMN fk_company_product_to bigint;
ALTER TABLE TRANS_IN_ITEM ADD COLUMN volume_in_kg numeric(24,4);
ALTER TABLE TRANS_IN_ITEM ADD COLUMN remaining_volume_in_kg numeric(24,4);
ALTER TABLE TRANS_IN_ITEM ADD COLUMN status integer NOT NULL DEFAULT 1;

ALTER TABLE TRANS_IN_ITEM ADD CONSTRAINT tii_fk_company_product_to_cp_pk_company_product FOREIGN KEY (fk_company_product_to)
      REFERENCES company_product (pk_company_product) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
      
ALTER TABLE TRANS_IN_ITEM DROP CONSTRAINT ti_fk_lookup_uom_l_pk_lookup;
ALTER TABLE TRANS_IN_ITEM ADD CONSTRAINT tii_fk_lookup_uom_l_pk_lookup FOREIGN KEY (fk_lookup_uom)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;