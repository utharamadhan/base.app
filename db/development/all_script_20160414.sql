ALTER TABLE PURCHASE_ORDER_ELEMENT ADD CREATED_BY character varying(255);
ALTER TABLE PURCHASE_ORDER_ELEMENT ADD MODIFIED_BY character varying(255);
ALTER TABLE PURCHASE_ORDER_ELEMENT ADD CREATION_TIME timestamp with time zone;
ALTER TABLE PURCHASE_ORDER_ELEMENT ADD MODIFICATION_TIME timestamp with time zone;

CREATE TABLE GOODS_RECEIPT_NOTE 
   (	PK_GOODS_RECEIPT_NOTE bigint, 
	FK_RMU bigint,
	GRN_NUMBER character varying(5),
	FK_LOOKUP_GRN_FROM bigint,
	GRN_PLAN_NO character varying(6),  
	FK_RMU_SUPPLIER bigint,
	FK_LOOKUP_UOM bigint, 
	FK_LOOKUP_CURRENCY bigint,
	GRN_QTY numeric(24,4),
	GRN_SUBVALUE numeric(24,4),
	GRN_VAT numeric(24,4),
	GRN_VAT_VALUE numeric(24,4),
	GRN_TOTAL_VALUE numeric(24,4),
	GRN_DATE timestamp with time zone,
	FK_PURCHASE_ORDER bigint,
	FK_COMPANY_REFERENCE bigint,
	GRN_IS_POSTED boolean,
	VALID character varying(1),
	CREATED_BY character varying(255),
	MODIFIED_BY character varying(255),
	CREATION_TIME timestamp with time zone,
	MODIFICATION_TIME timestamp with time zone
   ) ;

create sequence GOODS_RECEIPT_NOTE_PK_GOODS_RECEIPT_NOTE_SEQ start 1;  
alter table GOODS_RECEIPT_NOTE alter column PK_GOODS_RECEIPT_NOTE set default nextval('GOODS_RECEIPT_NOTE_PK_GOODS_RECEIPT_NOTE_SEQ');

CREATE TABLE GOODS_RECEIPT_NOTE_ELEMENT(
	PK_GOODS_RECEIPT_NOTE_ELEMENT bigint, 
	FK_GOODS_RECEIPT_NOE bigint,
	SEQ_NO bigint,
	FK_PRODUCT bigint,
	FK_LOOKUP_UOM bigint,
	RECEIVED_QUANTITY numeric(24,4),
	RECEIVED_PRICE numeric(24,4),
	RECEIVED_VALUE numeric(24,4),
	VALID character varying(1),
	CREATED_BY character varying(255),
	MODIFIED_BY character varying(255),
	CREATION_TIME timestamp with time zone,
	MODIFICATION_TIME timestamp with time zone
);

create sequence GOODS_RECEIPT_NOTE_ELEMENT_PK_GOODS_RECEIPT_NOTE_ELEMENT_SEQ start 1;  
alter table GOODS_RECEIPT_NOTE_ELEMENT alter column PK_GOODS_RECEIPT_NOTE_ELEMENT set default nextval('GOODS_RECEIPT_NOTE_ELEMENT_PK_GOODS_RECEIPT_NOTE_ELEMENT_SEQ');