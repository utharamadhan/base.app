package id.base.app.controller.master;

import id.base.app.controller.SuperController;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.master.IStockService;
import id.base.app.valueobject.master.CompanyProduct;
import id.base.app.valueobject.master.Stock;
import id.base.app.valueobject.party.Party;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_STOCK)
public class StockController extends SuperController<Stock> {

	@Autowired
	private IStockService stockService;
	
	@Override
	public MaintenanceService<Stock> getMaintenanceService() {
		return stockService;
	}

	@Override
	public Stock validate(Stock anObject) throws SystemException {
		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getAvailableCompanyProductByStock/{pkCompany}")
	@ResponseBody
	public List<CompanyProduct> getAvailableCompanyProductByStock(@PathVariable(value="pkCompany") Long pkCompany) throws SystemException {
		return stockService.getAvailableCompanyProductByStock(pkCompany);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getStockListByFilter/{pkCompany}/{pkCompanyProduct}/{tiSourceType}/{pkTiThirdParty}")
	@ResponseBody
	public List<Stock> getStockListByFilter(@PathVariable(value="pkCompany") Long pkCompany, @PathVariable(value="pkCompanyProduct") Long pkCompanyProduct,
			@PathVariable(value="tiSourceType") String tiSourceType, @PathVariable(value="pkTiThirdParty") Long pkTiThirdParty) throws SystemException {
		return stockService.getStockListByFilter(pkCompany, pkCompanyProduct, tiSourceType, pkTiThirdParty);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getStockListByCompanyProduct/{pkCompany}/{pkCompanyProduct}/{tiSourceType}")
	@ResponseBody
	public List<Stock> getStockListByCompanyProduct(@PathVariable(value="pkCompany") Long pkCompany, @PathVariable(value="pkCompanyProduct") Long pkCompanyProduct,
			@PathVariable(value="tiSourceType") String tiSourceType) throws SystemException {
		return stockService.getStockListByCompanyProduct(pkCompany, pkCompanyProduct, tiSourceType);
	}

	@RequestMapping(method=RequestMethod.POST, value="/addToStock")
	@ResponseBody
	public Map<String, Object> addToStock(@RequestParam Map<String, Object> stockMap) throws SystemException {
		return stockService.addToStock(stockMap);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getSuplierInStock/{pkCompany}/{pkCompanyProduct}/{tiSourceType}")
	@ResponseBody
	public List<Party> getSuplierInStock(@PathVariable(value="pkCompany") Long pkCompany, @PathVariable(value="pkCompanyProduct") Long pkCompanyProduct, 
			@PathVariable(value="tiSourceType") String tiSourceType) throws SystemException {
		return stockService.getSuplierInStock(pkCompany, pkCompanyProduct, tiSourceType);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getStockForDashboard/{pkCompany}")
	@ResponseBody
	public List<Stock> getStockForDashboard(@PathVariable(value="pkCompany") Long pkCompany) throws SystemException {
		return stockService.getStockForDashboard(pkCompany);
	}
}
