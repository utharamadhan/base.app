package id.base.app.dao.master;

import id.base.app.AbstractHibernateDAO;
import id.base.app.SystemConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.master.CompanyMachinery;
import id.base.app.valueobject.master.CompanyProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyProductDAO extends AbstractHibernateDAO<CompanyProduct, Long> implements ICompanyProductDAO {

	@Override
	public CompanyProduct findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(CompanyProduct anObject) throws SystemException {
		if(anObject.getPkCompanyProduct()==null){			
			super.create(anObject);
		} else {
			super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<CompanyProduct> objectList = new ArrayList<CompanyProduct>();
		for(Long objectPK : objectPKs){
			CompanyProduct obj = findById(objectPK);
			objectList.add(obj);
		}
		super.delete(objectList);
	}

	@Override
	public List<CompanyProduct> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<CompanyProduct> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<CompanyProduct> findAll(Long pkCompany) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias("company", "company");
			crit.add(Restrictions.eq("status", SystemConstant.ValidFlag.VALID));
			crit.add(Restrictions.eq("company.pkCompany", pkCompany));
		return crit.list();
	}

	@Override
	public List<CompanyProduct> findAll(Long pkCompany, String keyword) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias("company", "company");
			crit.add(Restrictions.eq("status", SystemConstant.ValidFlag.VALID));
			crit.add(Restrictions.eq("company.pkCompany", pkCompany));
			crit.add(Restrictions.ilike("name", "%"+keyword+"%"));
		return crit.list();
	}

	@Override
	public Boolean isMachineryUsedThisProduct(Long pkCompanyProduct) throws SystemException {
		Criteria crit = getSession().createCriteria(CompanyMachinery.class);
			crit.createAlias("companyProductFrom", "from");
			crit.createAlias("companyProductTo", "to");
			crit.add(Restrictions.or(Restrictions.eq("from.pkCompanyProduct", pkCompanyProduct), Restrictions.eq("to.pkCompanyProduct", pkCompanyProduct)));
			crit.setProjection(Projections.rowCount());
		Long rowCount = (Long) crit.uniqueResult();
		return rowCount != null && rowCount > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public List<CompanyProduct> findAllProductByUsageType(Long pkCompany, String usageItemType) throws SystemException {
		Criteria crit = getSession().createCriteria(CompanyProduct.class);
			crit.createAlias("type", "type");
			crit.createAlias("company", "company");
			crit.createAlias("uom", "uom");
			crit.add(Restrictions.like("type.usage", usageItemType, MatchMode.ANYWHERE));
			crit.add(Restrictions.eq("company.pkCompany", pkCompany));
			crit.add(Restrictions.eq("status", SystemConstant.ValidFlag.VALID));
			crit.setProjection(Projections.projectionList().
					add(Projections.property("pkCompanyProduct")).
					add(Projections.property("name")).
					add(Projections.property("uom.pkLookup")).
					add(Projections.property("buyingPrice")));
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					CompanyProduct cp = new CompanyProduct();
					try{
						cp.setPkCompanyProduct(Long.valueOf(tuple[0].toString()));
						cp.setName(tuple[1].toString());
						if (tuple[2]!=null) {
							Lookup lo = new Lookup();
							lo.setPkLookup(Long.valueOf(tuple[2].toString()));
							cp.setUom(lo);
						}
						BeanUtils.copyProperty(cp, "buyingPrice", tuple[3]);
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
					return cp;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		return crit.list();
	}
	
	@Override
	public List<CompanyProduct> findAllExistInStock(Long pkCompany) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias(CompanyProduct.COMPANY, CompanyProduct.COMPANY);
			crit.createAlias(CompanyProduct.STOCKS, CompanyProduct.STOCKS);
			crit.add(Restrictions.eq(CompanyProduct.STATUS, SystemConstant.ValidFlag.VALID));
			crit.add(Restrictions.eq(CompanyProduct.COMPANY_ID, pkCompany));
			crit.add(Restrictions.eq(CompanyProduct.STOCKS_STATUS, SystemConstant.ValidFlag.VALID));
			crit.add(Restrictions.ge(CompanyProduct.STOCKS_REMAINING_VOLUME_IN_KG, BigDecimal.ZERO));
			crit.setProjection(Projections.projectionList().
					add(Projections.groupProperty(CompanyProduct.ID)).
					add(Projections.property(CompanyProduct.NAME)));
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					CompanyProduct cp = new CompanyProduct();
					try{
						BeanUtils.copyProperty(cp, "pkCompanyProduct", tuple[0]);
						BeanUtils.copyProperty(cp, "name", tuple[1]);
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
					return cp;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		return crit.list();
	}
	
	@Override
	public Long countNotPredefinedAllByCompany(Long pkCompany) throws SystemException {
		Criteria crit = getSession().createCriteria(CompanyProduct.class);
		crit.setProjection(Projections.rowCount());
		crit.createAlias(CompanyProduct.COMPANY, CompanyProduct.COMPANY);
		crit.add(Restrictions.eq(CompanyProduct.COMPANY_ID, pkCompany));
		crit.add(Restrictions.eq(CompanyProduct.PREDEFINED, Boolean.FALSE));
		List ct = crit.list();
		if (ct!=null) {
			return (Long) ct.get(0);
		}else{
			return 0L;	
		}
	}
	
	@Override
	public int deleteAllByCompany(Long pkCompany) {
		String updateQuery = "DELETE FROM company_product WHERE fk_company = ?";
		SQLQuery sqlQuery = getSession().createSQLQuery(updateQuery);
		sqlQuery.setLong(0, pkCompany);
		return sqlQuery.executeUpdate();
	}
}