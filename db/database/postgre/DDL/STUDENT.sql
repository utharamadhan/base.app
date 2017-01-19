CREATE SEQUENCE STUDENT_PK_STUDENT_SEQ
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE STUDENT
(
  PK_STUDENT BIGINT NOT NULL DEFAULT NEXTVAL('STUDENT_PK_STUDENT_SEQ'::REGCLASS),
  NAME CHARACTER VARYING(100) NOT NULL,
  BIRTH_DATE TIMESTAMP WITH TIME ZONE,
  BIRTH_PLACE CHARACTER VARYING(100) NOT NULL,
  FK_LOOKUP_GENDER BIGINT NOT NULL REFERENCES LOOKUP(PK_LOOKUP),
  PHONE_NUMBER CHARACTER VARYING(100),
  EMAIL CHARACTER VARYING(100),
  ADDRESS TEXT,
  FK_LOOKUP_STUDENT_STATUS BIGINT NOT NULL REFERENCES LOOKUP(PK_LOOKUP),
  COMPANY CHARACTER VARYING(100),
  COM_POSITION CHARACTER VARYING(100),
  COM_CITY CHARACTER VARYING(100),
  COM_DESCRIPTION TEXT,
  SCHOOL CHARACTER VARYING(100),
  SC_DATES_ATTENDED TIMESTAMP WITH TIME ZONE,
  SC_DEGREE CHARACTER VARYING(100),
  SC_FIELD_OF_STUDY CHARACTER VARYING(100),
  SC_GRADE CHARACTER VARYING(100),
  SC_ACTIVITIES_SOCIETIES CHARACTER VARYING(100),
  SC_DESCRIPTION CHARACTER VARYING(100),
  CREATED_BY CHARACTER VARYING(200) NOT NULL,
  CREATION_TIME TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  MODIFIED_BY CHARACTER VARYING(200) NOT NULL,
  MODIFICATION_TIME TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  STATUS INTEGER NOT NULL DEFAULT 1,
  CONSTRAINT PK_STUDENT PRIMARY KEY (PK_STUDENT)
)
WITH (
  OIDS=FALSE
);