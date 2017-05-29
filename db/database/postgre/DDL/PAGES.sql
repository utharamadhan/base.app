CREATE SEQUENCE PAGES_PK_PAGES_SEQ
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE PAGES
(
  pk_pages bigint NOT NULL DEFAULT nextval('PAGES_PK_PAGES_SEQ'::regclass),
  title character varying(100),
  content text,
  status integer NOT NULL DEFAULT 1,
  publish_date timestamp with time zone,
  image_url text,
  permalink text,
  order_no integer,
  type character varying(3),
  created_by character varying(200) NOT NULL,
  creation_time timestamp with time zone NOT NULL DEFAULT now(),
  modified_by character varying(200) NOT NULL,
  modification_time timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT pk_pages PRIMARY KEY (pk_pages)
);

INSERT INTO PAGES(title, content, status, order_no, type, publish_date, created_by, modified_by) SELECT title, content, status, order_no, 'AU', creation_time, 'SYSTEM', 'SYSTEM' FROM common_post ORDER BY pk_common_post ASC;
COMMIT;

DROP TABLE common_post;
DROP SEQUENCE common_post_pk_common_post_seq;

COMMIT;