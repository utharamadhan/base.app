ALTER TABLE TESTIMONIAL RENAME COLUMN title TO job_title;

ALTER TABLE NEWS ADD COLUMN IMAGE_THUMB_URL text;
ALTER TABLE NEWS DROP COLUMN FK_LOOKUP_NEWS_STATUS;