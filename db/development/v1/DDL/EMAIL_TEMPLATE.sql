CREATE TABLE EMAIL_TEMPLATE
(
  PK_EMAIL_TEMPLATE bigserial NOT NULL,
  CODE character varying(255),
  TEMPLATE text,
  STATUS integer NOT NULL DEFAULT 1,
  created_by character varying(200) NOT NULL,
  creation_time timestamp with time zone NOT NULL DEFAULT now(),
  modified_by character varying(200) NOT NULL,
  modification_time timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT PK_EMAIL_TEMPLATE PRIMARY KEY (PK_EMAIL_TEMPLATE)
);