package id.base.app.dao.master;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchAlias;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.AppUser;
import id.base.app.valueobject.master.Company;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDAO extends AbstractHibernateDAO<Company, Long> implements ICompanyDAO {

	@Override
	public Company findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(Company anObject) throws SystemException {
		if(anObject.getPkCompany()!=null){
			super.update(anObject);
		}else{
			super.create(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<Company> objectList = new ArrayList<Company>();
		for(Long objectPK : objectPKs){
			Company obj = findById(objectPK);
			objectList.add(obj);
		}
		super.delete(objectList);
	}

	@Override
	public List<Company> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Company> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findFetchedPropertyWithPagingWrapper(startNo, offset, new SearchAlias[]{new SearchAlias("partyCompanies", "partyCompanies"), new SearchAlias("partyCompanies.party", "partyCompanies.party")}, Company.MAINTENANCE_LIST_FIELDS ,filter, order, null);
	}

	@Override
	public Company getCompanyByUser(Long pkAppUser) throws SystemException {
		Criteria criteria = getSession().createCriteria(AppUser.class);
			criteria.createAlias("party", "party");
			criteria.createAlias("party.partyCompanies", "partyCompany");
			criteria.createAlias("partyCompany.company", "company");
			criteria.add(Restrictions.eq("pkAppUser", pkAppUser));
			criteria.setMaxResults(1);
			criteria.setProjection(Projections.projectionList().
					add(Projections.property("company.pkCompany")).
					add(Projections.property("company.name"))
			);		
			criteria.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					Company company = new Company();
					try{
						company.setPkCompany(Long.valueOf(tuple[0].toString()));
						company.setName(tuple[1].toString());
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
					return company;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		return (Company) criteria.uniqueResult();
	}


	@Override
	public List<Company> getCompaniesByUser(Long pkAppUser) throws SystemException {
		Criteria criteria = getSession().createCriteria(AppUser.class);
			criteria.createAlias("party", "party");
			criteria.createAlias("party.partyCompanies", "partyCompany");
			criteria.createAlias("partyCompany.company", "company");
			criteria.add(Restrictions.eq("pkAppUser", pkAppUser));
			criteria.addOrder(Order.asc("company.createdBy"));
			criteria.setProjection(Projections.projectionList().
					add(Projections.property("company.pkCompany")).
					add(Projections.property("company.name"))
			);		
			criteria.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					Company company = new Company();
					try{
						company.setPkCompany(Long.valueOf(tuple[0].toString()));
						company.setName(tuple[1].toString());
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
					return company;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		return criteria.list();
	}
}
