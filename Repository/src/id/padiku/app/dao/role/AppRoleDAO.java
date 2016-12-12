package id.padiku.app.dao.role;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.SystemConstant;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.Operator;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.util.dao.SearchOrder.Sort;
import id.padiku.app.valueobject.AppFunction;
import id.padiku.app.valueobject.AppRole;
import id.padiku.app.valueobject.AppRoleFunction;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class AppRoleDAO extends AbstractHibernateDAO<AppRole, Long> implements IAppRoleDAO{

	public void delete(Long[] objectPKs) throws SystemException {
		for(int i=0;i<objectPKs.length;i++){
			AppRole object = new AppRole();
			object = findAppRoleById(objectPKs[i]);
			super.update(object);
		}
	}

	public PagingWrapper<AppRole> findAllAppRole(int startNo, int offset)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset);
	}

	public AppRole findAppRoleByCode(String roleCode) throws SystemException {
		List<AppRole> ret = super.findByQueryString(" from AppRole where code = ? " , new Object[] { roleCode } );
        if(ret.size() > 0)
    	   return ret.get(0) ;
        else
        	return null;
	}
	
	@Override
	public AppRole findAppRoleByCodeIgnoreCase(String code) throws SystemException{
		Criteria crit = getSession().createCriteria(domainClass);
		crit.add(Restrictions.eq(AppRole.CODE, code).ignoreCase());
		return (AppRole) crit.uniqueResult();
	}
	
	@Override
	public AppRole findAppRoleByNameIgnoreCase(String name) throws SystemException{
		Criteria crit = getSession().createCriteria(domainClass);
		crit.add(Restrictions.eq(AppRole.NAME, name).ignoreCase());
		return (AppRole) crit.uniqueResult();
	}

	public AppRole findAppRoleById(Long id) throws SystemException {
		if(id==null){
			return new AppRole();
		}
		AppRole appRole = super.findByPK(new Long(id));
		Query query = getSession().createQuery(" from AppRoleFunction where appRole.pkAppRole = ? and appFunction.fkAppFunctionParent is null");
		query.setParameter(0, id);
		List<AppRoleFunction> parentFunctionList = query.list();
		List<AppFunction> appFunctionList = new ArrayList<AppFunction>(); 
		for (AppRoleFunction roleFunction:parentFunctionList){
			AppFunction parent = roleFunction.getAppFunction();
			query = getSession().createQuery(" from AppFunction where fkAppFunctionParent = ?");
			query.setParameter(0, Long.valueOf(String.valueOf(parent.getPkAppFunction())));
			List<AppFunction> child1List = query.list();
			for (AppFunction child:child1List){
				query = getSession().createQuery(" from AppFunction where fkAppFunctionParent = ?");
				query.setParameter(0, Long.valueOf(String.valueOf(child.getPkAppFunction())));
				List<AppFunction> child2List = query.list();
				child.setChildList(child2List);
			}
			parent.setChildList(child1List);
			appFunctionList.add(parent);
		}
		appRole.setFunctionList(appFunctionList);
		return appRole;
	}

	public AppRole findAppRoleByName(String roleName) throws SystemException {
		List<AppRole> ret = super.findByQueryString(" from AppRole where name = ? " , new Object[] { roleName } );
        if(ret.size() > 0)
    	   return ret.get(0) ;
        else
        	return null;
	}

	public void saveOrUpdate(AppRole anObject) throws SystemException {
		if(anObject.getPkAppRole()==null)
			 super.create(anObject);
	     else
	    	 super.update(anObject);
	}
	
	@Override
	public List<AppRole> findAppRolesByAppUserId(Long pkAppUser)
			throws SystemException {
		Criteria roleCriteria = getSession().createCriteria(AppRole.class)
				.setFetchMode(AppRole.USERS, FetchMode.EAGER).add(Restrictions.eq(AppRole.USERS, pkAppUser));

		List<AppRole> result = (List<AppRole>) roleCriteria.list();

		return result;
	}

	public List<AppRole> findAllRole() throws SystemException{
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(AppRole.NAME, Sort.ASC));
		
		return super.findAll(filter, order, null);
	}
	
	public List<AppRole> findExternalRoles() throws SystemException{
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(AppRole.TYPE, Operator.EQUALS, SystemConstant.USER_TYPE_EXTERNAL));
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(AppRole.NAME, Sort.ASC));
		
		return super.findAll(filter, order, null);
	}
	
	public List<AppRole> findInternalRoles() throws SystemException{
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(AppRole.TYPE, Operator.EQUALS, SystemConstant.USER_TYPE_INTERNAL));
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(AppRole.NAME, Sort.ASC));
		
		return super.findAll(filter, order, null);
	}

	@Override
	public PagingWrapper<AppRole> findAllAppRole(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<AppRole> findAllAppRole(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return super.findAll(filter, order, null);
	}

	@Override
	public AppRole findAppRoleByIdFetchUser(Long pkAppRole)
			throws SystemException {
		Criteria roleCriteria = getSession().createCriteria(AppRole.class)
				.setFetchMode(AppRole.USERS, FetchMode.EAGER);
		roleCriteria.add(Restrictions.eq(AppRole.PK_APP_ROLE, pkAppRole));

		AppRole result = (AppRole) roleCriteria.uniqueResult();

		return result;
	}
	
	
}
