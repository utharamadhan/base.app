insert into app_parameter (pk_app_parameter, name, value, descr, is_viewable, datatype, created_by, creation_time, modified_by, modification_time)
values (3, 'PURGING_FORECAST_JOB_SCHEDULER', '0 0/1 * * * ?', 'Cron Scheduler for Purging Forecast Weather Data', true, null, 'SYSTEM', current_date, 'SYSTEM', current_date);
COMMIT;