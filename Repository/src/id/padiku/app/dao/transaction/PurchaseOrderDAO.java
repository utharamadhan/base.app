package id.padiku.app.dao.transaction;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.IBaseDAO;
import id.padiku.app.SystemConstant;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.StringFunction;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.Lookup;
import id.padiku.app.valueobject.master.Company;
import id.padiku.app.valueobject.master.CompanyLookup;
import id.padiku.app.valueobject.master.CompanyProduct;
import id.padiku.app.valueobject.party.Party;
import id.padiku.app.valueobject.procurement.PurchaseOrder;
import id.padiku.app.valueobject.procurement.PurchaseOrderDetail;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class PurchaseOrderDAO extends AbstractHibernateDAO<PurchaseOrder, Long> implements IBaseDAO<PurchaseOrder>, IPurchaseOrderDAO {

	@Override
	public PurchaseOrder findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(PurchaseOrder anObject) throws SystemException {
		if(anObject.getPkPurchaseOrder()!=null){
			super.update(anObject);
		}else{
			anObject.setPoNumber(generatePurchaseOrderNumber(anObject.getCompany().getPkCompany()));
			super.create(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		for(Long objectPK : objectPKs){
			PurchaseOrder obj = findById(objectPK);
				obj.setStatus(SystemConstant.ValidFlag.INVALID);
			super.update(obj);
		}
	}

	@Override
	public List<PurchaseOrder> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<PurchaseOrder> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}
	
	@Override
	public PurchaseOrder getPurchaseOrderById(Long maintenancePK) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias("company", "company");
			crit.createAlias("supplier", "supplier");
			crit.createAlias("termOfPayment", "termOfPayment");
			crit.createAlias("currency", "currency");
			crit.createAlias("partyReference", "partyReference", JoinType.LEFT_OUTER_JOIN);
			crit.add(Restrictions.eq("pkPurchaseOrder", maintenancePK));
			crit.setProjection(Projections.projectionList().
				add(Projections.property("pkPurchaseOrder")). //0
				add(Projections.property("company.pkCompany")). //1
				add(Projections.property("company.name")). //2
				add(Projections.property("supplier.pkParty")). //3
				add(Projections.property("supplier.name")). //4
				add(Projections.property("termOfPayment.pkCompanyLookup")). //5
				add(Projections.property("currency.pkCompanyLookup")). //6
				add(Projections.property("quantity")). //7
				add(Projections.property("subvalue")). //8
				add(Projections.property("vatPercent")). //9
				add(Projections.property("vatValue")). //10
				add(Projections.property("totalValue")). //11
				add(Projections.property("purchaseOrderDate")). //12
				add(Projections.property("partyReference.pkParty")). //13
				add(Projections.property("partyReference.name")). //14
				add(Projections.property("status")).
				add(Projections.property("poNumber"))); //15
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					PurchaseOrder po = new PurchaseOrder();
					try{
						BeanUtils.copyProperty(po, "pkPurchaseOrder", tuple[0]);
						if(tuple[1]!=null){
							Company cm = new Company();
							BeanUtils.copyProperty(cm, "pkCompany", tuple[1]);
							BeanUtils.copyProperty(cm, "name", tuple[2]);
							BeanUtils.copyProperty(po, "company", cm);
						}
						if(tuple[3]!=null){
							Party p = new Party();
							BeanUtils.copyProperty(p, "pkParty", tuple[3]);
							BeanUtils.copyProperty(p, "name", tuple[4]);
							BeanUtils.copyProperty(po, "supplier", p);
						}
						if(tuple[5]!=null){
							CompanyLookup cl = new CompanyLookup();
							BeanUtils.copyProperty(cl, "pkCompanyLookup", tuple[5]);
							BeanUtils.copyProperty(po, "termOfPayment", cl);
						}
						if(tuple[6]!=null){
							CompanyLookup cl = new CompanyLookup();
							BeanUtils.copyProperty(cl, "pkCompanyLookup", tuple[6]);
							BeanUtils.copyProperty(po, "currency", cl);
						}
						BeanUtils.copyProperty(po, "quantity", tuple[7]);
						BeanUtils.copyProperty(po, "subvalue", tuple[8]);
						BeanUtils.copyProperty(po, "vatPercent", tuple[9]);
						BeanUtils.copyProperty(po, "vatValue", tuple[10]);
						BeanUtils.copyProperty(po, "totalValue", tuple[11]);
						BeanUtils.copyProperty(po, "purchaseOrderDate", tuple[12]);
						if(tuple[13]!=null){
							Party p = new Party();
							BeanUtils.copyProperty(p, "pkParty", tuple[13]);
							BeanUtils.copyProperty(p, "name", tuple[14]);
							BeanUtils.copyProperty(po, "partyReference", p);
						}
						BeanUtils.copyProperty(po, "status", tuple[15]);
						BeanUtils.copyProperty(po, "poNumber", tuple[16]);
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
					return po;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		PurchaseOrder po = (PurchaseOrder) crit.uniqueResult();
		if(null != po) {
			po.setPurchaseOrderElements(getPurchaseOrderElements(maintenancePK));
		}
		return po;
	}
	
	public List<PurchaseOrderDetail> getPurchaseOrderElements(Long maintenancePK) throws SystemException {
		Criteria crit = getSession().createCriteria(PurchaseOrderDetail.class);
			crit.createAlias("purchaseOrder", "purchaseOrder");
			crit.createAlias("product", "product");
			crit.createAlias("uom", "uom");
			crit.add(Restrictions.eq("purchaseOrder.pkPurchaseOrder", maintenancePK));
			crit.addOrder(Order.asc("sequenceNumber"));
			crit.setProjection(Projections.projectionList().
					add(Projections.property("pkPurchaseOrderDetail")). //0
					add(Projections.property("product.pkCompanyProduct")). //1
					add(Projections.property("product.name")). //2
					add(Projections.property("uom.pkLookup")). //3
					add(Projections.property("quantity")). //4
					add(Projections.property("purchasePrice")). //5
					add(Projections.property("orderValue")). //6
					add(Projections.property("sequenceNumber")) //7 
			);
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					PurchaseOrderDetail pod = new PurchaseOrderDetail();
					try{
						BeanUtils.copyProperty(pod, "pkPurchaseOrderDetail", tuple[0]);
						if(tuple[1]!=null){
							CompanyProduct p = new CompanyProduct();
							BeanUtils.copyProperty(p, "pkCompanyProduct", tuple[1]);
							BeanUtils.copyProperty(p, "name", tuple[2]);
							BeanUtils.copyProperty(pod, "product", p);
						}
						if(tuple[3]!=null){
							Lookup cl = new Lookup();
							BeanUtils.copyProperty(cl, "pkLookup", tuple[3]);
							BeanUtils.copyProperty(pod, "uom", cl);
						}
						BeanUtils.copyProperty(pod, "quantity", tuple[4]);
						BeanUtils.copyProperty(pod, "purchasePrice", tuple[5]);
						BeanUtils.copyProperty(pod, "orderValue", tuple[6]);
						BeanUtils.copyProperty(pod, "sequenceNumber", tuple[7]);
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
					return pod;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		return crit.list();
	}

	@Override
	public List<PurchaseOrder> findAllByStock(Long pkStock) throws SystemException {
		Criteria criteria = getSession().createCriteria(domainClass);
		criteria.createAlias("stock", "stock");
		criteria.createAlias("transBuyOtherFees", "transBuyOtherFees");
		criteria.add(Restrictions.eq("stock.pkStock", pkStock));
		criteria.setProjection(Projections.projectionList().
				add(Projections.property("pkTransBuy")).
				add(Projections.property("buyingDate")).
				add(Projections.property("volume")).
				add(Projections.property("unitBuyFee")).
				add(Projections.property("totalBuyFee")).
				add(Projections.sum("transBuyOtherFees.fee")).
				add(Projections.groupProperty("pkTransBuy")));
		criteria.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				PurchaseOrder tb = new PurchaseOrder();
				try{
					BeanUtils.copyProperty(tb, "pkTransBuy", tuple[0]);
					BeanUtils.copyProperty(tb, "buyingDate", tuple[1]);
					BeanUtils.copyProperty(tb, "volume", tuple[2]);
					BeanUtils.copyProperty(tb, "unitBuyFee", tuple[3]);
					BeanUtils.copyProperty(tb, "totalBuyFee", tuple[4]);
					BeanUtils.copyProperty(tb, "totalOtherFees", tuple[5]);
				}catch(Exception e){
					LOGGER.error(e.getMessage(), e);
				}
				return tb;
			}
			
			@Override
			public List transformList(List collection) {
				return collection;
			}
		});
		return criteria.list();
	}

	@Override
	public List<PurchaseOrder> findAllBuyingResultByRmuAndCategory(Long pkRmu, Long pkCategoryLookup) throws SystemException {
		Criteria crit = getSession().createCriteria(PurchaseOrder.class);
			crit.createAlias("rmu", "rmu");
			crit.createAlias("partySeller", "partySeller");
			crit.createAlias("stock", "stock");
			crit.createAlias("stock.stockCategory", "stockCategory");
			crit.add(Restrictions.eq("rmu.pkRmu", pkRmu));
			crit.add(Restrictions.eq("stockCategory.pkLookup", pkCategoryLookup));
			crit.add(Restrictions.gt("stock.volume", 0L));
			crit.setProjection(Projections.projectionList().
					add(Projections.property("buyingDate")).
					add(Projections.property("stock.pkStock")).
					add(Projections.property("stock.stockName")).
					add(Projections.property("stock.volume")).
					add(Projections.property("stock.hpp")).
					add(Projections.property("partySeller.pkParty")).
					add(Projections.property("partySeller.name")).
					add(Projections.groupProperty("buyingDate")).
					add(Projections.groupProperty("stock.pkStock")).
					add(Projections.groupProperty("stock.stockName")).
					add(Projections.groupProperty("stock.volume")).
					add(Projections.groupProperty("stock.hpp")).
					add(Projections.groupProperty("partySeller.pkParty")).
					add(Projections.groupProperty("partySeller.name")));
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					PurchaseOrder tb = new PurchaseOrder();
					try{
						BeanUtils.copyProperty(tb, "buyingDate", tuple[0]);
						if(tuple[1]!=null){
							/*Stock s = new Stock();
							BeanUtils.copyProperty(s, "pkStock", tuple[1]);
							BeanUtils.copyProperty(s, "stockName", tuple[2]);
							BeanUtils.copyProperty(s, "volume", tuple[3]);
							BeanUtils.copyProperty(s, "hpp", tuple[4]);*/
							/*BeanUtils.copyProperty(tb, "stock", s);*/
						}
						if(tuple[5]!=null){
							Party p = new Party();
							BeanUtils.copyProperty(p, "pkParty", tuple[5]);
							BeanUtils.copyProperty(p, "name", tuple[6]);
							BeanUtils.copyProperty(tb, "partySeller", p);
						}
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
					return tb;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		return crit.list();
	}

	@Override
	public String generatePurchaseOrderNumber(Long pkCompany) throws SystemException {
		String poNumber = "";
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias("company", "company");
			crit.add(Restrictions.eq("company.pkCompany", pkCompany));
			crit.addOrder(Order.desc("poNumber"));
			crit.setProjection(Projections.projectionList().add(Projections.property("poNumber")));
			crit.setMaxResults(1);
		Object returnObj = crit.uniqueResult();
		if(returnObj != null && StringFunction.isNotEmpty(returnObj.toString())){
			Long poNumberLong = Long.valueOf(returnObj.toString());
			poNumber = String.format("%05d", ++poNumberLong);
		}else{
			poNumber = String.format("%05d", 1);
		}
		return poNumber;
	}
}