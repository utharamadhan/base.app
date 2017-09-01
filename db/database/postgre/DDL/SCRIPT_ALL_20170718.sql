CREATE OR REPLACE VIEW vw_learning_item AS 
SELECT row_number() over() as PK, C.PK_CATEGORY,C.PERMALINK AS CATEGORY_PERMALINK,LI.PK_LEARNING_ITEM, LI.TITLE, LI.PERMALINK AS LEARNING_PERMALINK, LI.IMAGE_URL, LI.IMAGE_THUMB_URL, LI.DATE_FROM, LI.DATE_TO, 
LI.fk_lookup_method, LI.fk_lookup_organizer, LI.fk_lookup_payment,
CASE 
	WHEN (DATE_FROM < CURRENT_TIMESTAMP) THEN 'P' 
	WHEN (DATE_FROM >= CURRENT_TIMESTAMP) THEN 'F' 
END AS PERIOD,LI.STATUS
FROM LEARNING_ITEM LI 
INNER JOIN LEARNING_ITEM_CATEGORY LIC ON LIC.FK_LEARNING_ITEM = LI.PK_LEARNING_ITEM 
INNER JOIN CATEGORY C ON C.PK_CATEGORY = LIC.FK_CATEGORY;

INSERT INTO pages(title, status, permalink, order_no, type, created_by, creation_time, modified_by, modification_time)
    VALUES ('LEARNING',2,'learning',1,'PRL','SYSTEM',CURRENT_TIMESTAMP,'SYSTEM',CURRENT_TIMESTAMP);
INSERT INTO pages(title, status, permalink, order_no, type, created_by, creation_time, modified_by, modification_time)
    VALUES ('ADVISORY',2,'advisory',2,'PRA','SYSTEM',CURRENT_TIMESTAMP,'SYSTEM',CURRENT_TIMESTAMP);
INSERT INTO pages(title, status, permalink, order_no, type, created_by, creation_time, modified_by, modification_time)
    VALUES ('RESEARCH & DEVELOPMENT',2,'research-development',3,'PRR','SYSTEM',CURRENT_TIMESTAMP,'SYSTEM',CURRENT_TIMESTAMP);
COMMIT;

ALTER TABLE PAGES ADD COLUMN IMAGE_THUMB_URL text;

ALTER TABLE LEARNING_ITEM ADD COLUMN CLOSING_DATE_REG timestamp with time zone;
ALTER TABLE LEARNING_ITEM ADD COLUMN LOCATION text;
ALTER TABLE LEARNING_ITEM ADD COLUMN ORGANIZER_DETAIL text;
ALTER TABLE LEARNING_ITEM ADD COLUMN PAYMENT_DESC text;
ALTER TABLE LEARNING_ITEM DROP COLUMN subtitle;
UPDATE LEARNING_ITEM SET PAYMENT_DESC = PAYMENT_DETAIL;
UPDATE LEARNING_ITEM SET PAYMENT_DETAIL = NULL;

CREATE SEQUENCE INTEGRATION_SCRIPT_PK_INTEGRATION_SCRIPT_SEQ
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE TABLE INTEGRATION_SCRIPT
(
  PK_INTEGRATION_SCRIPT BIGINT NOT NULL DEFAULT nextval('INTEGRATION_SCRIPT_PK_INTEGRATION_SCRIPT_SEQ'::regclass),
  TYPE INT NOT NULL,
  URL TEXT NOT NULL,
  POSITION INT NOT NULL,
  SCRIPT TEXT NOT NULL,
  STATUS INTEGER NOT NULL DEFAULT 1,
  CREATED_BY CHARACTER VARYING(200) NOT NULL,
  CREATION_TIME TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  MODIFIED_BY CHARACTER VARYING(200) NOT NULL,
  MODIFICATION_TIME TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  CONSTRAINT PK_INTEGRATION_SCRIPT PRIMARY KEY (PK_INTEGRATION_SCRIPT)
);

INSERT INTO app_function(pk_app_function,fk_app_function_parent, name, descr, access_page,is_active, user_type, order_no) VALUES (450,400,'Integration Script','INT_FE_INTEGRATION_SCRIPT','/do/fed/integrationScript/showList','t',1,5);
COMMIT;

UPDATE LOOKUP_GROUP SET LOOKUP_GROUP = 'FAQ_TYPE', GROUP_DESCR = 'FAQ_TYPE' WHERE LOOKUP_GROUP = 'FAQ_CATEGORY';
UPDATE LOOKUP SET LOOKUP_GROUP = 'FAQ_TYPE' WHERE LOOKUP_GROUP = 'FAQ_CATEGORY';
COMMIT;

UPDATE APP_FUNCTION SET NAME = 'FAQ Category', DESCR = 'INT_MT_FAQ_CATEGORY', ACCESS_PAGE = '/do/master/faqCategory/showList' WHERE PK_APP_FUNCTION = 330; 
INSERT INTO app_function(pk_app_function,fk_app_function_parent, name, descr, access_page,is_active, user_type, order_no) VALUES 
(340,300,'FAQ','INT_MT_FAQ','/do/master/faq/showList','t',1,4);
COMMIT;

UPDATE APP_FUNCTION SET NAME = 'FAQ Item', DESCR = 'INT_MT_FAQ_ITEM', ACCESS_PAGE = '/do/master/faqItem/showList' WHERE PK_APP_FUNCTION = 340;
COMMIT;

DROP TABLE faq;

CREATE TABLE faq
(
  pk_faq bigint NOT NULL DEFAULT nextval('faq_pk_faq_seq'::regclass),
  fk_category bigint,
  question text,
  answer text,
  status integer NOT NULL DEFAULT 1,
  created_by character varying(200) NOT NULL,
  creation_time timestamp with time zone NOT NULL DEFAULT now(),
  modified_by character varying(200) NOT NULL,
  modification_time timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT pk_faq PRIMARY KEY (pk_faq),
  CONSTRAINT faq_fk_category_fkey FOREIGN KEY (fk_category)
      REFERENCES category (pk_category) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

DELETE FROM LOOKUP WHERE LOOKUP_GROUP = 'FAQ_TYPE';
COMMIT;


DROP TABLE contact;
DROP SEQUENCE CONTACT_PK_CONTACT_SEQ;
CREATE SEQUENCE CONTACT_PK_CONTACT_SEQ
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE TABLE contact
(
  pk_contact bigint NOT NULL DEFAULT nextval('contact_pk_contact_seq'::regclass),
  fk_lookup_category_help bigint,
  fk_category bigint,
  fk_learning_item bigint,
  name character varying(200),
  telp character varying(50),
  email text,
  birth_place character varying(100),
  birth_date TIMESTAMP WITH TIME ZONE,
  job text,
  instansi text,
  message text,
  answer text,
  answered_by text,
  status int,
  created_by character varying(200) NOT NULL,
  creation_time timestamp with time zone NOT NULL DEFAULT now(),
  modified_by character varying(200) NOT NULL,
  modification_time timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT pk_contact PRIMARY KEY (pk_contact),
  CONSTRAINT contact_fk_lookup_category_help_fkey FOREIGN KEY (fk_lookup_category_help)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT contact_fk_category_fkey FOREIGN KEY (fk_category)
      REFERENCES category (pk_category) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

DELETE FROM LOOKUP WHERE LOOKUP_GROUP = 'CONTACT_TEMA';
DELETE FROM LOOKUP_GROUP WHERE LOOKUP_GROUP = 'CONTACT_TEMA';

ALTER TABLE CATEGORY ADD COLUMN IS_ITEMS_NEW_PAGE BOOLEAN DEFAULT FALSE;
ALTER TABLE CATEGORY ADD COLUMN IS_SHOW_FILTER BOOLEAN DEFAULT TRUE;

INSERT INTO LOOKUP_GROUP (LOOKUP_GROUP, GROUP_DESCR, IS_UPDATEABLE, IS_VIEWABLE)
VALUES ('LEARNING_DISPLAY', 'LEARNING_DISPLAY', 'f', 'f');
INSERT INTO LOOKUP (LOOKUP_GROUP, CODE, NAME, DESCR, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY) 
VALUES ('LEARNING_DISPLAY', 'FWS', 'Full screen without sidebar', 'Full screen without sidebar', 1, 1, 'SYSTEM', 'SYSTEM');
INSERT INTO LOOKUP (LOOKUP_GROUP, CODE, NAME, DESCR, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY) 
VALUES ('LEARNING_DISPLAY', 'FSR', 'Full screen + sidebar right in the description', 'Full screen + sidebar right in the description', 2, 1, 'SYSTEM', 'SYSTEM');
INSERT INTO LOOKUP (LOOKUP_GROUP, CODE, NAME, DESCR, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY) 
VALUES ('LEARNING_DISPLAY', 'NFSR', 'Not full screen + sidebar right', 'Not full screen + sidebar right', 3, 1, 'SYSTEM', 'SYSTEM');

ALTER TABLE LEARNING_ITEM ADD COLUMN SILABUS_DESC TEXT;
ALTER TABLE LEARNING_ITEM ADD COLUMN CONTACT_US_DESC TEXT;
ALTER TABLE LEARNING_ITEM ADD COLUMN FK_LOOKUP_LEARNING_DISPLAY bigint;
ALTER TABLE LEARNING_ITEM ADD CONSTRAINT learning_item_fk_lookup_learning_display_fkey FOREIGN KEY (FK_LOOKUP_LEARNING_DISPLAY)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

UPDATE LEARNING_ITEM SET FK_LOOKUP_LEARNING_DISPLAY = (SELECT PK_LOOKUP FROM LOOKUP WHERE LOOKUP_GROUP = 'LEARNING_DISPLAY' AND CODE = 'NFSR');

ALTER TABLE PARTY RENAME COLUMN TITLE TO AGENCY;

ALTER TABLE LEARNING_ITEM ADD COLUMN BROCHURE_URL TEXT;
ALTER TABLE CATEGORY ADD COLUMN DETAIL_LINK_IMAGE_URL TEXT;
ALTER TABLE LEARNING_ITEM ADD COLUMN type character varying(100) NOT NULL DEFAULT 'LG';

DELETE FROM APP_ROLE_FUNCTION WHERE FK_APP_FUNCTION IN (920,930);
DELETE FROM APP_FUNCTION WHERE PK_APP_FUNCTION IN (920,930);
INSERT INTO APP_FUNCTION (PK_APP_FUNCTION, FK_APP_FUNCTION_PARENT, NAME, DESCR, ACCESS_PAGE, IS_ACTIVE, USER_TYPE, ORDER_NO)
VALUES (920, 900, 'A. Item', 'INT_ADV_ITEM', '/do/advisory/item/showList', true, 1, 2);
INSERT INTO APP_FUNCTION (PK_APP_FUNCTION, FK_APP_FUNCTION_PARENT, NAME, DESCR, ACCESS_PAGE, IS_ACTIVE, USER_TYPE, ORDER_NO)
VALUES (930, 900, 'A. Contact', 'INT_ADV_CONTACT', '/do/advisory/contact/showList', true, 1, 3);
INSERT INTO APP_FUNCTION (PK_APP_FUNCTION, FK_APP_FUNCTION_PARENT, NAME, DESCR, ACCESS_PAGE, IS_ACTIVE, USER_TYPE, ORDER_NO)
VALUES (940, 900, 'A. Consulting', 'INT_ADV_CONSULTING', '/do/advisory/consulting/showList', true, 1, 4);

ALTER TABLE LEARNING_ITEM ADD COLUMN background_image_size int NOT NULL DEFAULT 1;

ALTER TABLE LEARNING_ITEM RENAME TO PROGRAM_ITEM;
ALTER TABLE LEARNING_ITEM_CATEGORY RENAME TO PROGRAM_ITEM_CATEGORY;
ALTER TABLE LEARNING_ITEM_IMAGE RENAME TO PROGRAM_ITEM_IMAGE;
ALTER TABLE LEARNING_ITEM_TEACHER RENAME TO PROGRAM_ITEM_TEACHER;

ALTER TABLE PROGRAM_ITEM RENAME COLUMN pk_learning_item TO pk_program_item;
ALTER TABLE PROGRAM_ITEM_CATEGORY RENAME COLUMN fk_learning_item TO fk_program_item;
ALTER TABLE PROGRAM_ITEM_IMAGE RENAME COLUMN pk_learning_item_image TO fk_learning_item_image;
ALTER TABLE PROGRAM_ITEM_IMAGE RENAME COLUMN fk_learning_item TO fk_program_item;
ALTER TABLE PROGRAM_ITEM_TEACHER RENAME COLUMN pk_learning_item_teacher TO pk_program_item_teacher;
ALTER TABLE PROGRAM_ITEM_TEACHER RENAME COLUMN fk_learning_item TO fk_program_item;

ALTER TABLE PROGRAM_ITEM RENAME CONSTRAINT pk_learning_item TO pk_program_item;
ALTER TABLE PROGRAM_ITEM RENAME CONSTRAINT learning_item_fk_lookup_learning_display_fkey TO program_item_fk_lookup_program_display_fkey;
ALTER TABLE PROGRAM_ITEM RENAME CONSTRAINT learning_item_fk_lookup_method_fkey TO program_item_fk_lookup_method_fkey;
ALTER TABLE PROGRAM_ITEM RENAME CONSTRAINT learning_item_fk_lookup_organizer_fkey TO program_item_fk_lookup_organizer_fkey;
ALTER TABLE PROGRAM_ITEM RENAME CONSTRAINT learning_item_fk_lookup_payment_fkey TO program_item_fk_lookup_payment_fkey;
ALTER TABLE PROGRAM_ITEM RENAME CONSTRAINT learning_item_permalink_key TO program_item_permalink_key;
ALTER TABLE PROGRAM_ITEM_CATEGORY RENAME CONSTRAINT learning_item_category_fk_category_fkey TO program_item_category_fk_category_fkey;
ALTER TABLE PROGRAM_ITEM_CATEGORY RENAME CONSTRAINT learning_item_category_fk_learning_item_fkey TO program_item_category_fk_program_item_fkey;
ALTER TABLE PROGRAM_ITEM_IMAGE RENAME COLUMN fk_learning_item_image TO pk_program_item_image;
ALTER TABLE PROGRAM_ITEM_IMAGE RENAME CONSTRAINT pk_learning_item_image TO pk_program_item_image;
ALTER TABLE PROGRAM_ITEM_IMAGE RENAME CONSTRAINT learning_item_image_fk_learning_item_fkey TO program_item_image_fk_program_item_fkey;
ALTER TABLE PROGRAM_ITEM_TEACHER RENAME CONSTRAINT pk_learning_item_teacher TO pk_program_item_teacher;
ALTER TABLE PROGRAM_ITEM_TEACHER RENAME CONSTRAINT learning_item_teacher_fk_learning_item_fkey TO program_item_teacher_fk_program_item_fkey;
ALTER TABLE PROGRAM_ITEM_TEACHER RENAME CONSTRAINT learning_item_teacher_fk_p_teacher_fkey TO program_item_teacher_fk_p_teacher_fkey;

ALTER TABLE PROGRAM_ITEM RENAME COLUMN fk_lookup_learning_display TO fk_lookup_program_display;

ALTER SEQUENCE learning_item_pk_learning_item_seq RENAME TO program_item_pk_program_item_seq;
ALTER SEQUENCE learning_item_image_pk_learning_item_image_seq RENAME TO program_item_image_pk_program_item_image_seq;
ALTER SEQUENCE learning_item_teacher_pk_learning_item_teacher_seq RENAME TO program_item_teacher_pk_program_item_teacher_seq;

UPDATE LOOKUP_GROUP SET LOOKUP_GROUP = 'PROGRAM_DISPLAY', GROUP_DESCR = 'PROGRAM_DISPLAY' WHERE LOOKUP_GROUP = 'LEARNING_DISPLAY';
UPDATE LOOKUP SET LOOKUP_GROUP = 'PROGRAM_DISPLAY' WHERE LOOKUP_GROUP = 'LEARNING_DISPLAY';
UPDATE LOOKUP_GROUP SET LOOKUP_GROUP = 'PROGRAM_METHOD', GROUP_DESCR = 'PROGRAM_METHOD' WHERE LOOKUP_GROUP = 'LEARNING_METHOD';
UPDATE LOOKUP SET LOOKUP_GROUP = 'PROGRAM_METHOD' WHERE LOOKUP_GROUP = 'LEARNING_METHOD';
UPDATE LOOKUP_GROUP SET LOOKUP_GROUP = 'PROGRAM_ORGANIZER', GROUP_DESCR = 'PROGRAM_ORGANIZER' WHERE LOOKUP_GROUP = 'LEARNING_ORGANIZER';
UPDATE LOOKUP SET LOOKUP_GROUP = 'PROGRAM_ORGANIZER' WHERE LOOKUP_GROUP = 'LEARNING_ORGANIZER';
UPDATE LOOKUP_GROUP SET LOOKUP_GROUP = 'PROGRAM_PAYMENT', GROUP_DESCR = 'PROGRAM_PAYMENT' WHERE LOOKUP_GROUP = 'LEARNING_PAYMENT';
UPDATE LOOKUP SET LOOKUP_GROUP = 'PROGRAM_PAYMENT' WHERE LOOKUP_GROUP = 'LEARNING_PAYMENT';

DROP VIEW vw_learning_item;

CREATE OR REPLACE VIEW vw_program_item AS 
 SELECT row_number() OVER () AS pk,
    c.pk_category,
    c.permalink AS category_permalink,
    li.pk_program_item,
    li.title,
    li.permalink AS program_permalink,
    li.image_url,
    li.image_thumb_url,
    li.date_from,
    li.date_to,
    li.fk_lookup_method,
    li.fk_lookup_organizer,
    li.fk_lookup_payment,
        CASE
            WHEN li.date_from < now() THEN 'P'::text
            WHEN li.date_from >= now() THEN 'F'::text
            ELSE NULL::text
        END AS period,
    li.status
   FROM program_item li
     JOIN program_item_category lic ON lic.fk_program_item = li.pk_program_item
     JOIN category c ON c.pk_category = lic.fk_category;
     
DROP TABLE research_theme;

ALTER TABLE PROGRAM_ITEM ADD COLUMN FACILITY_DESC text;

CREATE SEQUENCE PROGRAM_ITEM_MENU_PK_PROGRAM_ITEM_MENU_SEQ
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE TABLE PROGRAM_ITEM_MENU
(
  PK_PROGRAM_ITEM_MENU BIGINT NOT NULL DEFAULT nextval('PROGRAM_ITEM_MENU_PK_PROGRAM_ITEM_MENU_SEQ'::regclass),
  fk_program_item bigint NOT NULL,
  title text,
  fk_lookup_item_menu bigint,
  order_no integer,
  CONSTRAINT PK_PROGRAM_ITEM_MENU PRIMARY KEY (PK_PROGRAM_ITEM_MENU)
);

INSERT INTO LOOKUP_GROUP (LOOKUP_GROUP, GROUP_DESCR, IS_UPDATEABLE, IS_VIEWABLE)
VALUES ('ITEM_MENU', 'Item Menu', 'f', 'f');

INSERT INTO LOOKUP (LOOKUP_GROUP, CODE, NAME, DESCR, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY) 
VALUES ('ITEM_MENU', '1', 'Description', 'Description', 1, 1, 'SYSTEM', 'SYSTEM');
INSERT INTO LOOKUP (LOOKUP_GROUP, CODE, NAME, DESCR, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY) 
VALUES ('ITEM_MENU', '2', 'Admission Requirement', 'Admission RequirementProgram', 2, 1, 'SYSTEM', 'SYSTEM');
INSERT INTO LOOKUP (LOOKUP_GROUP, CODE, NAME, DESCR, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY) 
VALUES ('ITEM_MENU', '3', 'Silabus Program', 'Silabus Program', 3, 1, 'SYSTEM', 'SYSTEM');
INSERT INTO LOOKUP (LOOKUP_GROUP, CODE, NAME, DESCR, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY) 
VALUES ('ITEM_MENU', '4', 'Facility', 'Facility', 4, 1, 'SYSTEM', 'SYSTEM');
INSERT INTO LOOKUP (LOOKUP_GROUP, CODE, NAME, DESCR, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY) 
VALUES ('ITEM_MENU', '5', 'Payment Description', 'Payment Description', 5, 1, 'SYSTEM', 'SYSTEM');
INSERT INTO LOOKUP (LOOKUP_GROUP, CODE, NAME, DESCR, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY) 
VALUES ('ITEM_MENU', '6', 'Testimonial', 'Testimonial', 6, 1, 'SYSTEM', 'SYSTEM');
INSERT INTO LOOKUP (LOOKUP_GROUP, CODE, NAME, DESCR, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY) 
VALUES ('ITEM_MENU', '7', 'Teacher / Speaker', 'Teacher / Speaker', 7, 1, 'SYSTEM', 'SYSTEM');
INSERT INTO LOOKUP (LOOKUP_GROUP, CODE, NAME, DESCR, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY) 
VALUES ('ITEM_MENU', '8', 'Galeri', 'Galeri', 8, 1, 'SYSTEM', 'SYSTEM');
INSERT INTO LOOKUP (LOOKUP_GROUP, CODE, NAME, DESCR, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES ('ITEM_MENU', '9', 'Download Brochure', 'Download Brochure', 9, 1, 'SYSTEM', 'SYSTEM');
INSERT INTO LOOKUP (LOOKUP_GROUP, CODE, NAME, DESCR, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES ('ITEM_MENU', '10', 'Contact Us', 'Contact Us', 10, 1, 'SYSTEM', 'SYSTEM');

ALTER TABLE PARTY ADD COLUMN PERMALINK text;

ALTER TABLE APP_ROLE ADD COLUMN PERMALINK text;

CREATE OR REPLACE VIEW vw_user AS 
SELECT AU.PK_APP_USER, P.PK_PARTY, AR.PERMALINK AS ROLE_PERMALINK, P.PERMALINK, P.NAME, P.BASIC_PICTURE_URL FROM APP_USER AU 
INNER JOIN APP_USER_ROLE AUR ON AUR.FK_APP_USER = AU.PK_APP_USER
INNER JOIN APP_ROLE AR ON AR.PK_APP_ROLE = AUR.FK_APP_ROLE
INNER JOIN PARTY P ON P.PK_PARTY = AU.PK_APP_USER
WHERE AU.STATUS <> 2;
