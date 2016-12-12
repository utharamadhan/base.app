CREATE TABLE master_fee
(
	pk_master_fee bigserial NOT NULL,
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
	CONSTRAINT pk_master_fee PRIMARY KEY (pk_master_fee),
	CONSTRAINT mf_fk_lookup_fee_type_l_pk_lookup FOREIGN KEY (fk_lookup_fee_type)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT mf_fk_lookup_uom_l_pk_lookup FOREIGN KEY (fk_lookup_uom)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
