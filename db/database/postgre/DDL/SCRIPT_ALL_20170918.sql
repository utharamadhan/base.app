ALTER TABLE PROGRAM_ITEM_TEACHER ADD COLUMN ORDER_NO integer;
ALTER TABLE PROGRAM_ITEM ADD COLUMN IS_TEACHER_IN_PROFILE_DISPLAY BOOLEAN;
UPDATE PROGRAM_ITEM SET IS_TEACHER_IN_PROFILE_DISPLAY = 'f';
ALTER TABLE PROGRAM_ITEM_TEACHER ALTER COLUMN teacher_name DROP NOT NULL;
DROP TABLE program_item_testimonial;
CREATE SEQUENCE program_item_testimonial_pk_program_item_testimonial_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
  
CREATE TABLE program_item_testimonial
(
  pk_program_item_testimonial bigint NOT NULL DEFAULT nextval('program_item_testimonial_pk_program_item_testimonial_seq'::regclass),
  fk_program_item bigint NOT NULL,
  fk_testimonial bigint NOT NULL,
  order_no integer,
  CONSTRAINT pk_program_item_testimonial PRIMARY KEY (pk_program_item_testimonial),
  CONSTRAINT program_item_testimonial_fk_program_item_fkey FOREIGN KEY (fk_program_item)
      REFERENCES program_item (pk_program_item) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT program_item_testimonial_fk_testimonial_fkey FOREIGN KEY (fk_testimonial)
      REFERENCES testimonial (pk_testimonial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
