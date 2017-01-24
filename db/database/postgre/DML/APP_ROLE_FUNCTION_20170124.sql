-- dashboard menu
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_DB_STATISTIC_CONTACT_US'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_DB_RESEARCH_MANAGEMENT_UPDATE'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_DB_ADVISORY_CONSULTING_NOTIF'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_DB_CONTACT_US_NOTIF'));

-- Front End Display Menu
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_FRONT_END'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_FE_MENU'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_FE_SLIDE_SHOW'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_FE_BACKGROUND_IMAGE'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_FE_FOOTER'));

-- About Us Menu
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_ABOUT_US'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_AU_COMMON_POST'));

-- Activity Menu
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_ACTIVITY'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_ACT_ENGAGEMENT'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_ACT_PROGRAM'));

-- Publication Menu
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_PUBLICATION'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_PUB_EBOOK'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_PUB_NEWS'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_PUB_EVENT'));

-- Learning Menu
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_LEARNING'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_LEARNING_PROGRAM'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_LEARNING_COURSE_TAG'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_LEARNING_COURSE'));

-- Research Menu
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_RESEARCH'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_RESEARCH_TOPIC'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_RESEARCH_MAINTENANCE'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_RESEARCH_MANAGEMENT'));

-- Advisory Menu
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_ADVISORY'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_ADVISORY_SUB_MENU'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_ADVISORY_COMMON_POST'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_ADVISORY_ISSUE_INSIGHT'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_ADVISORY_CONSULTING'));

-- Student Database
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_STUDENT_DATABASE'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_SD_MAINTENANCE'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_SD_UPDATE_LEARNING'));

-- Contact Us
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_CONTACT_US'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_CU_MAINTENANCE'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_CU_USER_CONTACT'));