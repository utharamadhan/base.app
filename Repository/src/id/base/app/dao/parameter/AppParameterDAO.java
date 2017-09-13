package id.base.app.dao.parameter;

import id.base.app.AbstractHibernateDAO;
import id.base.app.SystemParameter;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.AppParameter;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class AppParameterDAO extends AbstractHibernateDAO<AppParameter,Long> implements IAppParameterDAO {

	public void delete(Long[] objectPKs) throws SystemException {
	}
	
	public List<AppParameter> findAll(){
		return super.findAll();
	}
	

	public AppParameter findAppParameterById(Long id) throws SystemException {
		return super.findByPK(new Long(id));
	}
	
	@Override
	public AppParameter findAppParameterByName(String name) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
		crit.add(Restrictions.eq(AppParameter.NAME, name));
		return (AppParameter) crit.uniqueResult();
	}

	public void saveOrUpdate(AppParameter anObject) throws SystemException {
    	 super.update(anObject);
    	 SystemParameter.updateSystemEnvironment(anObject.getName(), anObject.getValue());
	}

	public PagingWrapper<AppParameter> findAllParameterWithFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public Map<String,String> findParameterPairValue(String likeName) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
		ProjectionList projectionList = Projections
				.projectionList();
		projectionList.add(Projections.property(AppParameter.NAME));
		projectionList.add(Projections.property(AppParameter.VALUE));
		crit.add(Restrictions.like(AppParameter.NAME, likeName));
		crit.setProjection(projectionList);
		crit.setResultTransformer(new ResultTransformer() {
			
			private static final long serialVersionUID = 1023277533183757027L;

			@Override
			public AppParameter transformTuple(Object[] arg0, String[] arg1) {
				return AppParameter.getInstance(String.valueOf(arg0[0]), String.valueOf(arg0[1]));
			}
			
			@Override
			public List transformList(List arg0) {
				return arg0;
			}
		});
		List<AppParameter> params = crit.list();
		Map<String,String> pairValues = new HashMap<String, String>();
		for (AppParameter appParameter : params) {
			pairValues.put(appParameter.getName(), appParameter.getValue());
		}
		return pairValues;
	}


	@Override
	public List<AppParameter> findParametersByNames(List<SearchFilter> names)
			throws SystemException {
		return super.findAll(names, null);
	}
	
	@Override
	public List<AppParameter> getAllSocialMedia() throws SystemException {
		String query = "SELECT NAME, VALUE, ATTRIBUTE FROM APP_PARAMETER WHERE NAME LIKE 'SOCMED_URL%' AND VALUE IS NOT NULL AND VALUE != '' ORDER BY ORDER_NO ASC";
		SQLQuery sqlQuery = getSession().createSQLQuery(query);
			sqlQuery.setResultTransformer(new ResultTransformer() {

				private static final long serialVersionUID = 4589424464717278992L;

				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					AppParameter ap = new AppParameter();
					try {
						BeanUtils.copyProperty(ap, "name", tuple[0]);
						BeanUtils.copyProperty(ap, "value", tuple[1]);
						BeanUtils.copyProperty(ap, "attribute", tuple[2]);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					return ap;
				}
				
				@SuppressWarnings("rawtypes")
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});

		return sqlQuery.list();
	}
	
}
