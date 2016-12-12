create sequence PARTY_COMPANY_PK_PARTY_COMPANY_SEQ start 1;  
alter table PARTY_COMPANY alter column PK_PARTY_COMPANY set default nextval('PARTY_COMPANY_PK_PARTY_COMPANY_SEQ');