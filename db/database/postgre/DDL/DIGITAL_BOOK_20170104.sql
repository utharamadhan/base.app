ALTER TABLE DIGITAL_BOOK ADD COVER_IMAGE_URL TEXT;
ALTER TABLE DIGITAL_BOOK ADD FILE_URL TEXT;
ALTER TABLE DIGITAL_BOOK DROP COLUMN CODE;
ALTER TABLE DIGITAL_BOOK DROP COLUMN LINK;