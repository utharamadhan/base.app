ALTER TABLE NEWS ADD COLUMN permalink text;
ALTER TABLE ENGAGEMENT ADD COLUMN permalink text;
ALTER TABLE PROGRAM_POST ADD COLUMN permalink text;
ALTER TABLE DIGITAL_BOOK ADD COLUMN permalink text;
ALTER TABLE EVENT ADD COLUMN permalink text;

ALTER TABLE NEWS ADD UNIQUE(permalink);
ALTER TABLE ENGAGEMENT ADD UNIQUE(permalink);
ALTER TABLE PROGRAM_POST ADD UNIQUE(permalink);
ALTER TABLE DIGITAL_BOOK ADD UNIQUE(permalink);
ALTER TABLE EVENT ADD UNIQUE(permalink);

CREATE UNIQUE INDEX i_news_permalink ON news (permalink);
CREATE UNIQUE INDEX i_engagement_permalink ON engagement (permalink);
CREATE UNIQUE INDEX i_program_post_permalink ON program_post (permalink);
CREATE UNIQUE INDEX i_digital_book_permalink ON digital_book (permalink);
CREATE UNIQUE INDEX i_event_permalink ON event (permalink);