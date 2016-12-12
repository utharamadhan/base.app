insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time,usage)
values('PARTY_ROLE', 'PD', 'Produsen', 'Produsen', 2, 1, 'SYSTEM', now(), 'SYSTEM', now(),'TP');

DELETE FROM LOOKUP WHERE LOOKUP_GROUP = 'PARTY_ROLE_THIRD_PARTY';
DELETE FROM LOOKUP_GROUP WHERE LOOKUP_GROUP = 'PARTY_ROLE_THIRD_PARTY';
UPDATE LOOKUP SET USAGE = 'TP' WHERE LOOKUP_GROUP = 'PARTY_ROLE' AND CODE IN ('SP','CS');