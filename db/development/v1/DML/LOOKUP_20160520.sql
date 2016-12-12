UPDATE LOOKUP SET USAGE = 'BBP' WHERE CODE = 'BB' AND LOOKUP_GROUP = 'ITEM_TYPE';
UPDATE LOOKUP SET USAGE = 'BBP' WHERE CODE = 'BJ' AND LOOKUP_GROUP = 'ITEM_TYPE';
UPDATE LOOKUP SET USAGE = 'BPP' WHERE CODE = 'BHP' AND LOOKUP_GROUP = 'ITEM_TYPE';
UPDATE LOOKUP SET USAGE = 'BPP' WHERE CODE = 'BKM' AND LOOKUP_GROUP = 'ITEM_TYPE';

insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('ACTOR_PRODUCTION', 'M', 'Mandiri', 'Mandiri', 1, 1, 'SYSTEM', now(), 'SYSTEM', now());
insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('ACTOR_PRODUCTION', 'TP', 'Third Party', 'Third Party', 1, 1, 'SYSTEM', now(), 'SYSTEM', now());

DELETE FROM LOOKUP WHERE LOOKUP_GROUP = 'FEE_TYPE' AND CODE = 'PDRY';
DELETE FROM LOOKUP WHERE LOOKUP_GROUP = 'FEE_TYPE' AND CODE = 'PMIL';
DELETE FROM LOOKUP WHERE LOOKUP_GROUP = 'ITEM_TYPE' AND CODE = 'BPP';

insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('FEE_TYPE', 'PROD', 'Produksi', 'Biaya-biaya di "Pengolahan"', 1, 1, 'SYSTEM', now(), 'SYSTEM', now());