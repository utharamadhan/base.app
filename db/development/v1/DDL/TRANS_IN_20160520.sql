ALTER TABLE TRANS_IN DROP CONSTRAINT ti_fk_lookup_item_type_l_pk_lookup;
ALTER TABLE TRANS_IN DROP COLUMN fk_lookup_item_type;
ALTER TABLE TRANS_IN ADD COLUMN ITEM_TYPE_CATEGORY character varying(3) NOT NULL;

ALTER TABLE TRANS_IN_ITEM ADD COLUMN fk_lookup_item_type bigint;
ALTER TABLE TRANS_IN_ITEM ADD 
CONSTRAINT tii_fk_lookup_item_type_l_pk_lookup FOREIGN KEY (fk_lookup_item_type)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
      
ALTER TABLE TRANS_PROD ADD COLUMN TRANS_IN_SOURCE_TYPE character varying(3) NOT NULL;