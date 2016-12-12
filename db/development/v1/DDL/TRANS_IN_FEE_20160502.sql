CREATE TABLE trans_in_fee
(
  pk_trans_in_fee bigserial NOT NULL,
  fk_trans_in bigint NOT NULL,
  descr character varying(200),
  fee numeric(24,4),
  CONSTRAINT pk_trans_in_fee PRIMARY KEY (pk_trans_in_fee),
  CONSTRAINT tif_fk_trans_in_ti_pk_trans_in FOREIGN KEY (fk_trans_in)
      REFERENCES trans_in (pk_trans_in) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);