alter table COMPANY_CONTACT ADD CREATED_BY character varying(255);
alter table COMPANY_CONTACT ADD MODIFIED_BY character varying(255);
alter table COMPANY_CONTACT ADD CREATION_TIME timestamp with time zone;
alter table COMPANY_CONTACT ADD MODIFICATION_TIME timestamp with time zone;