package id.padiku.app.dao.forecast.call;

import id.padiku.app.IBaseDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.forecast.ForecastCallDaily;

import java.util.Date;

public interface IForecastCallDailyDAO extends IBaseDAO<ForecastCallDaily> {
	
	public Boolean isForecastDailyAlreadyExist(Long pkLookupAddress, Date date) throws SystemException;
	
	public ForecastCallDaily getForecastDaily(Long pkLookupAddress, Date dateParam) throws SystemException;
	
	public void purgingForecastDaily(Integer purgingLimit) throws SystemException;

}
