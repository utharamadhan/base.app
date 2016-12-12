package id.padiku.app.dao.master;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.SystemConstant;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.master.Company;
import id.padiku.app.valueobject.master.CompanyWarehouse;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyWarehouseDAO extends AbstractHibernateDAO<CompanyWarehouse, Long> implements ICompanyWarehouseDAO {

	@Override
	public CompanyWarehouse findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(CompanyWarehouse anObject) throws SystemException {
		if(anObject.getPkCompanyWarehouse()!=null){
			super.update(anObject);
		}else{
			super.create(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<CompanyWarehouse> objectList = new ArrayList<CompanyWarehouse>();
		for(Long objectPK : objectPKs){
			CompanyWarehouse obj = findById(objectPK);
			objectList.add(obj);
		}
		super.delete(objectList);
	}

	@Override
	public List<CompanyWarehouse> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<CompanyWarehouse> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<CompanyWarehouse> findAllByCompany(Long pkCompany) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.createAlias("company", "company");
			crit.add(Restrictions.eq("company.pkCompany", pkCompany));
			crit.add(Restrictions.eq("status", SystemConstant.ValidFlag.VALID));
			crit.setProjection(Projections.projectionList().
					add(Projections.property(CompanyWarehouse.ID)).
					add(Projections.property(CompanyWarehouse.NAME)).
					add(Projections.property("company.pkCompany"))
				);
			crit.addOrder(Order.asc(CompanyWarehouse.NAME));
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					CompanyWarehouse cw = new CompanyWarehouse();
					try{
						cw.setPkCompanyWarehouse(Long.valueOf(tuple[0].toString()));
						cw.setName(tuple[1].toString());
						if(tuple[2]!=null){
							Company c = new Company();
							BeanUtils.copyProperty(c, "pkCompany", tuple[2]);
							BeanUtils.copyProperty(cw, "company", c);
						}
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
					return cw;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
			return crit.list();	
	}
}
