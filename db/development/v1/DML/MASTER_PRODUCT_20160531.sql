insert into master_product(product_name,fk_lookup_uom,fk_lookup_item_type,status,created_by,creation_time)
values('Benang Jahit', (select pk_lookup from lookup where lookup_group = 'UOM' and code = 'PCS'), (select pk_lookup from lookup where lookup_group = 'ITEM_TYPE' and code = 'BHP'), 1, 'SYSTEM', now());
insert into master_product(product_name,fk_lookup_uom,fk_lookup_item_type,status,created_by,creation_time)
values('Solar/Premium', (select pk_lookup from lookup where lookup_group = 'UOM' and code = 'UNIT'), (select pk_lookup from lookup where lookup_group = 'ITEM_TYPE' and code = 'BHP'), 1, 'SYSTEM', now());
insert into master_product(product_name,fk_lookup_uom,fk_lookup_item_type,status,created_by,creation_time)
values('Gas', (select pk_lookup from lookup where lookup_group = 'UOM' and code = 'UNIT'), (select pk_lookup from lookup where lookup_group = 'ITEM_TYPE' and code = 'BHP'), 1, 'SYSTEM', now());
insert into master_product(product_name,fk_lookup_uom,fk_lookup_item_type,status,created_by,creation_time)
values('Karung/Goni', (select pk_lookup from lookup where lookup_group = 'UOM' and code = 'PCS'), (select pk_lookup from lookup where lookup_group = 'ITEM_TYPE' and code = 'BHP'), 1, 'SYSTEM', now());

insert into master_product(product_name,fk_lookup_uom,fk_lookup_item_type,status,created_by,creation_time)
values('Gabah Kering Panen', (select pk_lookup from lookup where lookup_group = 'UOM' and code = 'KG'), (select pk_lookup from lookup where lookup_group = 'ITEM_TYPE' and code = 'BB'), 1, 'SYSTEM', now());
insert into master_product(product_name,fk_lookup_uom,fk_lookup_item_type,status,created_by,creation_time)
values('Gabah Kering Giling', (select pk_lookup from lookup where lookup_group = 'UOM' and code = 'KG'), (select pk_lookup from lookup where lookup_group = 'ITEM_TYPE' and code = 'BB'), 1, 'SYSTEM', now());
insert into master_product(product_name,fk_lookup_uom,fk_lookup_item_type,status,created_by,creation_time)
values('Beras Asalan', (select pk_lookup from lookup where lookup_group = 'UOM' and code = 'KG'), (select pk_lookup from lookup where lookup_group = 'ITEM_TYPE' and code = 'BB'), 1, 'SYSTEM', now());
insert into master_product(product_name,fk_lookup_uom,fk_lookup_item_type,status,created_by,creation_time)
values('Beras Pecah Kulit', (select pk_lookup from lookup where lookup_group = 'UOM' and code = 'KG'), (select pk_lookup from lookup where lookup_group = 'ITEM_TYPE' and code = 'BB'), 1, 'SYSTEM', now());
insert into master_product(product_name,fk_lookup_uom,fk_lookup_item_type,status,created_by,creation_time)
values('Beras Putih', (select pk_lookup from lookup where lookup_group = 'UOM' and code = 'KG'), (select pk_lookup from lookup where lookup_group = 'ITEM_TYPE' and code = 'BJ'), 1, 'SYSTEM', now());
insert into master_product(product_name,fk_lookup_uom,fk_lookup_item_type,status,created_by,creation_time)
values('Sekam', (select pk_lookup from lookup where lookup_group = 'UOM' and code = 'KG'), (select pk_lookup from lookup where lookup_group = 'ITEM_TYPE' and code = 'BJ'), 1, 'SYSTEM', now());
insert into master_product(product_name,fk_lookup_uom,fk_lookup_item_type,status,created_by,creation_time)
values('Dedak', (select pk_lookup from lookup where lookup_group = 'UOM' and code = 'KG'), (select pk_lookup from lookup where lookup_group = 'ITEM_TYPE' and code = 'BJ'), 1, 'SYSTEM', now());
insert into master_product(product_name,fk_lookup_uom,fk_lookup_item_type,status,created_by,creation_time)
values('Menir', (select pk_lookup from lookup where lookup_group = 'UOM' and code = 'KG'), (select pk_lookup from lookup where lookup_group = 'ITEM_TYPE' and code = 'BJ'), 1, 'SYSTEM', now());
insert into master_product(product_name,fk_lookup_uom,fk_lookup_item_type,status,created_by,creation_time)
values('Bekatul', (select pk_lookup from lookup where lookup_group = 'UOM' and code = 'KG'), (select pk_lookup from lookup where lookup_group = 'ITEM_TYPE' and code = 'BJ'), 1, 'SYSTEM', now());