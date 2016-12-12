CREATE TABLE company_master_fee
(
	pk_company_master_fee bigserial NOT NULL,
	fk_company bigint NOT NULL,
	fk_lookup_fee_type bigint NOT NULL,
	descr character varying(200),
	unit_fee numeric(24,4),
	fk_lookup_uom bigint,
	total_fee numeric(24,4),
	status integer NOT NULL DEFAULT 1,
	created_by character varying(200) NOT NULL,
	creation_time timestamp with time zone NOT NULL DEFAULT now(),
	modified_by character varying(200),
	modification_time timestamp with time zone,
	CONSTRAINT pk_company_master_fee PRIMARY KEY (pk_company_master_fee),
	CONSTRAINT cmf_fk_company_c_pk_company FOREIGN KEY (fk_company)
      REFERENCES company (pk_company) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT cmf_fk_lookup_fee_type_l_pk_lookup FOREIGN KEY (fk_lookup_fee_type)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT cmf_fk_lookup_uom_l_pk_lookup FOREIGN KEY (fk_lookup_uom)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
