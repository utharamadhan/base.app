CREATE TABLE faq
(
  pk_faq bigserial NOT NULL,
  question text NOT NULL,
  answer text,
  status integer DEFAULT 1,
  created_by character varying(200) NOT NULL,
  creation_time timestamp with time zone NOT NULL DEFAULT now(),
  modified_by character varying(200) NOT NULL,
  modification_time timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT pk_faq PRIMARY KEY (pk_faq)
);