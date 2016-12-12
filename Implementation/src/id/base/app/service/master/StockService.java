package id.base.app.service.master;

import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.dao.master.IStockDAO;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.service.lookup.ILookupService;
import id.base.app.service.transaction.ITransInItemService;
import id.base.app.util.BigDecimalFunction;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.master.CompanyProduct;
import id.base.app.valueobject.master.CompanyWarehouse;
import id.base.app.valueobject.master.Stock;
import id.base.app.valueobject.master.StockHistory;
import id.base.app.valueobject.party.Party;
import id.base.app.valueobject.procurement.TransIn;
import id.base.app.valueobject.procurement.TransInItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import softtech.hong.hce.core.QueryTransformer;
import softtech.hong.hce.model.Expression;

@Service
@Transactional
public class StockService extends QueryTransformer<Stock> implements IStockService{

	protected static Logger LOGGER = LoggerFactory.getLogger(StockService.class);
	
	@Autowired
	private IStockDAO stockDAO;
	
	@Autowired
	private ITransInItemService transInItemService;
	
	@Autowired
	private ILookupService lookupService;
	
	@Override
	public Stock findById(Long id) throws SystemException {
		return stockDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(Stock anObject) throws SystemException {
		stockDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		stockDAO.delete(objectPKs);
	}

	@Override
	public List<Stock> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Stock> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return stockDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<Stock> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return null;
	}

	@Override
	public List<CompanyProduct> getAvailableCompanyProductByStock(Long pkCompany) throws SystemException {
		return stockDAO.getAvailableCompanyProductByStock(pkCompany);
	}

	@Override
	public Map<String, Object> addToStock(Map<String, Object> stockMap) throws SystemException {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errorList = new ArrayList<>();
		try {
			if(stockMap.get("combValue")!=null && stockMap.get("pkCompanyWarehouse")!=null && stockMap.get("currentSource")!=null){
				Long pkCompanyWarehouse = Long.valueOf(stockMap.get("pkCompanyWarehouse").toString());
				CompanyWarehouse cw = new CompanyWarehouse();
				cw.setPkCompanyWarehouse(pkCompanyWarehouse);
				
				Lookup kg = lookupService.findByCode(ILookupConstant.Uom.KG, ILookupGroupConstant.UOM);
				
				String transInItem = stockMap.get("combValue").toString();
				String prop[] = transInItem.split("-");
				Long pkTransInItem = Long.valueOf(prop[0]);
				BigDecimal volume = new BigDecimal(prop[1]);
				Long pkLookupUom = Long.valueOf(prop[2]);
				
				TransInItem obj = transInItemService.findByIdForAddToStock(pkTransInItem);
				
				BigDecimal totalAllInFee = obj.getTransIn().getMainFee().add(obj.getTransIn().getTotalInFee());
				BigDecimal hppTransIn = BigDecimalFunction.divide(totalAllInFee, obj.getVolumeInKg());
				
				Stock s = new Stock();
				TransIn ti = new TransIn();
				ti.setPkTransIn(obj.getTransIn().getPkTransIn());
				s.setTransIn(ti);
				s.setCompanyWarehouse(cw);
				s.setItemType(obj.getItemType());
				s.setCompanyProduct(obj.getCompanyProduct());
				s.setVolume(volume);
				Lookup uom = new Lookup();
				uom.setPkLookup(pkLookupUom);
				if(pkLookupUom==kg.getPkLookup()){
					s.setVolumeInKg(volume);
					s.setRemainingVolumeInKg(volume);
				}
				s.setUom(uom);
				s.setHpp(hppTransIn);
				s.setStatus(SystemConstant.ValidFlag.VALID);
				
				List<StockHistory> shList = new ArrayList<StockHistory>();
				StockHistory sh = new StockHistory();
				sh.setStock(s);
				sh.setTransInItem(obj);
				shList.add(sh);
				
				s.setStockHistories(shList);
				saveOrUpdate(s);
				
				if(obj.getRemainingVolumeInKg().compareTo(volume)==0){
					transInItemService.updateStatus(pkTransInItem, SystemConstant.StatusTransInItem.EMPTY);	
				}

				transInItemService.updateRemainingVolume(pkTransInItem, volume);
				
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			errorList.add(new ErrorHolder(e.getMessage()));
			resultMap.put(SystemConstant.ERROR_LIST, errorList);
		}
		return resultMap;
	}
	
	@Override
	public List<Stock> getStockListByFilter(Long pkCompany, Long pkCompanyProduct, String tiSourceType, Long pkTiThirdParty) throws SystemException {
		return stockDAO.getStockListByFilter(pkCompany, pkCompanyProduct, tiSourceType, pkTiThirdParty);
	}
	
	@Override
	public List<Stock> getStockListByCompanyProduct(Long pkCompany, Long pkCompanyProduct, String tiSourceType) throws SystemException {
		return stockDAO.getStockListByCompanyProduct(pkCompany, pkCompanyProduct, tiSourceType);
	}
	
	@Override
	public List<Party> getSuplierInStock(Long pkCompany, Long pkCompanyProduct, String tiSourceType) throws SystemException {
		return stockDAO.getSuplierInStock(pkCompany, pkCompanyProduct, tiSourceType);
	}
	
	@Override
	public BigDecimal findHppByPk(Long pkStock) throws SystemException {
		Expression exp = new Expression();
		exp.add(Expression.eq("pkStock", pkStock));
		DetachedCriteria detachedCriteria = criteriaByProperty(new String[]{"hpp"}, exp);
		Stock s = stockDAO.first(detachedCriteria);
		return s.getHpp();
	}
	
	@Override
	public int updateRemainingVolume(Long pkStock, BigDecimal volume) {
		return stockDAO.updateRemainingVolume(pkStock, volume);
	}
	
	@Override
	public List<Stock> getStockForDashboard(Long pkCompany) throws SystemException {
		return stockDAO.getStockForDashboard(pkCompany);
	}
}
