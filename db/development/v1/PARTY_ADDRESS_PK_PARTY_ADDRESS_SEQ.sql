create sequence PARTY_ADDRESS_PK_PARTY_ADDRESS_SEQ start 1;  
alter table PARTY_ADDRESS alter column PK_PARTY_ADDRESS set default nextval('PARTY_ADDRESS_PK_PARTY_ADDRESS_SEQ');