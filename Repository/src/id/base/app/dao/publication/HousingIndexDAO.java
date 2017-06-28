package id.base.app.dao.publication;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.publication.HousingIndex;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
	

}