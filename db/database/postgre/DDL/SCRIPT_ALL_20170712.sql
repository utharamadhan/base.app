ALTER TABLE course DROP CONSTRAINT course_fk_group_course_fkey;
ALTER TABLE course DROP COLUMN fk_group_course;
DROP TABLE gc_basic_info;
DROP TABLE group_course;
DROP TABLE COURSE CASCADE;
DROP SEQUENCE course_pk_course_seq;

CREATE SEQUENCE LEARNING_ITEM_PK_LEARNING_ITEM_SEQ
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE TABLE learning_item
(
  pk_learning_item bigint NOT NULL DEFAULT nextval('LEARNING_ITEM_PK_LEARNING_ITEM_SEQ'::regclass),
  code character varying(100) NOT NULL,
  title character varying(255) NOT NULL,
  permalink text,
  subtitle text,
  image_url text,
  image_thumb_url text,
  image_background_url text,
  date_from timestamp with time zone,
  date_to timestamp with time zone,
  fk_lookup_method bigint,
  fk_lookup_organizer bigint,
  fk_lookup_payment bigint,
  description text,
  is_certification boolean,
  admission_requirement text,
  payment_detail text,
  status integer NOT NULL DEFAULT 1,
  created_by character varying(200) NOT NULL,
  creation_time timestamp with time zone NOT NULL DEFAULT now(),
  modified_by character varying(200) NOT NULL,
  modification_time timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT pk_learning_item PRIMARY KEY (pk_learning_item),
  CONSTRAINT learning_item_permalink_key UNIQUE (permalink),
  CONSTRAINT learning_item_fk_lookup_method_fkey FOREIGN KEY (fk_lookup_method)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT learning_item_fk_lookup_organizer_fkey FOREIGN KEY (fk_lookup_organizer)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT learning_item_fk_lookup_payment_fkey FOREIGN KEY (fk_lookup_payment)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE learning_item_category
(
  fk_learning_item bigint NOT NULL,
  fk_category bigint NOT NULL,
  CONSTRAINT learning_item_category_fk_learning_item_fkey FOREIGN KEY (fk_learning_item)
      REFERENCES learning_item (pk_learning_item) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT learning_item_category_fk_category_fkey FOREIGN KEY (fk_category)
      REFERENCES category (pk_category) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE SEQUENCE learning_item_teacher_pk_learning_item_teacher_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE TABLE learning_item_teacher
(
  pk_learning_item_teacher bigint NOT NULL DEFAULT nextval('learning_item_teacher_pk_learning_item_teacher_seq'::regclass),
  fk_learning_item bigint NOT NULL,
  teacher_name character varying(255) NOT NULL,
  fk_party_teacher bigint,
  CONSTRAINT pk_learning_item_teacher PRIMARY KEY (pk_learning_item_teacher),
  CONSTRAINT learning_item_teacher_fk_p_teacher_fkey FOREIGN KEY (fk_party_teacher)
      REFERENCES party (pk_party) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT learning_item_teacher_fk_learning_item_fkey FOREIGN KEY (fk_learning_item)
      REFERENCES learning_item (pk_learning_item) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE SEQUENCE learning_item_image_pk_learning_item_image_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE learning_item_image
(
  pk_learning_item_image bigint NOT NULL DEFAULT nextval('learning_item_image_pk_learning_item_image_seq'::regclass),
  fk_learning_item bigint NOT NULL,
  image_url text,
  image_thumb_url text,
  CONSTRAINT pk_learning_item_image PRIMARY KEY (pk_learning_item_image),
  CONSTRAINT learning_item_image_fk_learning_item_fkey FOREIGN KEY (fk_learning_item)
      REFERENCES learning_item (pk_learning_item) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);