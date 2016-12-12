package id.padiku.app.dao.master;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.ILookupGroupConstant;
import id.padiku.app.SystemConstant;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.Lookup;
import id.padiku.app.valueobject.master.CompanyProduct;
import id.padiku.app.valueobject.master.Stock;
import id.padiku.app.valueobject.party.Party;
import id.padiku.app.valueobject.procurement.TransIn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class StockDAO extends AbstractHibernateDAO<Stock, Long> implements IStockDAO {

	@Override
	public Stock findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(Stock anObject) throws SystemException {
		if(anObject.getPkStock()!=null){
			super.update(anObject);
		}else{
			super.create(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		for(Long objectPK : objectPKs){
			Stock obj = findById(objectPK);
				obj.setStatus(SystemConstant.ValidFlag.INVALID);
			super.update(obj);
		}
	}

	@Override
	public List<Stock> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Stock> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<CompanyProduct> getAvailableCompanyProductByStock(Long pkCompany) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias("companyProduct", "companyProduct");
			crit.createAlias("companyProduct.company", "companyProductCompany");
			crit.createAlias("companyProduct.type", "companyProductType");
			crit.add(Restrictions.gt("volumeInKg", BigDecimal.ZERO));
			crit.add(Restrictions.eq("companyProductCompany.pkCompany", pkCompany));
			crit.setProjection(Projections.projectionList().
					add(Projections.groupProperty("companyProduct.pkCompanyProduct")).
					add(Projections.groupProperty("companyProduct.name")).
					add(Projections.groupProperty("companyProductType.pkLookup")).
					add(Projections.groupProperty("companyProductType.code")));
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					CompanyProduct cp = new CompanyProduct();
					try{
						BeanUtils.copyProperty(cp, "pkCompanyProduct", tuple[0]);
						BeanUtils.copyProperty(cp, "name", tuple[1]);
						if(tuple[2] != null) {
							Lookup lo = new Lookup();
							BeanUtils.copyProperty(lo, "pkLookup", tuple[2]);
							BeanUtils.copyProperty(lo, "code", tuple[3]);
							BeanUtils.copyProperty(cp, "type", lo);
						}
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
					return cp;
				}
				
				@Override
				public List transformList(List collection) {
					List<CompanyProduct> returnList = new ArrayList<>();
					outerLoop : 
					for(Object item : collection) {
						if(item instanceof CompanyProduct) {
							for(CompanyProduct returnItem : returnList) {
								if(returnItem.getPkCompanyProduct().equals(((CompanyProduct) item).getPkCompanyProduct())) {
									continue outerLoop;
								}
							}
							returnList.add((CompanyProduct)item);
						}
					}
					return collection;
				}
			});
		return crit.list();
	}

	@Override
	public List<Stock> getStockListByFilter(Long pkCompany, Long pkCompanyProduct, String tiSourceType, Long pkTiThirdParty) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias("transIn", "transIn", JoinType.LEFT_OUTER_JOIN);
			crit.createAlias("transIn.thirdParty", "transInThirdParty", JoinType.LEFT_OUTER_JOIN);
			crit.createAlias("companyProduct", "companyProduct");
			crit.createAlias("companyProduct.company", "company");
			crit.add(Restrictions.eq("company.pkCompany", pkCompany));
			crit.add(Restrictions.eq("companyProduct.pkCompanyProduct", pkCompanyProduct));
			if(tiSourceType.equals(SystemConstant.TransInSourceType.BUYING)) {
				crit.add(Restrictions.eq("transIn.sourceType", SystemConstant.TransInSourceType.BUYING));
			}else if(tiSourceType.equals(SystemConstant.TransInSourceType.MAKLON)) {
				crit.add(Restrictions.eq("transIn.sourceType", SystemConstant.TransInSourceType.MAKLON));
			}
			crit.add(Restrictions.eq("transInThirdParty.pkParty", pkTiThirdParty));
			crit.add(Restrictions.gt("remainingVolumeInKg", BigDecimal.ZERO));
			crit.add(Restrictions.eq("status", SystemConstant.ValidFlag.VALID));
			crit.setProjection(Projections.projectionList().
					add(Projections.property("pkStock")).
					add(Projections.property("transInThirdParty.name")).
					add(Projections.property("transIn.inDate")).
					add(Projections.property("companyProduct.name")).
					add(Projections.property("remainingVolumeInKg")).
					add(Projections.property("hpp")));
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					Stock st = new Stock();
					try {
						BeanUtils.copyProperty(st, "pkStock", tuple[0]);
						Party p = new Party();
						BeanUtils.copyProperty(p, "name", tuple[1]);
						
						TransIn ti = new TransIn();
						ti.setThirdParty(p);
						BeanUtils.copyProperty(ti, "inDate", tuple[2]);
						st.setTransIn(ti);
						
						CompanyProduct cp = new CompanyProduct();
						BeanUtils.copyProperty(cp, "name", tuple[3]);
						st.setCompanyProduct(cp);
						
						BeanUtils.copyProperty(st, "remainingVolumeInKg", tuple[4]);
						BeanUtils.copyProperty(st, "hpp", tuple[5]);
						
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
					return st;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		return crit.list();
	}
	
	@Override
	public List<Stock> getStockListByCompanyProduct(Long pkCompany, Long pkCompanyProduct, String tiSourceType) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias("transIn", "transIn", JoinType.LEFT_OUTER_JOIN);
			crit.createAlias("transIn.thirdParty", "transInThirdParty", JoinType.LEFT_OUTER_JOIN);
			crit.createAlias("companyProduct", "companyProduct");
			crit.createAlias("companyProduct.company", "company");
			crit.add(Restrictions.eq("company.pkCompany", pkCompany));
			crit.add(Restrictions.eq("companyProduct.pkCompanyProduct", pkCompanyProduct));
			crit.add(Restrictions.gt("hpp", BigDecimal.ZERO));
			/*if(tiSourceType.equals(SystemConstant.TransInSourceType.BUYING)) {
				crit.add(Restrictions.eq("transIn.sourceType", SystemConstant.TransInSourceType.BUYING));
			}else if(tiSourceType.equals(SystemConstant.TransInSourceType.MAKLON)) {
				crit.add(Restrictions.eq("transIn.sourceType", SystemConstant.TransInSourceType.MAKLON));
			}*/
			crit.add(Restrictions.gt("remainingVolumeInKg", BigDecimal.ZERO));
			crit.add(Restrictions.ne("status", SystemConstant.ValidFlag.INVALID));
			crit.setProjection(Projections.projectionList().
					add(Projections.property("pkStock")).
					add(Projections.property("transInThirdParty.name")).
					add(Projections.property("transIn.inDate")).
					add(Projections.property("companyProduct.name")).
					add(Projections.property("remainingVolumeInKg")).
					add(Projections.property("hpp")));
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					Stock st = new Stock();
					try {
						BeanUtils.copyProperty(st, "pkStock", tuple[0]);
						Party p = new Party();
						BeanUtils.copyProperty(p, "name", tuple[1]);
						
						TransIn ti = new TransIn();
						ti.setThirdParty(p);
						BeanUtils.copyProperty(ti, "inDate", tuple[2]);
						st.setTransIn(ti);
						
						CompanyProduct cp = new CompanyProduct();
						BeanUtils.copyProperty(cp, "name", tuple[3]);
						st.setCompanyProduct(cp);
						
						BeanUtils.copyProperty(st, "remainingVolumeInKg", tuple[4]);
						BeanUtils.copyProperty(st, "hpp", tuple[5]);
						
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
					return st;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		return crit.list();
	}
	
	@Override
	public List<Party> getSuplierInStock(Long pkCompany, Long pkCompanyProduct, String tiSourceType) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias("transIn", "transIn");
			crit.createAlias("companyProduct", "companyProduct");
			crit.createAlias("companyProduct.company", "company");
			crit.createAlias("transIn.thirdParty", "transInThirdParty");
			crit.add(Restrictions.eq("company.pkCompany", pkCompany));
			crit.add(Restrictions.eq("companyProduct.pkCompanyProduct", pkCompanyProduct));
			crit.add(Restrictions.gt("remainingVolumeInKg", BigDecimal.ZERO));
			crit.add(Restrictions.eq("status", SystemConstant.ValidFlag.VALID));
			if(tiSourceType.equals(SystemConstant.TransInSourceType.BUYING)) {
				crit.add(Restrictions.eq("transIn.sourceType", SystemConstant.TransInSourceType.BUYING));
			}else if(tiSourceType.equals(SystemConstant.TransInSourceType.MAKLON)) {
				crit.add(Restrictions.eq("transIn.sourceType", SystemConstant.TransInSourceType.MAKLON));
			}
			crit.setProjection(Projections.projectionList().
					add(Projections.groupProperty("transInThirdParty.pkParty")).
					add(Projections.property("transInThirdParty.name")));
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					Stock st = new Stock();
					try {
						Party p = new Party();
						BeanUtils.copyProperty(p, "pkParty", tuple[0]);
						BeanUtils.copyProperty(p, "name", tuple[1]);
						
						TransIn ti = new TransIn();
						ti.setThirdParty(p);
						st.setTransIn(ti);						
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
					return st;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		List<Stock> stockList = crit.list();
		List<Party> partyList = new ArrayList<Party>();
		for (Stock stock : stockList) {
			partyList.add(stock.getTransIn().getThirdParty());
		}
		return partyList;
	}
	
	@Override
	public int updateRemainingVolume(Long pkTransInItem, BigDecimal volume) {
		String updateQuery = "UPDATE STOCK SET REMAINING_VOLUME_IN_KG = REMAINING_VOLUME_IN_KG - ? "
				+ "WHERE PK_STOCK = ?";
		
		SQLQuery sqlQuery = getSession().createSQLQuery(updateQuery);
		sqlQuery.setBigDecimal(0, volume);
		sqlQuery.setLong(1, pkTransInItem);
		return sqlQuery.executeUpdate();
	}
	
	@Override
	public List<Stock> getStockForDashboard(Long pkCompany) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias("companyProduct", "companyProduct");
			crit.createAlias("companyProduct.company", "company");
			crit.createAlias("companyProduct.type", "type");
			crit.add(Restrictions.eq("company.pkCompany", pkCompany));
			crit.add(Restrictions.ne("status", SystemConstant.ValidFlag.INVALID));
			crit.add(Restrictions.eq("type.lookupGroupString", ILookupGroupConstant.ITEM_TYPE));
			crit.add(Restrictions.eq("type.usage", SystemConstant.UsageItemType.BAHAN_BAKU_PRODUKSI));
			crit.add(Restrictions.eq("type.status", SystemConstant.ValidFlag.VALID));
			crit.setProjection(Projections.projectionList().
					add(Projections.groupProperty("companyProduct.name")).
					add(Projections.sum("remainingVolumeInKg")));
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					Stock st = new Stock();
					try {
						BeanUtils.copyProperty(st, "companyProductName", tuple[0]);
						BeanUtils.copyProperty(st, "remainingVolumeInKg", tuple[1]);
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
					return st;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		return crit.list();
	}
}
