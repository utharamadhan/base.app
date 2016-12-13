package id.base.app.webMember.controller.dashboard;

import id.base.app.exception.SystemException;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.valueobject.business.report.ViewCashFlow;
import id.base.app.valueobject.forecast.ForecastCallDaily;
import id.base.app.valueobject.forecast.ForecastCallHourly;
import id.base.app.valueobject.master.Stock;
import id.base.app.webMember.WebGeneralFunction;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@Controller
@RequestMapping(RestConstant.RM_DASHBOARD)
public class DashboardWebController{

	protected static Logger LOGGER = LoggerFactory.getLogger(DashboardWebController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request){
		setDefaultData(model, request);
		return "/dashboard/dashboardDetail";
	}
	
	private void setDefaultData(ModelMap model, HttpServletRequest request){
		model.addAttribute("UIName",WebGeneralFunction.getLogin(request).getName());
		model.addAttribute("UIEmail",WebGeneralFunction.getLogin(request).getEmail());
		BigDecimal debit = BigDecimal.ZERO;
		BigDecimal kredit = BigDecimal.ZERO;
		/*List<ViewCashFlow> vcfList = getCashFlowFee(request);
		for (ViewCashFlow vcf : vcfList) {
			model.addAttribute(vcf.getSource(),vcf);
			debit = debit.add(vcf.getDebit());
			kredit = kredit.add(vcf.getKredit());
		}*/
		model.addAttribute("debit",debit);
		model.addAttribute("kredit",kredit);
		model.addAttribute("cash",debit.subtract(kredit));
		model.addAttribute("forecastResult", getForecastForCurrentTime(WebGeneralFunction.getLogin(request).getCompanySelected()));
		model.addAttribute("forecastResultPlusOne", getForecast(WebGeneralFunction.getLogin(request).getCompanySelected(), 1));
		model.addAttribute("forecastResultPlusTwo", getForecast(WebGeneralFunction.getLogin(request).getCompanySelected(), 2));
	};
	
	private ForecastCallHourly getForecastForCurrentTime(final Long pkCompany) {
		try{
			return new SpecificRestCaller<ForecastCallHourly>(RestConstant.REST_SERVICE, RestServiceConstant.FORECAST_CALL_SERVICE).executeGet(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/getForecastHourly/{pkCompany}";
				}
				
				@Override
				public Map<String, Object> getParameters() {
					Map<String, Object> map = new HashMap<>();
						map.put("pkCompany", pkCompany);
					return map;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private ForecastCallDaily getForecast(final Long pkCompany, final Integer plusParam) {
		try{
			return new SpecificRestCaller<ForecastCallDaily>(RestConstant.REST_SERVICE, RestConstant.RM_FORECAST_CALL, ForecastCallDaily.class).executeGet(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/getForecastDaily/{pkCompany}/{plusParam}";
				}
				
				@Override
				public Map<String, Object> getParameters() {
					Map<String, Object> map = new HashMap<>();
						map.put("pkCompany", pkCompany);
						map.put("plusParam", plusParam);
					return map;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ViewCashFlow> getCashFlowFee(HttpServletRequest request) throws SystemException {
		try{
			final Long pkCompany = WebGeneralFunction.getLogin(request).getCompanySelected();
			return new SpecificRestCaller<ViewCashFlow>(RestConstant.REST_SERVICE, RestServiceConstant.CASH_FLOW_SERVICE).executeGetList(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/getCashFlowFee/{pkCompany}";
				}
				
				@Override
				public Map<String, Object> getParameters() {
					Map<String, Object> map = new HashMap<>();
						map.put("pkCompany", pkCompany);
					return map;
				}
			});
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}

	@RequestMapping(method=RequestMethod.GET, value="getStockForDashboard")
	@ResponseBody
	public List<Stock> getStockForDashboard(HttpServletRequest request) throws SystemException {
		try{
			final Long pkCompany = WebGeneralFunction.getLogin(request).getCompanySelected();
			return new SpecificRestCaller<Stock>(RestConstant.REST_SERVICE, RestServiceConstant.STOCK_SERVICE).executeGetList(new PathInterfaceRestCaller() {
				@Override
				public String getPath() {
					return "/getStockForDashboard/{pkCompany}";
				}
				
				@Override
				public Map<String, Object> getParameters() {
					Map<String, Object> map = new HashMap<>();
						map.put("pkCompany", pkCompany);
					return map;
				}
			});
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
}
