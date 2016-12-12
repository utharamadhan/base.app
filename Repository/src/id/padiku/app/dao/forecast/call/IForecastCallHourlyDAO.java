package id.padiku.app.dao.forecast.call;

import id.padiku.app.IBaseDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.forecast.ForecastCallHourly;

import java.util.List;

public interface IForecastCallHourlyDAO extends IBaseDAO<ForecastCallHourly> {
	
	public Boolean isAvailableForToday(Long pkLookupAddress) throws SystemException;
	
	public List<ForecastCallHourly> getAllForecastForToday(Long pkLookupAddress) throws SystemException;
	
	public ForecastCallHourly getCurrentForecastHourly(Long pkLookupAddress, Integer hour) throws SystemException;
	
	public void purgingForecastHourly(Integer purgingLimit) throws SystemException;
	
}
