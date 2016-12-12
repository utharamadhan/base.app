CREATE TABLE master_product
(
  pk_master_product bigserial NOT NULL,
  product_name character varying(255),
  fk_lookup_uom bigint,
  fk_lookup_item_type bigint,
  status integer NOT NULL DEFAULT 1,
  created_by character varying(200) NOT NULL,
  creation_time timestamp with time zone NOT NULL DEFAULT now(),
  modified_by character varying(200),
  modification_time timestamp with time zone,
  CONSTRAINT pk_master_product PRIMARY KEY (pk_master_product),
  CONSTRAINT mp_fk_lookup_uom_l_pk_lookup FOREIGN KEY (fk_lookup_uom)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT mp_fk_lookup_item_type_l_pk_lookup FOREIGN KEY (fk_lookup_item_type)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

ALTER TABLE APP_USER DROP CONSTRAINT app_user_unique_email;
ALTER TABLE company_lookup ALTER COLUMN code DROP NOT NULL;
