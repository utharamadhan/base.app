ALTER TABLE ARTICLE ADD COLUMN FK_CATEGORY bigint;

ALTER TABLE ARTICLE ADD CONSTRAINT AR_FK_CATEGORY_CAT_PK_CATEGORY FOREIGN KEY (FK_CATEGORY) REFERENCES CATEGORY (PK_CATEGORY) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;