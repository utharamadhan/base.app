insert into lookup_group (lookup_group,group_descr, is_updateable, is_viewable)
values('PARTY_ROLE_THIRD_PARTY', 'Party Role Lookup of Third Party',false, true);

insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('PARTY_ROLE_THIRD_PARTY', 'SP', 'Pemasok', 'Supplier', 1, 1, 'SYSTEM', now(), 'SYSTEM', now());
insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('PARTY_ROLE_THIRD_PARTY', 'CS', 'Pelanggan', 'Customer', 2, 1, 'SYSTEM', now(), 'SYSTEM', now());