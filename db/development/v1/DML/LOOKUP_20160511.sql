DELETE FROM LOOKUP WHERE LOOKUP_GROUP = 'FEE_TYPE' AND CODE = 'PROD';
insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('FEE_TYPE', 'PDRY', 'Produksi Pengeringan', 'Biaya-biaya di "Pengolahan -> Produksi -> Pengeringan"', 1, 1, 'SYSTEM', now(), 'SYSTEM', now());
insert into lookup(lookup_group,code,name_id,descr,order_no,status,created_by,creation_time,modified_by,modification_time)
values('FEE_TYPE', 'PMIL', 'Produksi Penggilingan', 'Biaya-biaya di "Pengolahan -> Produksi -> Penggilingan"', 2, 1, 'SYSTEM', now(), 'SYSTEM', now());