TRUNCATE HOUSING_INDEX;
DROP TABLE HOUSING_INDEX;
DROP SEQUENCE HOUSING_INDEX_PK_HOUSING_INDEX_SEQ;

CREATE SEQUENCE LINK_URL_PK_LINK_URL_SEQ
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE LINK_URL
(
  PK_LINK_URL BIGINT NOT NULL DEFAULT NEXTVAL('LINK_URL_PK_LINK_URL_SEQ'::REGCLASS),
  TYPE CHARACTER VARYING(3) NOT NULL,
  ORDER_NO INT,
  TITLE CHARACTER VARYING(255) NOT NULL,
  URL TEXT,
  STATUS INTEGER NOT NULL DEFAULT 1,
  CREATED_BY CHARACTER VARYING(200) NOT NULL,
  CREATION_TIME TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  MODIFIED_BY CHARACTER VARYING(200) NOT NULL,
  MODIFICATION_TIME TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  CONSTRAINT PK_LINK_URL PRIMARY KEY (PK_LINK_URL)
)
WITH (
  OIDS=FALSE
);

INSERT INTO APP_FUNCTION (PK_APP_FUNCTION, FK_APP_FUNCTION_PARENT, NAME, DESCR, ACCESS_PAGE, IS_ACTIVE, USER_TYPE, ORDER_NO)
VALUES (450, 400, 'Footer Link URL', 'INT_FED_FOOTER_LINK_URL', '/do/fed/footerLinkUrl/showList', true, 1, 5);
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_FED_FOOTER_LINK_URL'));