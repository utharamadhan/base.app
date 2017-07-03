package id.base.app.dao.publication;

import id.base.app.AbstractHibernateDAO;
import id.base.app.ILookupConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Pages;
import id.base.app.valueobject.publication.HousingIndex;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class HousingIndexDAO extends AbstractHibernateDAO<HousingIndex, Long> implements IHousingIndexDAO {

	@Override
	public HousingIndex findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(HousingIndex anObject) throws SystemException {
		if (anObject.getPkHousingIndex()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<HousingIndex> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			HousingIndex object = new HousingIndex();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<HousingIndex> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<HousingIndex> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}
	
	@Override
	public void updateLinkDetail(String linkUrl) {
		String updateQuery = "UPDATE LINK_URL SET URL = ? WHERE TYPE = 'HI'";
		SQLQuery sqlQuery = getSession().createSQLQuery(updateQuery);
		sqlQuery.setString(0, linkUrl);
		sqlQuery.executeUpdate();
	}
	
	@Override
	public String getLinkUrlDetail() throws SystemException {
		Query query = getSession().createSQLQuery("SELECT url FROM LINK_URL WHERE TYPE = 'HI' AND STATUS = 1 LIMIT 1");
		return ((String) query.uniqueResult()).toString();
	}

	@Override
	public List<HousingIndex> findSimpleData() throws SystemException {
		Criteria crit = this.getSession().createCriteria(domainClass);
		crit.add(Restrictions.eq(HousingIndex.STATUS, ILookupConstant.Status.PUBLISH));
		crit.addOrder(Order.asc(Pages.ORDER_NO));
		crit.setProjection(Projections.projectionList().
				add(Projections.property(HousingIndex.TITLE)).
				add(Projections.property(HousingIndex.VALUE)));
		crit.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				HousingIndex obj = new HousingIndex();
				try {
					BeanUtils.copyProperty(obj, HousingIndex.TITLE, tuple[0]);
					BeanUtils.copyProperty(obj, HousingIndex.VALUE, tuple[1]);
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
				}
				return obj;
			}
			
			@Override
			public List transformList(List collection) {
				return collection;
			}
		});
		return crit.list();
	}
}