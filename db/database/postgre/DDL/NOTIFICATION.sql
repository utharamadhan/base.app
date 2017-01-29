CREATE SEQUENCE NOTIFICATION_PK_NOTIFICATION_SEQ
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE NOTIFICATION
(
  PK_NOTIFICATION BIGINT NOT NULL DEFAULT NEXTVAL('NOTIFICATION_PK_NOTIFICATION_SEQ'::REGCLASS),
  FK_LOOKUP_ACTION_TYPE BIGINT NOT NULL REFERENCES LOOKUP(PK_LOOKUP),
  IS_READ boolean default false,
  EMAIL_FROM CHARACTER VARYING(200),
  NAME_FROM CHARACTER VARYING(200),
  FK_MAINTENANCE BIGINT NOT NULL,
  ACTION_DATE TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  READ_BY CHARACTER VARYING(200),  
  READ_TIME TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
  CONSTRAINT PK_NOTIFICATION PRIMARY KEY (PK_NOTIFICATION)
)
WITH (
  OIDS=FALSE
);