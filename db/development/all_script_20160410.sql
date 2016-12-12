-- alter trans_buy to purchase_order (based on new requirement)
alter table trans_buy rename column fk_party_seller to fk_company_supplier;
drop view vw_stock_produksi;
drop view vw_stock_non_produksi;
alter table trans_buy drop column fk_stock;
alter table trans_buy add PO_NUMBER character varying(200);
alter table trans_buy add FK_LOOKUP_TERM_OF_PAYMENT bigint;
alter table trans_buy add FK_LOOKUP_CURRENCY bigint;
alter table trans_buy rename column volume to PO_QTY;
alter table trans_buy rename column UNIT_BUY_FEE to PO_SUBVALUE;
alter table trans_buy drop column fk_lookup_volume_unit;
alter table trans_buy add PO_VAT_PERCENT numeric(6,6);
alter table trans_buy add PO_VAT_VALUE numeric(24,4);
alter table trans_buy add FK_COMPANY_REFERENCE bigint;
alter table trans_buy add VALID character varying(1);
alter table trans_buy rename column TOTAL_BUY_FEE TO TOTAL_VALUE;
alter table trans_buy rename to PURCHASE_ORDER;
alter table PURCHASE_ORDER rename PK_TRANS_BUY TO PK_PURCHASE_ORDER;
alter sequence TRANS_BUY_PK_TRANS_BUY_SEQ rename to PURCHASE_ORDER_PK_PURCHASE_ORDER_SEQ;

-- alter trans_buy_other_fee to purchase_order_element (based on new requirement)
alter table TRANS_BUY_OTHER_FEE add SEQ_NO int;
alter table TRANS_BUY_OTHER_FEE add FK_PRODUCT bigint;
alter table TRANS_BUY_OTHER_FEE rename column FEE to PURCHASE_PRICE;
alter table TRANS_BUY_OTHER_FEE rename column DESCR to VALID;
alter table TRANS_BUY_OTHER_FEE rename to PURCHASE_ORDER_ELEMENT;
alter table PURCHASE_ORDER_ELEMENT rename column PK_TRANS_BUY_OTHER_FEE to PK_PURCHASE_ORDER_ELEMENT;
alter sequence TRANS_BUY_OTHER_FEE_PK_TRANS_BUY_OTHER_FEE_SEQ rename to PURCHASE_ORDER_ELEMENT_PK_PURCHASE_ORDER_ELEMENT_SEQ;