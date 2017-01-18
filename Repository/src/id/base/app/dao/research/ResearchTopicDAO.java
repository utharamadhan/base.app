package id.base.app.dao.research;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.research.ResearchTopic;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class ResearchTopicDAO extends AbstractHibernateDAO<ResearchTopic, Long> implements IResearchTopicDAO {

	@Override
	public ResearchTopic findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(ResearchTopic anObject) throws SystemException {
		if (anObject.getPkResearchTopic()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<ResearchTopic> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			ResearchTopic object = ResearchTopic.getInstance();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<ResearchTopic> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<ResearchTopic> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<ResearchTopic> findAllResearchTopicTitle() throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.setProjection(Projections.projectionList().
					add(Projections.property("pkResearchTopic")).
					add(Projections.property("title")));
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					ResearchTopic rt = ResearchTopic.getInstance();
					try {
						BeanUtils.copyProperty(rt, "pkResearchTopic", tuple[0]);
						BeanUtils.copyProperty(rt, "title", tuple[1]);
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
					}
					return rt;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		return crit.list();
	}

}