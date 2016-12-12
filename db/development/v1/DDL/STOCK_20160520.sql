ALTER TABLE STOCK ADD COLUMN fk_latest_trans_in bigint;
ALTER TABLE STOCK ADD COLUMN fk_latest_trans_prod bigint;
ALTER TABLE STOCK ADD COLUMN fk_latest_trans_out bigint;

ALTER TABLE STOCK ADD CONSTRAINT s_fk_latest_trans_in_ti_pk_trans_in FOREIGN KEY (fk_latest_trans_in)
      REFERENCES trans_in (pk_trans_in) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE STOCK ADD CONSTRAINT s_fk_latest_trans_prod_tp_pk_trans_prod FOREIGN KEY (fk_latest_trans_prod)
      REFERENCES trans_prod (pk_trans_prod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE STOCK ADD CONSTRAINT s_fk_latest_trans_out_ti_pk_trans_out FOREIGN KEY (fk_latest_trans_out)
      REFERENCES trans_out (pk_trans_out) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
      
ALTER TABLE STOCK DROP COLUMN fk_latest_trans_out;
ALTER TABLE STOCK RENAME COLUMN fk_latest_trans_in TO fk_trans_in_from;
ALTER TABLE STOCK RENAME COLUMN fk_latest_trans_prod TO fk_trans_prod_from;

ALTER TABLE STOCK RENAME CONSTRAINT s_fk_latest_trans_in_ti_pk_trans_in TO s_fk_trans_in_from_ti_pk_trans_in;
ALTER TABLE STOCK RENAME CONSTRAINT s_fk_latest_trans_prod_tp_pk_trans_prod TO s_fk_trans_prod_from_tp_pk_trans_prod;