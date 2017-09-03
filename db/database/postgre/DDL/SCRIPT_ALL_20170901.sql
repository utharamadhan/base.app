ALTER TABLE APP_FUNCTION ADD COLUMN IS_MENU boolean DEFAULT false;
UPDATE APP_FUNCTION SET IS_MENU = true;
UPDATE APP_FUNCTION SET IS_MENU = false WHERE FK_APP_FUNCTION_PARENT = 100;

ALTER TABLE app_user DROP CONSTRAINT app_user_unique_email;

DROP TABLE home_setting;
DROP SEQUENCE setting_pk_home_setting_seq;

CREATE SEQUENCE SETTING_PK_SETTING_SEQ
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE SETTING
(
  PK_SETTING BIGINT NOT NULL DEFAULT NEXTVAL('SETTING_PK_SETTING_SEQ'::REGCLASS),
  TYPE INTEGER,
  DATA_FROM character varying(100),
  LABEL1 TEXT,
  LABEL2 TEXT,
  VALUE TEXT,
  ORDER_NO INTEGER,
  STATUS INTEGER NOT NULL DEFAULT 1,
  CREATED_BY CHARACTER VARYING(200) NOT NULL,
  CREATION_TIME TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  MODIFIED_BY CHARACTER VARYING(200) NOT NULL,
  MODIFICATION_TIME TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
  CONSTRAINT PK_SETTING PRIMARY KEY (PK_SETTING)
)
WITH (
  OIDS=FALSE
);

INSERT INTO SETTING(TYPE, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (1, NULL, NULL, NULL, 1, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (2, 'SEMUA INFORMASI PEMBIAYAAN PERUMAHAN ADA DISINI', 'Ikuti 4 Langkah Berikut Ini', NULL, 1, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (3, '/Site', NULL, '<span>Akses Website<br>Housing Finance<br>Center</span>', 1, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (3, '#', NULL, '<span>Browser<br>Publikasi<br>Kami</span>', 2, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (3, 'page/main-program', NULL, '<span>Pilih<br>Program<br>Kami</span>', 3, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (3, 'page/contact', NULL, '<span>Butuh Bantuan?<br>Hubungi Layanan<br>Pelanggan</span>', 4, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (4, 'SIAPAKAH KAMI?', NULL, NULL, 1, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (5, 'BTN HOUSING FINANCE CENTER', NULL, '<p>Sebagai leader pembiayaan perumahan di Indonesia, BTN HFC menjadi motor penggerak dan pusat referensi property di Indonesia. Sesuai dengan tujuan didirikannya BTN HFC akan menjadi pengelola pusat pembelajaraan perbankan dan riset  perumahan yang profesional dan terkemuka di Indonesia. BTN HFC akan menjadi sumber inspirasi para pelaku bisnis di bidang pembiayaan perumahan. Menjawab kebutuhan bisnis pembiayaan perumahan apakah itu dari dunia perbankan ataupun para pelaku pembangunan perumahan, BTN HFC adalah tempatnya. Ini akan menjadi pusat menjawab kebutuhan masalah perumahan di Indonesia.</p>', 1, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (5, 'Tujuan', NULL, '<p>Housing Finance Center BTN yang selaras dengan tujuan didirikannya, yakni :</p><ul><li>Pusat Pembelajaran perbankan dan riset perumahan yang profesional terkemuka di Indonesia,</li><li>Menjadi sumber inspirasi para pelaku bisnis di bidang pembiayaan perumahan,</li><li>Menjawab kebutuhan bisnis pembiayaan perumahan baik dunia perbankan maupun pengembang perumahan.</li></ul></div>', 2, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (5, 'Program', NULL, '<p>Program kerja di bidang  Learning :</p><ol><li>Foundation Property Developer : diperuntukkan bagi developer kecil atau pemula. Selain itu program ini dirancang bagi masyarakat umum yang memiliki passion untuk tumbuh menjadi pengembang.</li><li>Professional  Developer : diperuntukkan bagi  pengembang skala menengah, yang telah membangun di beberapa titik lokasi sekaligus.</li><li>Executive  Property Developer : Diperuntukkan bagi pengembang skala besar, yang berorientasi pada pembangunan suatu kawasan atau kota mandiri.</li></ol></div>', 3, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (5, 'Kerjasama', NULL, '<ol><li>MoU BTN - HDFC India</li><li>MoU BTN dengan Pengembang 21-22 April 2015</li><li>MoU BTN - SBM ITB 24 Oktober 2015</li><li>MoU BTN - IAEI 3 Maret 2016</li><li>PKS BTN dengan UNDIP Semarang, 20 April 2017</li><li>MoU BTN dengan Balai Pustaka, 2 Mei 2017</li></ol>', 4, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (6, 'Capital', NULL, NULL, 1, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (7, 'Land & Environment', NULL, NULL, 1, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (8, 'Legal', NULL, NULL, 1, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (9, 'Skills', NULL, NULL, 1, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (10, 'BERITA TERKINI', NULL, NULL, 1, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (11, 'APA KATA MEREKA?', NULL, NULL, 1, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (12, NULL, NULL, '<blockquote><span>Anda ingin menjadi pengembang sukses ?.<br>Kami siap membantu anda !</span></blockquote><br><h2>HOUSING FINANCE CENTER</h2><hr><ul><li>Memberikan pelayanan unggul, inovatif dan terintegrasi yang bisa diandalkan</li><li>Fokus pada bidang riset, konsultasi dan edukasi</li><li>Menjadi pusat riset, edukasi dan konsultasi terkait pembiayaan perumahan yang terdepan dengan pelayanan <i>CEPAT</i></li></ul></div>', 1, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, DATA_FROM, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (13, 'TOP_BERANDA', 'Beranda', NULL, NULL, 1, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, DATA_FROM, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (13, 'TOP_TENTANG_KAMI', 'Tentang Kami', NULL, NULL, 2, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, DATA_FROM, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (13, 'TOP_PUBLIKASI', 'Publikasi', NULL, NULL, 3, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, DATA_FROM, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (13, 'TOP_LAYANAN', 'Layanan', NULL, NULL, 4, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, DATA_FROM, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (13, 'TOP_GALERI', 'Galeri', NULL, NULL, 5, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, DATA_FROM, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (13, 'TOP_KONTAK_KAMI','Kontak Kami', NULL, NULL, 6, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, DATA_FROM, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (14, 'FOOTER_ABOUT', 'Tentang Website', NULL, NULL, 1, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, DATA_FROM, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (14, 'FOOTER_OTHER_SITE', 'Situs Lain', NULL, NULL, 2, 2, 'SYSTEM', 'SYSTEM');
INSERT INTO SETTING(TYPE, DATA_FROM, LABEL1, LABEL2, VALUE, ORDER_NO, STATUS, CREATED_BY, MODIFIED_BY)
VALUES (14, 'FOOTER_FOLLOW_US', 'Ikuti Kami', NULL, NULL, 3, 2, 'SYSTEM', 'SYSTEM');

UPDATE APP_FUNCTION SET ACCESS_PAGE = '/do/fed/homeSetting/showEdit' WHERE PK_APP_FUNCTION = 420;
UPDATE APP_FUNCTION SET ACCESS_PAGE = '/do/fed/menuSetting/showEdit' WHERE PK_APP_FUNCTION = 410;