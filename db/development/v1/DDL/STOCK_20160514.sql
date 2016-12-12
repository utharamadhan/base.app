ALTER TABLE stock RENAME fk_company_machinery TO fk_company_warehouse;
ALTER TABLE stock DROP CONSTRAINT s_fk_company_machinery_cm_pk_company_machinery;
ALTER TABLE stock ADD CONSTRAINT s_fk_company_warehouse_cm_pk_company_warehouse FOREIGN KEY (fk_company_warehouse)
      REFERENCES company_warehouse (pk_company_warehouse) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE stock DROP COLUMN last_trans;
ALTER TABLE stock DROP COLUMN fk_trans_in;
ALTER TABLE stock DROP COLUMN fk_trans_prod;

DROP TABLE stock_history;

CREATE TABLE stock_history
(
  pk_stock_history bigserial NOT NULL,
  fk_stock bigint NOT NULL,
  fk_trans_in_item bigint,
  fk_trans_prod bigint,
  fk_trans_out_item bigint,
  CONSTRAINT pk_stock_history PRIMARY KEY (pk_stock_history),
  CONSTRAINT sh_fk_stock_s_pk_stock FOREIGN KEY (fk_stock)
      REFERENCES stock (pk_stock) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT sh_fk_trans_in_item_tii_pk_trans_in_item FOREIGN KEY (fk_trans_in_item)
      REFERENCES trans_in_item (pk_trans_in_item) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT sh_fk_trans_prod_tp_pk_trans_prod FOREIGN KEY (fk_trans_prod)
      REFERENCES trans_prod (pk_trans_prod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT sh_fk_trans_out_item_to_pk_trans_out_item FOREIGN KEY (fk_trans_out_item)
      REFERENCES trans_out_item (pk_trans_out_item) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
