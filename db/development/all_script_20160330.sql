CREATE TABLE PRODUCT 
   (	PK_PRODUCT bigint, 
	FK_RMU bigint,
	CODE character varying(5),
	NAME character varying(255),  
	FK_LOOKUP_UOM bigint, 
	BUYING_PRICE numeric(24,4),
	SELLING_PRICE numeric(24,4),
	FK_LOOKUP_PRODUCT_TYPE bigint, 
	VALID character varying(1), 
	CREATED_BY character varying(255),
	MODIFIED_BY character varying(255),
	CREATION_TIME timestamp with time zone,
	MODIFICATION_TIME timestamp with time zone
   ) ;

create sequence PRODUCT_PK_PRODUCT_SEQ start 1;  
alter table PRODUCT alter column PK_PRODUCT set default nextval('PRODUCT_PK_PRODUCT_SEQ');