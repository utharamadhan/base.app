  CREATE TABLE QRTZ_LOCKS
   (	SCHED_NAME character varying(120), 
	LOCK_NAME character varying(40)
   );

  CREATE TABLE QRTZ_BLOB_TRIGGERS 
   (	SCHED_NAME character varying (120), 
	TRIGGER_NAME character varying(200), 
	TRIGGER_GROUP character varying(200), 
	BLOB_DATA bytea
   );

  CREATE TABLE QRTZ_CALENDARS 
   (    SCHED_NAME character varying (120), 
	CALENDAR_NAME character varying (200), 
	CALENDAR bytea
   );
   
  CREATE TABLE QRTZ_CRON_TRIGGERS 
   (	SCHED_NAME  character varying(120), 
	TRIGGER_NAME  character varying(200), 
	TRIGGER_GROUP  character varying(200), 
	CRON_EXPRESSION  character varying(120), 
	TIME_ZONE_ID  character varying(80)
   );

  CREATE TABLE QRTZ_FIRED_TRIGGERS 
   (	SCHED_NAME character varying(120), 
	ENTRY_ID character varying(95), 
	TRIGGER_NAME character varying(200), 
	TRIGGER_GROUP character varying(200), 
	INSTANCE_NAME character varying(200), 
	FIRED_TIME bigint, 
	SCHED_TIME bigint, 
	PRIORITY bigint, 
	STATE character varying(16), 
	JOB_NAME character varying(200), 
	JOB_GROUP character varying(200), 
	IS_NONCONCURRENT character varying(10), 
	REQUESTS_RECOVERY character varying(10)
   );
   
  CREATE TABLE QRTZ_JOB_DETAILS 
   (	SCHED_NAME character varying(120), 
	JOB_NAME character varying(200), 
	JOB_GROUP character varying(200), 
	DESCRIPTION character varying(250), 
	JOB_CLASS_NAME character varying(250), 
	IS_DURABLE character varying(10), 
	IS_NONCONCURRENT character varying(10), 
	IS_UPDATE_DATA character varying(10), 
	REQUESTS_RECOVERY character varying(10), 
	JOB_DATA bytea
   );

  CREATE TABLE QRTZ_PAUSED_TRIGGER_GRPS 
   (	SCHED_NAME character varying(120), 
	TRIGGER_GROUP character varying(200)
   );
   
  CREATE TABLE QRTZ_SCHEDULER_STATE 
   (	SCHED_NAME character varying(120), 
	INSTANCE_NAME character varying(200), 
	LAST_CHECKIN_TIME bigint, 
	CHECKIN_INTERVAL bigint
   );
   
  CREATE TABLE QRTZ_SIMPLE_TRIGGERS 
   (	SCHED_NAME character varying(120),
	TRIGGER_NAME character varying(200), 
	TRIGGER_GROUP character varying(200), 
	REPEAT_COUNT bigint, 
	REPEAT_INTERVAL bigint, 
	TIMES_TRIGGERED bigint
   );
   
  CREATE TABLE QRTZ_SIMPROP_TRIGGERS 
   (	SCHED_NAME character varying(120), 
	TRIGGER_NAME character varying(200), 
	TRIGGER_GROUP character varying(200), 
	STR_PROP_1 character varying(512), 
	STR_PROP_2 character varying(512), 
	STR_PROP_3 character varying(512), 
	INT_PROP_1 bigint, 
	INT_PROP_2 bigint, 
	LONG_PROP_1 bigint, 
	LONG_PROP_2 bigint, 
	DEC_PROP_1 bigint, 
	DEC_PROP_2 bigint, 
	BOOL_PROP_1 character varying(20), 
	BOOL_PROP_2 character varying(20)
   );

   drop table qrtz_simprop_triggers

  CREATE TABLE QRTZ_TRIGGERS 
   (	SCHED_NAME character varying(120), 
	TRIGGER_NAME character varying(200), 
	TRIGGER_GROUP character varying(200), 
	JOB_NAME character varying(200), 
	JOB_GROUP character varying(200), 
	DESCRIPTION character varying(250), 
	NEXT_FIRE_TIME bigint, 
	PREV_FIRE_TIME bigint, 
	PRIORITY bigint, 
	TRIGGER_STATE character varying(16), 
	TRIGGER_TYPE character varying(8), 
	START_TIME bigint, 
	END_TIME bigint, 
	CALENDAR_NAME character varying(200), 
	MISFIRE_INSTR bigint, 
	JOB_DATA bytea
   );
   
   INSERT INTO APP_PARAMETER 
   (DESCR, IS_VIEWABLE, NAME, VALUE, CREATED_BY, MODIFIED_BY, CREATION_TIME, MODIFICATION_TIME)
 VALUES
   ('Cron Scheduler for Test Job', true, 'TEST_SCHEDULER_JOB_SCHEDULER', '0 0/1 * * * ?', 'SYSTEM', 'SYSTEM', CURRENT_DATE, CURRENT_DATE);
   
  CREATE TABLE JOB_MONITOR 
   (	PK_JOB_MONITOR bigint, 
	START_TIME date not null default CURRENT_DATE, 
	END_TIME date, 
	JOB_NAME character varying (255), 
	DURATION bigint, 
	STATUS character varying (20), 
	REMARK text
   ) ;

   create sequence JOB_MONITOR_PK_JOB_MONITOR_SEQ start 1;
   alter table job_monitor alter column pk_job_monitor set default nextval('JOB_MONITOR_PK_JOB_MONITOR_SEQ');