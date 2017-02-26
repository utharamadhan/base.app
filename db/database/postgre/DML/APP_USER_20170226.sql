delete from app_user_role aur where aur.fk_app_user in (select au.pk_app_user from app_user au where au.user_name = 'GEORGE');
delete from app_user au where au.user_name = 'GEORGE';
delete from party pr where pr.code = 'GEOR';