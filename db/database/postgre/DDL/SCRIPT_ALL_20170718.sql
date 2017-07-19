CREATE OR REPLACE VIEW vw_learning_item AS 
SELECT row_number() over() as PK, C.PK_CATEGORY,C.PERMALINK AS CATEGORY_PERMALINK,LI.PK_LEARNING_ITEM, LI.TITLE, LI.PERMALINK AS LEARNING_PERMALINK, LI.IMAGE_URL, LI.IMAGE_THUMB_URL, LI.DATE_FROM, LI.DATE_TO, 
LI.fk_lookup_method, LI.fk_lookup_organizer, LI.fk_lookup_payment,LI.STATUS FROM LEARNING_ITEM LI 
INNER JOIN LEARNING_ITEM_CATEGORY LIC ON LIC.FK_LEARNING_ITEM = LI.PK_LEARNING_ITEM 
INNER JOIN CATEGORY C ON C.PK_CATEGORY = LIC.FK_CATEGORY;