CREATE TABLE trans_in
(
  pk_trans_in bigserial NOT NULL,
  fk_company bigint NOT NULL,
  source_type character varying(3) NOT NULL,
  fk_lookup_item_type bigint,
  in_no character varying(15) NOT NULL,
  fk_party_third_party bigint NOT NULL,
  fk_c_lookup_tof bigint,
  in_date date NOT NULL DEFAULT now(),
  total_fee numeric(24,4),
  total_other_fee numeric(24,4),
  status integer NOT NULL DEFAULT 1,
  created_by character varying(200) NOT NULL,
  creation_time timestamp with time zone NOT NULL DEFAULT now(),
  modified_by character varying(200),
  modification_time timestamp with time zone,
  CONSTRAINT pk_trans_in PRIMARY KEY (pk_trans_in),
  CONSTRAINT ti_fk_company_c_pk_company FOREIGN KEY (fk_company)
      REFERENCES company (pk_company) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT ti_fk_lookup_item_type_l_pk_lookup FOREIGN KEY (fk_lookup_item_type)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT ti_fk_party_third_party_p_pk_party FOREIGN KEY (fk_party_third_party)
      REFERENCES party (pk_party) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,	  
  CONSTRAINT ti_fk_c_lookup_tof FOREIGN KEY (fk_c_lookup_tof)
      REFERENCES company_lookup (pk_company_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
