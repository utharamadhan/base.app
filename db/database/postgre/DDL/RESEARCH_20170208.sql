DROP TABLE research_budgeting;
DROP SEQUENCE research_budgeting_pk_research_budgeting_seq;
DROP TABLE research_file;
DROP SEQUENCE research_file_pk_research_file_seq;
DROP TABLE research_goal_target;
DROP SEQUENCE research_goal_target_pk_research_goal_target_seq;
DROP TABLE research_memo;
DROP SEQUENCE research_memo_pk_research_memo_seq;
DROP TABLE research_research_topic;
DROP SEQUENCE research_rs_topic_pk_research_research_topic_seq;
DROP TABLE research_researcher;
DROP SEQUENCE research_researcher_pk_research_researcher_seq;
DROP TABLE research_time_planning;
DROP SEQUENCE research_tm_plan_pk_research_time_planning_seq;
DROP TABLE research_topic;
DROP SEQUENCE research_topic_pk_research_topic_seq;
DROP TABLE research;
DROP SEQUENCE research_pk_research_seq;
DROP SEQUENCE research_report_pk_research_report_seq;

CREATE SEQUENCE research_theme_pk_research_theme_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE TABLE research_theme
(
  pk_research_theme bigint NOT NULL DEFAULT nextval('research_theme_pk_research_theme_seq'::regclass),
  title character varying(255),
  description text,
  status integer NOT NULL DEFAULT 1,
  created_by character varying(200) NOT NULL,
  creation_time timestamp with time zone NOT NULL DEFAULT now(),
  modified_by character varying(200) NOT NULL,
  modification_time timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT pk_research_theme PRIMARY KEY (pk_research_theme)
);

CREATE SEQUENCE research_pk_research_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE research(
	pk_research bigint NOT NULL DEFAULT nextval('research_pk_research_seq'::regclass),
	is_internal BOOLEAN NOT NULL DEFAULT TRUE,
	fk_research_theme bigint,
	title character varying(255) NOT NULL,
	subtitle text,
	image_url text,
	description text,
	principle_permit_file text,
	principle_permit_description text,
	procurement_file text,
	procurement_description text,
	work_order_file text,
	work_order_description text,
	vendor text,
	project_value numeric(24,4),
	status integer NOT NULL DEFAULT 1,
	created_by character varying(200) NOT NULL,
	creation_time timestamp with time zone NOT NULL DEFAULT now(),
	modified_by character varying(200) NOT NULL,
	modification_time timestamp with time zone NOT NULL DEFAULT now(),
	CONSTRAINT pk_research PRIMARY KEY (pk_research),
	CONSTRAINT research_fk_research_theme_fkey FOREIGN KEY (fk_research_theme)
      REFERENCES research_theme (pk_research_theme) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE SEQUENCE research_officer_pk_research_officer_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE TABLE research_officer(
	pk_research_officer bigint NOT NULL DEFAULT nextval('research_officer_pk_research_officer_seq'::regclass),
	fk_research bigint NOT NULL,
	officer_name character varying(255) NOT NULL,
	officer_position text,
	fk_party_officer bigint DEFAULT NULL,
	CONSTRAINT pk_research_officer PRIMARY KEY (pk_research_officer),
	CONSTRAINT research_officer_fk_research_fkey FOREIGN KEY (fk_research)
      REFERENCES research (pk_research) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT research_officer_fk_p_officer_fkey FOREIGN KEY (fk_party_officer)
      REFERENCES party (pk_party) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE SEQUENCE research_implementation_pk_research_implementation_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE research_implementation(
	pk_research_implementation bigint NOT NULL DEFAULT nextval('research_implementation_pk_research_implementation_seq'::regclass),
	fk_research bigint NOT NULL,
	step_no integer NOT NULL DEFAULT 1,
	output_desc text,
	output_file text,
	executor_name character varying(255) NOT NULL,
	fk_party_executor bigint DEFAULT NULL,
	deadline_date timestamp with time zone,
	status integer NOT NULL DEFAULT 1,
	created_by character varying(200) NOT NULL,
	creation_time timestamp with time zone NOT NULL DEFAULT now(),
	modified_by character varying(200) NOT NULL,
	modification_time timestamp with time zone NOT NULL DEFAULT now(),
	CONSTRAINT pk_research_implementation PRIMARY KEY (pk_research_implementation),
	CONSTRAINT research_implementation_fk_research_fkey FOREIGN KEY (fk_research)
      REFERENCES research (pk_research) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT research_officer_fk_p_executor_fkey FOREIGN KEY (fk_party_executor)
      REFERENCES party (pk_party) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE SEQUENCE research_result_pk_research_result_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE research_result(
	pk_research_result bigint NOT NULL DEFAULT nextval('research_result_pk_research_result_seq'::regclass),
	fk_research bigint NOT NULL,
	result_title character varying(255) NOT NULL,
	result_desc text,
	created_by character varying(200) NOT NULL,
	creation_time timestamp with time zone NOT NULL DEFAULT now(),
	modified_by character varying(200) NOT NULL,
	modification_time timestamp with time zone NOT NULL DEFAULT now(),
	CONSTRAINT pk_research_result PRIMARY KEY (pk_research_result),
	CONSTRAINT research_result_fk_research_fkey FOREIGN KEY (fk_research)
      REFERENCES research (pk_research) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE SEQUENCE research_result_file_pk_research_result_file_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE research_result_file(
	pk_research_result_file bigint NOT NULL DEFAULT nextval('research_result_file_pk_research_result_file_seq'::regclass),
	fk_research_result bigint NOT NULL,
	result_file text,
	CONSTRAINT pk_research_result_file PRIMARY KEY (pk_research_result_file),
	CONSTRAINT research_result_file_fk_r_result_fkey FOREIGN KEY (fk_research_result)
      REFERENCES research_result (pk_research_result) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);