CREATE TABLE master_machinery
(
  pk_master_machinery bigserial NOT NULL,
  machine_name character varying(45) NOT NULL,
  fk_lookup_machine_model bigint,
  machine_capacity numeric(24,4),
  fk_lookup_machine_weighting bigint,
  fk_lookup_machine_capacity_uom bigint,
  fk_lookup_machine_power_source bigint,
  status integer NOT NULL DEFAULT 1,
  created_by character varying(200) NOT NULL,
  creation_time timestamp with time zone NOT NULL DEFAULT now(),
  modified_by character varying(200),
  modification_time timestamp with time zone,
  CONSTRAINT pk_master_machinery PRIMARY KEY (pk_master_machinery),
  CONSTRAINT mm_fk_lookup_machine_model_l_pk_lookup FOREIGN KEY (fk_lookup_machine_model)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT mm_fk_lookup_machine_weighting_l_pk_lookup FOREIGN KEY (fk_lookup_machine_weighting)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT mm_fk_lookup_machine_capacity_uom_l_pk_lookup FOREIGN KEY (fk_lookup_machine_capacity_uom)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT mm_fk_lookup_machine_power_source_l_pk_lookup FOREIGN KEY (fk_lookup_machine_power_source)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);