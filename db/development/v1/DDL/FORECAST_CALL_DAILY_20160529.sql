CREATE TABLE FORECAST_CALL_DAILY
(
  PK_FORECAST_CALL_DAILY bigserial NOT NULL,
  FK_LOOKUP_ADDRESS bigint,
  SUMMARY character varying(200) NOT NULL,
  ICON character varying(200) NOT NULL,
  SUNRISE_TIME bigint,
  SUNSET_TIME bigint,
  MOON_PHASE bigint,
  PRECIP_INTENSITY numeric(24,4),
  PRECIP_INTENSITY_MAX numeric(24,4),
  PRECIP_INTENSITY_MAX_TIME bigint,
  PRECIP_PROBABILITY numeric(24,4),
  PRECIP_TYPE character varying(200) NOT NULL,
  TEMPERATURE_MIN numeric(24,4),
  TEMPERATURE_MIN_TIME bigint,
  TEMPERATURE_MAX numeric(24,4),
  TEMPERATURE_MAX_TIME bigint,
  APPARENT_TEMPERATURE_MIN numeric(24,4), 
  APPARENT_TEMPERATURE_MIN_TIME bigint,
  APPARENT_TEMPERATURE_MAX numeric(24,4),
  APPARENT_TEMPERATURE_MAX_TIME bigint,
  DEW_POINT numeric(24,4), 
  HUMIDITY numeric(24,4), 
  WIND_SPEED numeric(24,4), 
  WIND_BEARING numeric(24,4), 
  VISIBILITY numeric(24,4), 
  CLOUD_COVER numeric(24,4), 
  PRESSURE numeric(24,4), 
  OZONE numeric(24,4), 
  FORECAST_DATE timestamp with time zone NOT NULL DEFAULT now(),
  CONSTRAINT PK_FORECAST_CALL_DAILY PRIMARY KEY (PK_FORECAST_CALL_DAILY), 
  CONSTRAINT fcd_fk_lookup_address_la_pk_lookup_address FOREIGN KEY (FK_LOOKUP_ADDRESS)
      REFERENCES lookup_address (PK_LOOKUP_ADDRESS) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);