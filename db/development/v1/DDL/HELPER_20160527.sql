CREATE TABLE helper
(
  pk_helper bigserial NOT NULL,
  code character varying(255) NOT NULL,
  content text,
  created_by character varying(200) NOT NULL,
  creation_time timestamp with time zone NOT NULL DEFAULT now(),
  modified_by character varying(200) NOT NULL,
  modification_time timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT pk_helper PRIMARY KEY (pk_helper)
);