INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_ADMINISTRATOR'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_APP_ROLE'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_APP_USER'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_BUSINESS_SETTING'));
INSERT INTO APP_ROLE_FUNCTION (FK_APP_ROLE, FK_APP_FUNCTION)
VALUES ((SELECT PK_APP_ROLE FROM APP_ROLE WHERE CODE = 'SA'), (SELECT PK_APP_FUNCTION FROM APP_FUNCTION WHERE DESCR = 'INT_REFERENCE'));