TRUNCATE APP_ROLE_FUNCTION RESTART IDENTITY CASCADE;
TRUNCATE APP_FUNCTION RESTART IDENTITY CASCADE;
TRUNCATE APP_ROLE RESTART IDENTITY CASCADE;
TRUNCATE APP_USER_ROLE RESTART IDENTITY CASCADE;

insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1, null, 'Internal Function', 'INT_INTERNAL_FUNCTION', null, true, 1, 1);

insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (100,1, 'Dashboard', 'INT_DASHBOARD', '/do/dashboard', true, 1, 1);

insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (200,1, 'Security Admin', 'INT_SEC_ADMIN', null, true, 1, 2);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (210,200, 'User', 'INT_SEC_ADMIN_USER', '/do/user/showList?', true, 1, 1);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (220,200, 'User Role', 'INT_SEC_ADMIN_USER_ROLE', '/do/role/showList?', true, 1, 2);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (230,200, 'Rice Milling Unit', 'INT_SEC_ADMIN_RMU', '/do/rmu/showList?', true, 1, 3);

insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (300,1, 'System Admin', 'INT_SYS_ADMIN', null, true, 1, 3);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (310,300, 'Business Reference', 'INT_SYS_ADMIN_BUS_REFERENCE', '/do/lookupGroup/showList?', true, 1, 1);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (320,300, 'Error Log', 'INT_SYS_ADMIN_ERROR_LOG', '/do/error/showList?', true, 1, 2);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (330,300, 'System Parameter', 'INT_SYS_ADMIN_SYS_PARAMETER', '/do/systemParameter/showList?', true, 1, 3);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (340,300, 'Email Maintenance', 'INT_SYS_ADMIN_EMAIL_MAINTENANCE', '/do/emailMaintenance/showList?', true, 1, 4);

insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (400,1, 'Frontend Content', 'INT_FRONTEND', null, true, 1, 4);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (410,400, 'Slideshow', 'INT_FRONTEND_SLIDESHOW', '/do/slideshow/showList?', true, 1, 1);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (420,400, 'Team', 'INT_FRONTEND_TEAM', '/do/team/showList?', true, 1, 2);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (430,400, 'About', 'INT_FRONTEND_ABOUT', '/do/about/showList?', true, 1, 3);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (440,400, 'Contact', 'INT_FRONTEND_CONTACT', '/do/contact/showList?', true, 1, 4);

insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (500,1, 'Report', 'INT_REPORT', null, true, 1, 5);

insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (2, null, 'External Function', 'EX_EXTERNAL_FUNCTION', null, true, 2, 1);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1100, 2, 'Dashboard', 'EX_DASHBOARD', '/do/dashboard', true, 2, 1);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1110, 1100, 'Info User', 'EX_DASHBOARD_INFO_USER', null, true, 2, 1);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1120, 1100, 'Last User Activity', 'EX_DASHBOARD_LAST_USER_ACTIVITY', null, true, 2, 2);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1130, 1100, 'Total Stock In Hand', 'EX_DASHBOARD_TOTAL_STOCK_IN_HAND', null, true, 2, 3);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1140, 1100, 'Hasil Produksi (Pie Chart)', 'EX_DASHBOARD_RES_PROD_PIE_CHART', null, true, 2, 4);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1150, 1100, 'Hasil Produksi (Table)', 'EX_DASHBOARD_RES_PROD_TABLE', null, true, 2, 5);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1160, 1100, 'Semua - Penggilingan Padi Terdaftar', 'EX_DASHBOARD_ALL_RMU', null, true, 2, 6);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1170, 1100, 'Semua - Stock Beras (Grafik)', 'EX_DASHBOARD_ALL_STOCK_BERAS_GRAFIK', null, true, 2, 7);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1180, 1100, 'Semua - 10 Penggilingan Padi Produksi Terbesar', 'EX_DASHBOARD_ALL_10_RMU_BEST', null, true, 2, 8);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1190, 1100, 'Semua - 10 Penggilingan Padi Produksi Terkecil', 'EX_DASHBOARD_ALL_10_RMU_WORST', null, true, 2, 9);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1200, 1100, 'Semua - Informasi Harga', 'EX_DASHBOARD_ALL_PRICE', null, true, 2, 10);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1400, 2, 'Pembelian', 'EX_BUY', null, true, 2, 2);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1500, 2, 'Pengolahan', 'EX_PROCESS', null, true, 2, 3);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1600, 2, 'Penjualan', 'EX_SELL', null, true, 2, 4);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1700, 2, 'Stock', 'EX_STOCK', null, true, 2, 5);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1710, 1700, 'Stock Produksi', 'EX_STOCK_PROD', null, true, 2, 1);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1720, 1700, 'Stock Non Produksi', 'EX_STOCK_NON_PROD', null, true, 2, 2);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1800, 2, 'Mitra', 'EX_MITRA', null, true, 2, 6);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (1900, 2, 'Report', 'EX_SUPER_REPORT', null, true, 2, 7);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (2000, 2, 'Report', 'EX_REPORT', null, true, 2, 7);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (2100, 2, 'Setting', 'EX_SETTING', null, true, 2, 8);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (2110, 2, 'Setting Parameter', 'EX_SETTING_PARAMETER', null, true, 2, 1);
insert into app_function (pk_app_function, fk_app_function_parent, name, descr, access_page, is_active, user_type, order_no)
values (2120, 2, 'Setting Reference', 'EX_SETTING_REFERENCE', null, true, 2, 2);

insert into app_role (code, name, user_type, created_by, creation_time, modified_by, modification_time)
values ('SA', 'Super Admin',1,'SYSTEM',current_timestamp,'SYSTEM',current_timestamp);
insert into app_role (code, name, user_type, created_by, creation_time, modified_by, modification_time)
values ('HM', 'Head Member',2,'SYSTEM',current_timestamp,'SYSTEM',current_timestamp);
insert into app_role (code, name, user_type, created_by, creation_time, modified_by, modification_time)
values ('MT', 'Transaction Member',2,'SYSTEM',current_timestamp,'SYSTEM',current_timestamp);

insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'SA'),(select pk_app_function from app_function where descr = 'INT_INTERNAL_FUNCTION'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'SA'),(select pk_app_function from app_function where descr = 'INT_DASHBOARD'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'SA'),(select pk_app_function from app_function where descr = 'INT_SEC_ADMIN'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'SA'),(select pk_app_function from app_function where descr = 'INT_SEC_ADMIN_USER'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'SA'),(select pk_app_function from app_function where descr = 'INT_SEC_ADMIN_USER_ROLE'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'SA'),(select pk_app_function from app_function where descr = 'INT_SEC_ADMIN_RMU'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'SA'),(select pk_app_function from app_function where descr = 'INT_SYS_ADMIN'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'SA'),(select pk_app_function from app_function where descr = 'INT_SYS_ADMIN_BUS_REFERENCE'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'SA'),(select pk_app_function from app_function where descr = 'INT_SYS_ADMIN_ERROR_LOG'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'SA'),(select pk_app_function from app_function where descr = 'INT_SYS_ADMIN_SYS_PARAMETER'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'SA'),(select pk_app_function from app_function where descr = 'INT_SYS_ADMIN_EMAIL_MAINTENANCE'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'SA'),(select pk_app_function from app_function where descr = 'INT_FRONTEND'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'SA'),(select pk_app_function from app_function where descr = 'INT_FRONTEND_SLIDESHOW'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'SA'),(select pk_app_function from app_function where descr = 'INT_FRONTEND_TEAM'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'SA'),(select pk_app_function from app_function where descr = 'INT_FRONTEND_ABOUT'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'SA'),(select pk_app_function from app_function where descr = 'INT_FRONTEND_CONTACT'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'SA'),(select pk_app_function from app_function where descr = 'INT_REPORT'));

insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'HM'),(select pk_app_function from app_function where descr = 'EX_EXTERNAL_FUNCTION'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'HM'),(select pk_app_function from app_function where descr = 'EX_DASHBOARD'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'HM'),(select pk_app_function from app_function where descr = 'EX_DASHBOARD_ALL_RMU'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'HM'),(select pk_app_function from app_function where descr = 'EX_DASHBOARD_ALL_STOCK_BERAS_GRAFIK'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'HM'),(select pk_app_function from app_function where descr = 'EX_DASHBOARD_ALL_10_RMU_BEST'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'HM'),(select pk_app_function from app_function where descr = 'EX_DASHBOARD_ALL_10_RMU_WORST'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'HM'),(select pk_app_function from app_function where descr = 'EX_DASHBOARD_ALL_PRICE'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'HM'),(select pk_app_function from app_function where descr = 'EX_SETTING'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'HM'),(select pk_app_function from app_function where descr = 'EX_SETTING_PARAMETER'));

insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'MT'),(select pk_app_function from app_function where descr = 'EX_EXTERNAL_FUNCTION'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'MT'),(select pk_app_function from app_function where descr = 'EX_DASHBOARD'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'MT'),(select pk_app_function from app_function where descr = 'EX_DASHBOARD_INFO_USER'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'MT'),(select pk_app_function from app_function where descr = 'EX_DASHBOARD_LAST_USER_ACTIVITY'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'MT'),(select pk_app_function from app_function where descr = 'EX_DASHBOARD_TOTAL_STOCK_IN_HAND'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'MT'),(select pk_app_function from app_function where descr = 'EX_DASHBOARD_RES_PROD_PIE_CHART'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'MT'),(select pk_app_function from app_function where descr = 'EX_DASHBOARD_RES_PROD_TABLE'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'MT'),(select pk_app_function from app_function where descr = 'EX_BUY'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'MT'),(select pk_app_function from app_function where descr = 'EX_PROCESS'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'MT'),(select pk_app_function from app_function where descr = 'EX_SELL'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'MT'),(select pk_app_function from app_function where descr = 'EX_STOCK'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'MT'),(select pk_app_function from app_function where descr = 'EX_STOCK_PROD'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'MT'),(select pk_app_function from app_function where descr = 'EX_STOCK_NON_PROD'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'MT'),(select pk_app_function from app_function where descr = 'EX_MITRA'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'MT'),(select pk_app_function from app_function where descr = 'EX_REPORT'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'MT'),(select pk_app_function from app_function where descr = 'EX_SETTING'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'MT'),(select pk_app_function from app_function where descr = 'EX_SETTING_PARAMETER'));
insert into app_role_function (fk_app_role, fk_app_function)
values ((select pk_app_role from app_role where code = 'MT'),(select pk_app_function from app_function where descr = 'EX_SETTING_REFERENCE'));

insert into app_user_role (fk_app_user, fk_app_role)
values (1,(select pk_app_role from app_role where code = 'SA'));

alter table app_parameter drop column modified_by;
alter table app_parameter drop column modification_time;
alter table app_parameter add column created_by character varying(200) NOT NULL;
alter table app_parameter add column creation_time time with time zone NOT NULL DEFAULT now();
alter table app_parameter add column modified_by character varying(200) NOT NULL;
alter table app_parameter add column modification_time time with time zone NOT NULL DEFAULT now();

ALTER TABLE runtime_user_login ALTER COLUMN access_info TYPE text;

TRUNCATE trans_production_other_fee RESTART IDENTITY CASCADE;
TRUNCATE trans_production_from RESTART IDENTITY CASCADE;
TRUNCATE trans_production_to RESTART IDENTITY CASCADE;
TRUNCATE trans_production RESTART IDENTITY CASCADE;
TRUNCATE trans_buy_other_fee RESTART IDENTITY CASCADE;
TRUNCATE trans_buy RESTART IDENTITY CASCADE;
TRUNCATE stock RESTART IDENTITY CASCADE;

ALTER TABLE trans_buy ALTER COLUMN unit_buy_fee TYPE numeric(18,4);
ALTER TABLE trans_buy ALTER COLUMN total_buy_fee TYPE numeric(18,4);
ALTER TABLE trans_buy_other_fee ALTER COLUMN fee TYPE numeric(18,4);
ALTER TABLE trans_production_other_fee ALTER COLUMN fee TYPE numeric(18,4);
ALTER TABLE trans_sell ALTER COLUMN total_fee TYPE numeric(18,4);
ALTER TABLE trans_sell_detail ALTER COLUMN unit_sell_fee TYPE numeric(18,4);
ALTER TABLE trans_sell_detail ALTER COLUMN total_sell_fee TYPE numeric(18,4);
ALTER TABLE trans_sell_other_fee ALTER COLUMN fee TYPE numeric(18,4);
ALTER TABLE stock ALTER COLUMN hpp TYPE numeric(18,4);

ALTER TABLE trans_production ADD COLUMN production_date date NOT NULL DEFAULT now();

insert into lookup(lookup_group,fk_lookup_parent,code,name,descr,order_no,is_active,created_by,creation_time,modified_by,modification_time)
values('PARTY_ROLE',null,'BY','Buyer','Buyer',4,true,'SYSTEM',now(),'SYSTEM',now());

create or replace view vw_stock_produksi as
	select pk_stock, name, transaction_date, volume, hpp, satuan, pk_rmu from
        ((select pk_stock as pk_stock, lo.name as name, tb.buying_date as transaction_date, 
		s.volume as volume, s.hpp as hpp, loUnit.name as satuan, r.pk_rmu as pk_rmu from trans_buy tb
        inner join stock s on tb.fk_stock = s.pk_stock
	inner join lookup lo 
			join lookup loParent on lo.fk_lookup_parent = loParent.pk_lookup and loParent.code = 'BBP'
		on lo.pk_lookup = s.fk_lookup_stock_type
	inner join lookup loUnit on loUnit.pk_lookup = tb.fk_lookup_volume_unit
	inner join rmu r on r.pk_rmu = tb.fk_rmu)
	union all
	(select pk_stock as pk_stock, lo.name as name, tp.production_date as transaction_date, s.volume as volume, s.hpp as hpp, loUnit.name as satuan, r.pk_rmu as pk_rmu from trans_production_to tpt
	inner join stock s on tpt.fk_stock_to = s.pk_stock
	inner join lookup lo
			join lookup loParent on lo.fk_lookup_parent = loParent.pk_lookup and loParent.code = 'BBP'
		on lo.pk_lookup = s.fk_lookup_stock_type
	inner join lookup loUnit on loUnit.pk_lookup = tpt.fk_lookup_volume_unit
	inner join trans_production tp on tp.pk_trans_production = tpt.fk_trans_production
	inner join rmu r on r.pk_rmu = tp.fk_rmu)) a;

	create or replace view vw_stock_non_produksi as
	select pk_stock, name, transaction_date, volume, hpp, satuan from
        ((select pk_stock as pk_stock, lo.name as name, tb.buying_date as transaction_date, 
		s.volume as volume, s.hpp as hpp, loUnit.name as satuan, r.pk_rmu as pk_rmu from trans_buy tb
        inner join stock s on tb.fk_stock = s.pk_stock
	inner join lookup lo 
			join lookup loParent on lo.fk_lookup_parent = loParent.pk_lookup and loParent.code = 'BBNP'
		on lo.pk_lookup = s.fk_lookup_stock_type
	inner join lookup loUnit on loUnit.pk_lookup = tb.fk_lookup_volume_unit
	inner join rmu r on r.pk_rmu = tb.fk_rmu)
	union all
	(select pk_stock as pk_stock, lo.name as name, tp.production_date as transaction_date, s.volume as volume, s.hpp as hpp, loUnit.name as satuan, r.pk_rmu as pk_rmu from trans_production_to tpt
	inner join stock s on tpt.fk_stock_to = s.pk_stock
	inner join lookup lo
			join lookup loParent on lo.fk_lookup_parent = loParent.pk_lookup and loParent.code = 'BBNP'
		on lo.pk_lookup = s.fk_lookup_stock_type
	inner join lookup loUnit on loUnit.pk_lookup = tpt.fk_lookup_volume_unit
	inner join trans_production tp on tp.pk_trans_production = tpt.fk_trans_production
	inner join rmu r on r.pk_rmu = tp.fk_rmu)) b;
	
	alter table trans_sell_detail add fk_trans_sell bigint;
	
	alter table trans_production add fk_lookup_actor_production bigint;
	alter table trans_production add constraint tp_fk_lookup_actor_prod_l_pk_lookup FOREIGN KEY (fk_lookup_actor_production)
      REFERENCES lookup (pk_lookup) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
      
    alter table trans_production alter column method_other drop not null;