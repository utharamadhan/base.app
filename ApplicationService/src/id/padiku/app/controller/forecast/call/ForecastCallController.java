package id.padiku.app.controller.forecast.call;

import id.padiku.app.controller.SuperController;
import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.service.forecast.call.IForecastCallService;
import id.padiku.app.service.master.ICompanyService;
import id.padiku.app.valueobject.forecast.ForecastCallDaily;
import id.padiku.app.valueobject.forecast.ForecastCallHourly;
import id.padiku.app.valueobject.master.Company;
import id.padiku.app.valueobject.master.CompanyAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_FORECAST_CALL)
public class ForecastCallController extends SuperController<ForecastCallHourly> {

	@Autowired
	private IForecastCallService forecastCallService;
	@Autowired
	private ICompanyService companyService;
	
	@Override
	public MaintenanceService<ForecastCallHourly> getMaintenanceService() {
		return null;
	}

	@Override
	public ForecastCallHourly validate(ForecastCallHourly anObject) throws SystemException {
		return null;
	}

	@RequestMapping(method=RequestMethod.GET, value="/getForecastHourly/{pkCompany}")
	@ResponseBody
	public ForecastCallHourly getForecastHourly(@PathVariable(value="pkCompany") Long pkCompany) throws SystemException {
		Company c = companyService.findById(pkCompany);
		Long pkLookupAddress = null;
		if(c.getCompanyAddresses() != null && c.getCompanyAddresses().size() > 0) {
			for(CompanyAddress ca : c.getCompanyAddresses()) {
				if(ca.getKelurahan() != null && ca.getKelurahan().getPkLookupAddress() != null) {
					pkLookupAddress = ca.getKelurahan().getPkLookupAddress();
					break;
				}
			}
		}
		if (pkLookupAddress != null) {			
			return forecastCallService.getForecastCallHourly(pkLookupAddress);
		} else {
			return null;
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getForecastDaily/{pkCompany}/{plusParam}")
	@ResponseBody
	public ForecastCallDaily getForecastDaily(@PathVariable(value="pkCompany") Long pkCompany, @PathVariable(value="plusParam") Integer plusParam) throws SystemException {
		Company c = companyService.findById(pkCompany);
		Long pkLookupAddress = null;
		if(c.getCompanyAddresses() != null && c.getCompanyAddresses().size() > 0) {
			for(CompanyAddress ca : c.getCompanyAddresses()) {
				if(ca.getKelurahan() != null && ca.getKelurahan().getPkLookupAddress() != null) {
					pkLookupAddress = ca.getKelurahan().getPkLookupAddress();
					break;
				}
			}
		}
		if (pkLookupAddress != null) {			
			return forecastCallService.getForecastDaily(pkLookupAddress, plusParam);
		} else {
			return null;
		}
	}
	
}
