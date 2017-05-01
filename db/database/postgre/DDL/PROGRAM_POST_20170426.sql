ALTER TABLE PROGRAM_POST 
ADD COLUMN publish_date timestamp with time zone,
ADD COLUMN excerpt text,
ADD COLUMN image_url text;