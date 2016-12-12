package id.padiku.app.service.forecast.call;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.forecast.ForecastCallDaily;
import id.padiku.app.valueobject.forecast.ForecastCallHourly;

public interface IForecastCallService extends MaintenanceService<ForecastCallHourly> {
	
	public ForecastCallHourly getForecastCallHourly(Long pkLookupAddress) throws SystemException;
	
	public ForecastCallDaily getForecastDaily(Long pkLookupAddress, Integer plusParam) throws SystemException;
	
	public void purgingForecastData() throws SystemException;
	
}
