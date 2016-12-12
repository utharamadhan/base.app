ALTER TABLE COMPANY_PRODUCT RENAME fk_lookup_product_type TO fk_lookup_item_type;

ALTER TABLE COMPANY_PRODUCT DROP CONSTRAINT cp_fk_lookup_product_type_l_pk_lookup;
ALTER TABLE COMPANY_PRODUCT ADD CONSTRAINT cp_fk_lookup_item_type_l_pk_lookup FOREIGN KEY (fk_lookup_item_type)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;