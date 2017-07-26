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