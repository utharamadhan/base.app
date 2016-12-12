package id.padiku.app.dao.party;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.SystemConstant;
import id.padiku.app.SystemParameter;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.StringFunction;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.AppParameter;
import id.padiku.app.valueobject.master.Company;
import id.padiku.app.valueobject.party.Party;
import id.padiku.app.valueobject.party.PartyCompany;
import id.padiku.app.valueobject.party.PartyRole;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class PartyDAO extends AbstractHibernateDAO<Party,Long> implements IPartyDAO {

	public void delete(Long[] objectPKs) throws SystemException {
		for(Long objectPK : objectPKs){
			Party obj = findById(objectPK);
				obj.setStatus(SystemConstant.ValidFlag.INVALID);
			super.update(obj);
		}
	}
	
	public List<Party> findAll(){
		return super.findAll();
	}

	public void saveOrUpdate(AppParameter anObject) throws SystemException {
    	 super.update(anObject);
    	 SystemParameter.updateSystemEnvironment(anObject.getName(), anObject.getValue());
	}

	public PagingWrapper<Party> findAllParameterWithFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public Party findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(Party anObject) throws SystemException {
		if(anObject.getPkParty()!=null){
			super.update(anObject);
		}else{
			super.create(anObject);
		}
	}

	@Override
	public List<Party> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Party> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}
	
	@Override
	public List<Party> findAllPartyByRole(Long pkCompany, String roleCode, String keyword) {
		Criteria criteria = getSession().createCriteria(domainClass);
			criteria.createAlias("partyRoles", "partyRoles");
			criteria.createAlias("partyRoles.role", "roleLookup");
			criteria.createAlias("partyCompanies", "partyCompanies");
			criteria.createAlias("partyCompanies.company", "company");
			if(StringFunction.isNotEmpty(keyword)){
				criteria.add(Restrictions.like("name", "%"+keyword+"%").ignoreCase());
			}
			criteria.add(Restrictions.eq("roleLookup.code", roleCode));
			criteria.add(Restrictions.eq("company.pkCompany", pkCompany));
			criteria.setProjection(Projections.projectionList().
						add(Projections.distinct(Projections.property("pkParty"))).
						add(Projections.property("name"))
					);
			criteria.addOrder(Order.asc("name"));
			criteria.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					Party p = new Party();
					try{
						BeanUtils.copyProperty(p, "pkParty", tuple[0]);
						BeanUtils.copyProperty(p, "name", tuple[1]);
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
					return p;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		return criteria.list();
	}
	
	@Override
	public PartyRole findPartyRole(String partyRoleCode, Long pkParty) throws SystemException {
		Criteria crit = getSession().createCriteria(PartyRole.class);
			crit.createAlias("party", "party");
			crit.createAlias("role", "role");
			crit.add(Restrictions.eq("party.pkParty", pkParty));
			crit.add(Restrictions.eq("role.code", partyRoleCode));
			crit.setMaxResults(1);
		return (PartyRole) crit.uniqueResult();
	}
	
	@Override
	public PartyRole findPartyRole(Long pkParty) throws SystemException {
		Criteria crit = getSession().createCriteria(PartyRole.class);
			crit.createAlias("party", "party");
			crit.createAlias("role", "role");
			crit.add(Restrictions.eq("party.pkParty", pkParty));
			crit.setMaxResults(1);
		return (PartyRole) crit.uniqueResult();
	}

	@Override
	public PartyCompany findPartyCompanyByPartyAndCompany(Long pkCompany, Long pkParty) throws SystemException {
		Criteria crit = getSession().createCriteria(PartyCompany.class);
			crit.createAlias("company", "company");
			crit.createAlias("party", "party");
			crit.add(Restrictions.eq("company.pkCompany", pkCompany));
			crit.add(Restrictions.eq("party.pkParty", pkParty));
			crit.setMaxResults(1);
			crit.setProjection(Projections.projectionList().
					add(Projections.property("pkPartyCompany")).
					add(Projections.property("company.pkCompany")).
					add(Projections.property("party.pkParty")));
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					PartyCompany pc = new PartyCompany();
					try{
						BeanUtils.copyProperty(pc, "pkPartyCompany", tuple[0]);
						if(tuple[1]!=null){
							Company co = new Company();
							BeanUtils.copyProperty(co, "pkCompany", tuple[1]);
							BeanUtils.copyProperty(pc, "company", co);
						}
						if(tuple[2]!=null){
							Party pa = new Party();
							BeanUtils.copyProperty(pa, "pkParty", tuple[2]);
							BeanUtils.copyProperty(pc, "party", pa);
						}
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
					return pc;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		return (PartyCompany) crit.uniqueResult();
	}
	
}