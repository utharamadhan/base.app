CREATE SEQUENCE research_keyword_pk_research_keyword_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE TABLE research_keyword
(
  pk_research_keyword bigint NOT NULL DEFAULT nextval('research_keyword_pk_research_keyword_seq'::regclass),
  keyword text,
  CONSTRAINT pk_research_keyword PRIMARY KEY (pk_research_keyword)
);