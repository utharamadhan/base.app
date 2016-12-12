package id.padiku.app.dao.master;

import id.padiku.app.IBaseDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.master.CompanyProduct;
import id.padiku.app.valueobject.master.Stock;
import id.padiku.app.valueobject.party.Party;

import java.math.BigDecimal;
import java.util.List;

public interface IStockDAO extends IBaseDAO<Stock>{
	
	public List<CompanyProduct> getAvailableCompanyProductByStock(Long pkCompany) throws SystemException;
	
	public List<Stock> getStockListByFilter(Long pkCompany, Long pkCompanyProduct,
			String tiSourceType, Long pkTiThirdParty) throws SystemException;

	public List<Party> getSuplierInStock(Long pkCompany, Long pkCompanyProduct, String tiSourceType)
			throws SystemException;

	public List<Stock> getStockListByCompanyProduct(Long pkCompany,
			Long pkCompanyProduct, String tiSourceType) throws SystemException;

	public int updateRemainingVolume(Long pkTransInItem, BigDecimal volume);

	public List<Stock> getStockForDashboard(Long pkCompany) throws SystemException;

}
