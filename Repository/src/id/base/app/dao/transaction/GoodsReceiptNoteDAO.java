package id.base.app.dao.transaction;

import id.base.app.AbstractHibernateDAO;
import id.base.app.SystemConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.StringFunction;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.inventory.GoodsReceiptNote;
import id.base.app.valueobject.inventory.GoodsReceiptNoteDetail;
import id.base.app.valueobject.master.Company;
import id.base.app.valueobject.master.CompanyLookup;
import id.base.app.valueobject.master.CompanyProduct;
import id.base.app.valueobject.master.CompanyWarehouse;
import id.base.app.valueobject.party.Party;

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
public class GoodsReceiptNoteDAO extends AbstractHibernateDAO<GoodsReceiptNote, Long> implements IGoodsReceiptNoteDAO {

	@Override
	public GoodsReceiptNote findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(GoodsReceiptNote anObject) throws SystemException {
		if(anObject.getPkGoodsReceiptNote()!=null){
			super.update(anObject);
		}else{
			anObject.setGrnNumber(generateGoodsReceiptNoteNumber(anObject.getCompany().getPkCompany()));
			super.create(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		for(Long objectPK : objectPKs){
			GoodsReceiptNote obj = findById(objectPK);
				obj.setStatus(SystemConstant.ValidFlag.INVALID);
			super.update(obj);
		}
	}

	@Override
	public List<GoodsReceiptNote> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<GoodsReceiptNote> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}
	
	@Override
	public GoodsReceiptNote getGoodsReceiptNoteById(Long maintenancePK) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias("company", "company");
			crit.createAlias("grnFrom", "grnFrom");
			crit.createAlias("companyWarehouse", "companyWarehouse");
			crit.createAlias("supplier", "supplier");
			crit.createAlias("currency", "currency");
			crit.createAlias("partyReference", "partyReference", JoinType.LEFT_OUTER_JOIN);
			crit.add(Restrictions.eq("pkGoodsReceiptNote", maintenancePK));
			crit.setProjection(Projections.projectionList().
				add(Projections.property("pkGoodsReceiptNote")). //0
				add(Projections.property("company.pkCompany")). //1
				add(Projections.property("company.name")). //2
				add(Projections.property("grnFrom.pkCompanyLookup")). //3
				add(Projections.property("companyWarehouse.pkCompanyWarehouse")). //4
				add(Projections.property("supplier.pkParty")). //5
				add(Projections.property("supplier.name")). //6
				add(Projections.property("currency.pkCompanyLookup")). //7
				add(Projections.property("quantity")). //8
				add(Projections.property("subvalue")). //9
				add(Projections.property("vatPercent")). //10
				add(Projections.property("vatValue")). //11
				add(Projections.property("totalValue")). //12
				add(Projections.property("goodsReceiptNoteDate")). //13
				add(Projections.property("partyReference.pkParty")). //14
				add(Projections.property("partyReference.name")). //15
				add(Projections.property("isPosted")). //16
				add(Projections.property("grnNumber")). //17
				add(Projections.property("status"))); //18
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					GoodsReceiptNote grn = new GoodsReceiptNote();
					try{
						BeanUtils.copyProperty(grn, "pkGoodsReceiptNote", tuple[0]);
						if(tuple[1]!=null){
							Company cm = new Company();
							BeanUtils.copyProperty(cm, "pkCompany", tuple[1]);
							BeanUtils.copyProperty(cm, "name", tuple[2]);
							BeanUtils.copyProperty(grn, "company", cm);
						}
						if(tuple[3]!=null){
							CompanyLookup cl = new CompanyLookup();
							BeanUtils.copyProperty(cl, "pkCompanyLookup", tuple[3]);
							BeanUtils.copyProperty(grn, "grnFrom", cl);
						}
						if(tuple[4]!=null){
							CompanyWarehouse cw = new CompanyWarehouse();
							BeanUtils.copyProperty(cw, "pkCompanyWarehouse", tuple[4]);
							BeanUtils.copyProperty(grn, "companyWarehouse", cw);
						}
						if(tuple[5]!=null){
							Party p = new Party();
							BeanUtils.copyProperty(p, "pkParty", tuple[5]);
							BeanUtils.copyProperty(p, "name", tuple[6]);
							BeanUtils.copyProperty(grn, "supplier", p);
						}
						if(tuple[7]!=null){
							CompanyLookup cl = new CompanyLookup();
							BeanUtils.copyProperty(cl, "pkCompanyLookup", tuple[7]);
							BeanUtils.copyProperty(grn, "currency", cl);
						}
						BeanUtils.copyProperty(grn, "quantity", tuple[8]);
						BeanUtils.copyProperty(grn, "subvalue", tuple[9]);
						BeanUtils.copyProperty(grn, "vatPercent", tuple[10]);
						BeanUtils.copyProperty(grn, "vatValue", tuple[11]);
						BeanUtils.copyProperty(grn, "totalValue", tuple[12]);
						BeanUtils.copyProperty(grn, "goodsReceiptNoteDate", tuple[13]);
						if(tuple[14]!=null){
							Party p = new Party();
							BeanUtils.copyProperty(p, "pkParty", tuple[14]);
							BeanUtils.copyProperty(p, "name", tuple[15]);
							BeanUtils.copyProperty(grn, "partyReference", p);
						}
						BeanUtils.copyProperty(grn, "isPosted", tuple[16]);
						BeanUtils.copyProperty(grn, "grnNumber", tuple[17]);
						BeanUtils.copyProperty(grn, "status", tuple[18]);
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
					return grn;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		GoodsReceiptNote grn = (GoodsReceiptNote) crit.uniqueResult();
		if(null != grn) {
			grn.setGoodsReceiptNoteDetails(getGoodsReceiptNoteDetails(maintenancePK));
		}
		return grn;
	}
	
	public List<GoodsReceiptNoteDetail> getGoodsReceiptNoteDetails(Long maintenancePK) throws SystemException {
		Criteria crit = getSession().createCriteria(GoodsReceiptNoteDetail.class);
			crit.createAlias("goodsReceiptNote", "goodsReceiptNote");
			crit.createAlias("product", "product");
			crit.createAlias("uom", "uom");
			crit.add(Restrictions.eq("goodsReceiptNote.pkGoodsReceiptNote", maintenancePK));
			crit.addOrder(Order.asc("sequenceNumber"));
			crit.setProjection(Projections.projectionList().
					add(Projections.property("pkGoodsReceiptNoteDetail")). //0
					add(Projections.property("product.pkCompanyProduct")). //1
					add(Projections.property("product.name")). //2
					add(Projections.property("uom.pkLookup")). //3
					add(Projections.property("quantity")). //4
					add(Projections.property("price")). //5
					add(Projections.property("value")). //6
					add(Projections.property("sequenceNumber")) //7 
			);
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					GoodsReceiptNoteDetail pod = new GoodsReceiptNoteDetail();
					try{
						BeanUtils.copyProperty(pod, "pkGoodsReceiptNoteDetail", tuple[0]);
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
						BeanUtils.copyProperty(pod, "price", tuple[5]);
						BeanUtils.copyProperty(pod, "value", tuple[6]);
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
	public String generateGoodsReceiptNoteNumber(Long pkCompany) throws SystemException {
		String grnNumber = "";
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias("company", "company");
			crit.add(Restrictions.eq("company.pkCompany", pkCompany));
			crit.addOrder(Order.desc("grnNumber"));
			crit.setProjection(Projections.projectionList().add(Projections.property("grnNumber")));
			crit.setMaxResults(1);
		Object returnObj = crit.uniqueResult();
		if(returnObj != null && StringFunction.isNotEmpty(returnObj.toString())){
			Long grnNumberLong = Long.valueOf(returnObj.toString());
			grnNumber = String.format("%05d", ++grnNumberLong);
		}else{
			grnNumber = String.format("%05d", 1);
		}
		return grnNumber;
	}
}