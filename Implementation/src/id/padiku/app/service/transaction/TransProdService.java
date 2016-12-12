package id.padiku.app.service.transaction;

import id.padiku.app.HCEFilterOrderConverter;
import id.padiku.app.SystemConstant;
import id.padiku.app.dao.transaction.ITransProdDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.service.master.ICompanyProductService;
import id.padiku.app.service.master.IStockService;
import id.padiku.app.util.DateTimeFunction;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.util.dao.SearchOrder.Sort;
import id.padiku.app.valueobject.master.Stock;
import id.padiku.app.valueobject.master.StockHistory;
import id.padiku.app.valueobject.production.TransProd;
import id.padiku.app.valueobject.production.TransProdOtherItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import softtech.hong.hce.core.QueryTransformer;
import softtech.hong.hce.model.Expression;

@Service
@Transactional
public class TransProdService extends QueryTransformer<TransProd> implements ITransProdService{

	@Autowired
	private ITransProdDAO transProdDAO;
	
	@Autowired
	private ICompanyProductService companyProductService;
	
	@Autowired
	private IStockService stockService;
	
	@Override
	public TransProd findById(Long id) throws SystemException {
		return transProdDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(TransProd anObject) throws SystemException {
		transProdDAO.saveOrUpdate(anObject);
		
		if(anObject.getStatus()==SystemConstant.StatusProd.COMPLETE){
			//save main result
			Stock s = new Stock();
			s.setCompanyWarehouse(anObject.getCompanyWarehouseMain());
			s.setItemType(companyProductService.findItemType(anObject.getCompanyProductTo().getPkCompanyProduct()));
			s.setCompanyProduct(anObject.getCompanyProductTo());
			s.setVolume(anObject.getVolumeTo());
			s.setUom(anObject.getUomTo());
			s.setVolumeInKg(anObject.getVolumeToInKg());
			s.setRemainingVolumeInKg(anObject.getVolumeToInKg());
			s.setHpp(anObject.getHpp());
			s.setStatus(SystemConstant.ValidFlag.VALID);
			s.setTransProd(anObject);
			
			List<StockHistory> shList = new ArrayList<StockHistory>();
			StockHistory sh = new StockHistory();
			sh.setStock(s);
			sh.setTransProd(anObject);
			shList.add(sh);
			s.setStockHistories(shList);
			stockService.saveOrUpdate(s);
			anObject.setStockTo(s);
			transProdDAO.saveOrUpdate(anObject);
			
			stockService.updateRemainingVolume(anObject.getStockFrom().getPkStock(), anObject.getVolumeInKg());
			
			if(anObject.getOtherItems() != null && anObject.getOtherItems().size() > 0) {
				//save other result
				for (TransProdOtherItem o : anObject.getOtherItems()) {
					Stock so = new Stock();
					so.setCompanyWarehouse(anObject.getCompanyWarehouseOther());
					so.setItemType(companyProductService.findItemType(o.getCompanyProduct().getPkCompanyProduct()));
					so.setCompanyProduct(o.getCompanyProduct());
					so.setVolume(o.getVolume());
					so.setUom(o.getUom());
					so.setVolumeInKg(o.getVolumeInKg());
					so.setRemainingVolumeInKg(o.getVolumeInKg());
					so.setStatus(SystemConstant.ValidFlag.VALID);
					so.setTransProd(anObject);
					
					List<StockHistory> shoList = new ArrayList<StockHistory>();
					StockHistory sho = new StockHistory();
					sho.setStock(so);
					sho.setTransProd(anObject);
					shoList.add(sho);
					so.setStockHistories(shoList);
					stockService.saveOrUpdate(so);
				}
			}
		}
		
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		transProdDAO.delete(objectPKs);
	}

	@Override
	public List<TransProd> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<TransProd> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return transProdDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<TransProd> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return null;
	}
	
	@Override
	public Long countTransProd(Long pkCompany){
		return transProdDAO.countTransProd(pkCompany);
	}
	
	@Override
	public List<TransProd> findAllByYearMonth(Long pkCompany, Integer year, Integer month) throws SystemException {
		Date firstDateInMonth = DateTimeFunction.truncateDate(DateTimeFunction.getDate(year, month, 1));
		Date lastDateInMonth = DateTimeFunction.truncateDate(DateTimeFunction.getDate(year, month+1, 1));
		
		List<SearchOrder> orders = new ArrayList<SearchOrder>();
		orders.add(new SearchOrder(TransProd.PROD_DATE_FROM,Sort.ASC));
		Expression exp = new Expression();
		exp.add(Expression.eq(TransProd.COMPANY_ID, pkCompany));
		exp.add(Expression.ge(TransProd.PROD_DATE_FROM, firstDateInMonth));
		exp.add(Expression.lt(TransProd.PROD_DATE_TO, lastDateInMonth));
		DetachedCriteria detachedCriteria = criteriaByProperty(new String[]{
				TransProd.ID, TransProd.PROD_NO, TransProd.TRANS_IN_SOURCE_TYPE, TransProd.COMPANY_PRODUCT_FROM_NAME, TransProd.COMPANY_PRODUCT_TO_NAME, 
				TransProd.PROD_DATE_FROM, TransProd.PROD_DATE_TO, TransProd.STATUS}, exp,
				HCEFilterOrderConverter.convertOrders(orders));
		List<TransProd> tpList = transProdDAO.loadAll(detachedCriteria);
		for (TransProd tp : tpList) {
			tp.setStart(DateTimeFunction.date2String(tp.getProdDateFrom(), SystemConstant.SYSTEM_DATE_MONTH_POINT));
			tp.setEnd(DateTimeFunction.date2String(tp.getProdDateTo(), SystemConstant.SYSTEM_DATE_MONTH_POINT));
			tp.setUrl("javascript:showEdit("+tp.getPkTransProd()+")");
			if(tp.getStatus()==SystemConstant.StatusProd.INVALID){
				tp.setClassName("cancel event");	
			}else if(tp.getStatus()==SystemConstant.StatusProd.VALID){
				if(DateTimeFunction.compareDate(tp.getProdDateTo(), DateTimeFunction.getCurrentDateWithoutTime())<0){
					tp.setClassName("cancel event");	
				}else if(DateTimeFunction.compareDate(tp.getProdDateTo(), DateTimeFunction.getCurrentDateWithoutTime())>=0){
					if(SystemConstant.TransInSourceType.BUYING.equalsIgnoreCase(tp.getTransInSourceType())){
						tp.setClassName("buy event");
					}else{
						tp.setClassName("maklon event");
					}
				}
			}else if(tp.getStatus()==SystemConstant.StatusProd.PAST){
				tp.setClassName("past event");
			}else if(tp.getStatus()==SystemConstant.StatusProd.COMPLETE){
				tp.setClassName("success event");
			}
		}
		return tpList;
	}
}