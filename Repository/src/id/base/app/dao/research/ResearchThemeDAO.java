package id.base.app.dao.research;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.research.ResearchTheme;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class ResearchThemeDAO extends AbstractHibernateDAO<ResearchTheme, Long> implements IResearchThemeDAO {

	@Override
	public ResearchTheme findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(ResearchTheme anObject) throws SystemException {
		if (anObject.getPkResearchTheme()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		List<ResearchTheme> objectList = new ArrayList<>();
		for(int i=0;i<objectPKs.length;i++){
			ResearchTheme object = ResearchTheme.getInstance();
			object = findById(objectPKs[i]);
			objectList.add(object);
		}
		super.delete(objectList);
	}

	@Override
	public List<ResearchTheme> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<ResearchTheme> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public List<ResearchTheme> findAllResearchThemeTitle() throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.setProjection(Projections.projectionList().
					add(Projections.property(ResearchTheme.PK_RESEARCH_THEME)).
					add(Projections.property(ResearchTheme.TITLE)));
			crit.setResultTransformer(new ResultTransformer() {
				private static final long serialVersionUID = 7242499844075874681L;
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					ResearchTheme rt = ResearchTheme.getInstance();
					try {
						rt.setPkResearchTheme(Long.valueOf(tuple[0].toString()));
						rt.setTitle(String.valueOf(tuple[1]));
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