CREATE SEQUENCE HOUSING_INDEX_PK_HOUSING_INDEX_SEQ
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE HOUSING_INDEX
(
  PK_HOUSING_INDEX BIGINT NOT NULL DEFAULT NEXTVAL('HOUSING_INDEX_PK_HOUSING_INDEX_SEQ'::REGCLASS),
  TITLE CHARACTER VARYING(255) NOT NULL,
  FILE_URL TEXT,
  STATUS INTEGER NOT NULL DEFAULT 1,
  CREATED_BY CHARACTER VARYING(200) NOT NULL,
  CREATION_TIME TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  MODIFIED_BY CHARACTER VARYING(200) NOT NULL,
  MODIFICATION_TIME TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  CONSTRAINT PK_HOUSING_INDEX PRIMARY KEY (PK_HOUSING_INDEX)
)
WITH (
  OIDS=FALSE
);