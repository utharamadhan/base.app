DROP TABLE stock_history;
DROP TABLE trans_prod_plan_machinery;
DROP TABLE trans_prod_plan;
DROP TABLE trans_prod_fee;
DROP TABLE trans_prod_machinery;
DROP TABLE trans_prod CASCADE;

DROP TABLE trans_out_fee;
DROP TABLE trans_out_item;
DROP TABLE trans_out;

DROP TABLE stock;

CREATE TABLE stock
(
  pk_stock bigserial NOT NULL,
  fk_company_warehouse bigint NOT NULL,
  fk_lookup_item_type bigint NOT NULL,
  fk_company_product bigint NOT NULL,
  volume numeric(24,4),
  fk_lookup_uom bigint,
  volume_in_kg numeric(24,4),
  suspense_volume_in_kg numeric(24,4),
  remaining_volume_in_kg numeric(24,4),
  hpp numeric(24,4),
  status integer NOT NULL DEFAULT 1,
  created_by character varying(200) NOT NULL,
  creation_time timestamp with time zone NOT NULL DEFAULT now(),
  modified_by character varying(200) NOT NULL,
  modification_time timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT pk_stock PRIMARY KEY (pk_stock),
  CONSTRAINT s_fk_company_product_pk_company_product FOREIGN KEY (fk_company_product)
      REFERENCES company_product (pk_company_product) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT s_fk_company_warehouse_cm_pk_company_warehouse FOREIGN KEY (fk_company_warehouse)
      REFERENCES company_warehouse (pk_company_warehouse) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT s_fk_lookup_item_type_l_pk_lookup FOREIGN KEY (fk_lookup_item_type)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT s_fk_lookup_uom_l_pk_lookup FOREIGN KEY (fk_lookup_uom)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE trans_prod
(
  pk_trans_prod bigserial NOT NULL,
  fk_company bigint NOT NULL,
  prod_type character varying(3) NOT NULL,
  fk_lookup_actor_prod bigint,
  fk_company_product_from bigint NOT NULL,
  fk_company_product_to bigint NOT NULL,
  fk_stock_from bigint NOT NULL,
  fk_stock_to bigint NOT NULL,
  plan_name character varying(200) NOT NULL,
  volume numeric(24,4),
  fk_lookup_uom bigint,
  volume_in_kg numeric(24,4),
  hpp numeric(24,4),
  prod_date_from date NOT NULL,
  prod_date_to date NOT NULL,
  total_fee numeric(24,4),
  status integer NOT NULL DEFAULT 2,
  created_by character varying(200) NOT NULL,
  creation_time timestamp with time zone NOT NULL DEFAULT now(),
  modified_by character varying(200) NOT NULL,
  modification_time timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT pk_trans_prod PRIMARY KEY (pk_trans_prod),
  CONSTRAINT tp_fk_company_c_pk_company FOREIGN KEY (fk_company)
      REFERENCES company (pk_company) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tp_fk_lookup_actor_prod_l_pk_lookup FOREIGN KEY (fk_lookup_actor_prod)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tp_fk_lookup_uom_l_pk_lookup FOREIGN KEY (fk_lookup_uom)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tp_fk_company_product_from_pk_company_product FOREIGN KEY (fk_company_product_from)
      REFERENCES company_product (pk_company_product) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tp_fk_company_product_to_pk_company_product FOREIGN KEY (fk_company_product_to)
      REFERENCES company_product (pk_company_product) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tp_fk_stock_from_s_pk_stock FOREIGN KEY (fk_stock_from)
      REFERENCES stock (pk_stock) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tp_fk_stock_to_s_pk_stock FOREIGN KEY (fk_stock_to)
      REFERENCES stock (pk_stock) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE trans_prod_fee
(
  pk_trans_prod_fee bigserial NOT NULL,
  fk_trans_prod bigint NOT NULL,
  descr character varying(200),
  fee numeric(24,4),
  CONSTRAINT pk_trans_prod_fee PRIMARY KEY (pk_trans_prod_fee),
  CONSTRAINT tpf_fk_trans_prod_rp_pk_trans_prod FOREIGN KEY (fk_trans_prod)
      REFERENCES trans_prod (pk_trans_prod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE trans_prod_machinery
(
  pk_trans_prod_machinery bigserial NOT NULL,
  fk_trans_prod bigint NOT NULL,
  fk_company_machinery bigint NOT NULL,
  order_no integer,
  CONSTRAINT pk_trans_prod_machinery PRIMARY KEY (pk_trans_prod_machinery),
  CONSTRAINT tpm_fk_company_machinery_cm_pk_company_machinery FOREIGN KEY (fk_company_machinery)
      REFERENCES company_machinery (pk_company_machinery) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tpm_fk_trans_tp_pk_trans_prod FOREIGN KEY (fk_trans_prod)
      REFERENCES trans_prod (pk_trans_prod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE trans_out
(
  pk_trans_out bigserial NOT NULL,
  fk_company bigint NOT NULL,
  source_type character varying(3) NOT NULL,
  fk_lookup_item_type bigint,
  out_no character varying(15) NOT NULL,
  fk_party_third_party bigint NOT NULL,
  fk_c_lookup_top bigint,
  out_date date NOT NULL DEFAULT now(),
  main_fee numeric(24,4),
  total_out_fee numeric(24,4),
  received_date date,
  received_message character varying(200),
  status integer NOT NULL DEFAULT 1,
  created_by character varying(200) NOT NULL,
  creation_time timestamp with time zone NOT NULL DEFAULT now(),
  modified_by character varying(200),
  modification_time timestamp with time zone,
  CONSTRAINT pk_trans_out PRIMARY KEY (pk_trans_out),
  CONSTRAINT to_fk_c_lookup_top FOREIGN KEY (fk_c_lookup_top)
      REFERENCES company_lookup (pk_company_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT to_fk_company_c_pk_company FOREIGN KEY (fk_company)
      REFERENCES company (pk_company) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT to_fk_lookup_item_type_l_pk_lookup FOREIGN KEY (fk_lookup_item_type)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT to_fk_party_third_party_p_pk_party FOREIGN KEY (fk_party_third_party)
      REFERENCES party (pk_party) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE trans_out_fee
(
  pk_trans_out_fee bigserial NOT NULL,
  fk_trans_out bigint NOT NULL,
  descr character varying(200),
  fee numeric(24,4),
  CONSTRAINT pk_trans_out_fee PRIMARY KEY (pk_trans_out_fee),
  CONSTRAINT tof_fk_trans_out_to_pk_trans_out FOREIGN KEY (fk_trans_out)
      REFERENCES trans_out (pk_trans_out) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE trans_out_item
(
  pk_trans_out_item bigserial NOT NULL,
  fk_trans_out bigint NOT NULL,
  fk_stock bigint,
  volume numeric(24,4),
  volume_in_kg numeric(24,4),
  fk_lookup_uom bigint,
  fee numeric(24,4),
  total_fee numeric(24,4),
  CONSTRAINT pk_trans_out_item PRIMARY KEY (pk_trans_out_item),
  CONSTRAINT toi_fk_lookup_uom_l_pk_lookup FOREIGN KEY (fk_lookup_uom)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT toi_fk_stock_s_pk_stock FOREIGN KEY (fk_stock)
      REFERENCES stock (pk_stock) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT toi_fk_trans_out_to_pk_trans_out FOREIGN KEY (fk_trans_out)
      REFERENCES trans_out (pk_trans_out) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

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
  CONSTRAINT sh_fk_trans_out_item_to_pk_trans_out_item FOREIGN KEY (fk_trans_out_item)
      REFERENCES trans_out_item (pk_trans_out_item) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT sh_fk_trans_prod_tp_pk_trans_prod FOREIGN KEY (fk_trans_prod)
      REFERENCES trans_prod (pk_trans_prod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
