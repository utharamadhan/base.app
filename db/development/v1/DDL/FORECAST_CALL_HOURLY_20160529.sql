CREATE TABLE FORECAST_CALL_HOURLY
(
  PK_FORECAST_CALL_HOURLY bigserial NOT NULL,
  FK_LOOKUP_ADDRESS bigint,
  SUMMARY character varying(200),
  ICON character varying(200),
  PRECIP_TYPE character varying(200),
  PRECIP_INTENSITY numeric(24,4),
  PRECIP_PROBABILITY numeric(24,4),
  TEMPERATURE numeric(24,4),
  APPARENT_TEMPERATURE numeric(24,4), 
  DEW_POINT numeric(24,4), 
  HUMIDITY numeric(24,4), 
  WIND_SPEED numeric(24,4), 
  WIND_BEARING numeric(24,4), 
  VISIBILITY numeric(24,4), 
  CLOUD_COVER numeric(24,4), 
  PRESSURE numeric(24,4), 
  OZONE numeric(24,4), 
  FORECAST_DATE timestamp with time zone DEFAULT now(),
  FORECAST_TIME integer,
  CONSTRAINT pk_forecast_call_hourly PRIMARY KEY (pk_forecast_call_hourly), 
  CONSTRAINT fch_fk_lookup_address_la_pk_lookup_address FOREIGN KEY (fk_lookup_address)
      REFERENCES lookup_address (pk_lookup_address) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);