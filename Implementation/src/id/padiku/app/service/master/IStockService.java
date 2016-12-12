package id.padiku.app.service.master;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.master.CompanyProduct;
import id.padiku.app.valueobject.master.Stock;
import id.padiku.app.valueobject.party.Party;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IStockService extends MaintenanceService<Stock>{

	public List<CompanyProduct> getAvailableCompanyProductByStock(Long pkCompany) throws SystemException;
	
	public Map<String, Object> addToStock(Map<String, Object> stockMap)
			throws SystemException;

	public List<Stock> getStockListByFilter(Long pkCompany, Long pkCompanyProduct,
			String tiSourceType, Long pkTiThirdParty) throws SystemException;

	public List<Party> getSuplierInStock(Long pkCompany, Long pkCompanyProduct, String tiSourceType)
			throws SystemException;

	public BigDecimal findHppByPk(Long pkStock) throws SystemException;

	public List<Stock> getStockListByCompanyProduct(Long pkCompany,
			Long pkCompanyProduct, String tiSourceType) throws SystemException;

	public int updateRemainingVolume(Long pkStock, BigDecimal volume);

	public List<Stock> getStockForDashboard(Long pkCompany) throws SystemException;
	
}
