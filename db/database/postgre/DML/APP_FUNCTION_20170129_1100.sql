UPDATE APP_FUNCTION SET access_page = '/do/advisory/consulting/showList' WHERE PK_APP_FUNCTION = 940;
UPDATE APP_FUNCTION SET access_page = '/do/course/groupCourse/showList' WHERE PK_APP_FUNCTION = 710;
UPDATE APP_FUNCTION SET NAME = 'Group Course' WHERE PK_APP_FUNCTION = 710;
COMMIT;