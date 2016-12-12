update app_function set access_page = '/do/errorLog/showList?' where pk_app_function = 320;

insert into app_function(pk_app_function,fk_app_function_parent,name,descr,access_page,is_active,user_type,order_no)
values (350,300,'Faq','INT_SYS_FAQ','/do/faq/showList?',TRUE,1,5);
insert into app_function(pk_app_function,fk_app_function_parent,name,descr,access_page,is_active,user_type,order_no)
values (360,300,'Helper','INT_SYS_HELPER','/do/helper/showList?',TRUE,1,5);
