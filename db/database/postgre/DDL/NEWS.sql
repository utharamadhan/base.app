CREATE SEQUENCE NEWS_PK_NEWS_SEQ
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE NEWS
(
  PK_NEWS BIGINT NOT NULL DEFAULT NEXTVAL('NEWS_PK_NEWS_SEQ'::REGCLASS),
  CODE CHARACTER VARYING(100) NOT NULL,
  NAME CHARACTER VARYING(100),
  CONTENT TEXT,
  CREATED_BY CHARACTER VARYING(200) NOT NULL,
  CREATION_TIME TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  MODIFIED_BY CHARACTER VARYING(200) NOT NULL,
  MODIFICATION_TIME TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  STATUS INTEGER NOT NULL DEFAULT 1,
  CONSTRAINT PK_NEWS PRIMARY KEY (PK_NEWS)
)
WITH (
  OIDS=FALSE
);