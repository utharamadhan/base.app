DROP TABLE HOUSING_INDEX_PROVINCE;
DELETE FROM LOOKUP_GROUP WHERE LOOKUP_GROUP = 'PROVINCE';
DELETE FROM LOOKUP WHERE LOOKUP_GROUP = 'PROVINCE';
DROP SEQUENCE HOUSING_INDEX_PROVINCE_PK_HOUSING_INDEX_PROVINCE_SEQ;
COMMIT;

ALTER TABLE HOUSING_INDEX RENAME COLUMN file_url TO link_url;