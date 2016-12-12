package id.padiku.app.service.forecast.call.api;

import id.padiku.app.exception.SystemException;

import java.util.Map;

public interface ForecastWeatherAPI {
	
	public Map<String, Object> callForecastWeather(String coordinate) throws SystemException;

}
