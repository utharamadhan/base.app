UPDATE LOOKUP SET LOOKUP_GROUP = 'ACTOR_PRODUCTION' WHERE LOOKUP_GROUP = 'TIPE_PENGOLAHAN';

insert into lookup(lookup_group,fk_lookup_parent,code,name,descr,order_no,is_active,created_by,creation_time,modified_by,modification_time)
values('DRYING_METHOD',null,'DR','Dryer','Dryer',1,true,'SYSTEM',now(),'SYSTEM',now());
insert into lookup(lookup_group,fk_lookup_parent,code,name,descr,order_no,is_active,created_by,creation_time,modified_by,modification_time)
values('DRYING_METHOD',null,'MT','Matahari','Matahari',2,true,'SYSTEM',now(),'SYSTEM',now());