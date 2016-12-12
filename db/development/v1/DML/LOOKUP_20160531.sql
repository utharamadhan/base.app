DELETE FROM lookup WHERE lookup_group = 'FEE_TYPE' AND code = 'PROD';

insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('FEE_TYPE', 'PDRY', 'Pengolahan Pengeringan', 'Biaya-biaya di "Pengolahan -> Pengeringan"', 1, 1, 'SYSTEM', now(), 'SYSTEM', now());
insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('FEE_TYPE', 'PMIL', 'Pengolahan Penggilingan', 'Biaya-biaya di "Pengolahan -> Penggilingan"', 1, 1, 'SYSTEM', now(), 'SYSTEM', now());
