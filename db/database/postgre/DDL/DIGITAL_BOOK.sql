CREATE SEQUENCE DIGITAL_BOOK_PK_DIGITAL_BOOK_SEQ
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE DIGITAL_BOOK
(
  PK_DIGITAL_BOOK BIGINT NOT NULL DEFAULT NEXTVAL('DIGITAL_BOOK_PK_DIGITAL_BOOK_SEQ'::REGCLASS),
  CODE CHARACTER VARYING(100),
  TITLE CHARACTER VARYING(100),
  DESCRIPTION TEXT,
  LINK CHARACTER VARYING(100),
  CREATED_BY CHARACTER VARYING(200) NOT NULL,
  CREATION_TIME TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  MODIFIED_BY CHARACTER VARYING(200) NOT NULL,
  MODIFICATION_TIME TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  STATUS INTEGER NOT NULL DEFAULT 1,
  CONSTRAINT PK_DIGITAL_BOOK PRIMARY KEY (PK_DIGITAL_BOOK)
)
WITH (
  OIDS=FALSE
);