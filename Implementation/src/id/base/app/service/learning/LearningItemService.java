package id.base.app.service.learning;

import id.base.app.dao.learning.ILearningItemDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.learning.LearningItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.collection.spi.PersistentCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LearningItemService implements ILearningItemService {

	@Autowired
	private ILearningItemDAO learningItemDAO;
    
	public PagingWrapper<LearningItem> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public LearningItem findById(Long id) throws SystemException {
		LearningItem obj = learningItemDAO.findById(id);
		if(obj.getCategories() instanceof PersistentBag) {
			((PersistentCollection) obj.getCategories() ).forceInitialization();
		}
		if(obj.getTeachers() instanceof PersistentBag) {
			((PersistentCollection) obj.getTeachers() ).forceInitialization();
		}
		return obj;
	}

	public void saveOrUpdate(LearningItem anObject) throws SystemException {
		learningItemDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		learningItemDAO.delete(objectPKs);
	}

	public PagingWrapper<LearningItem> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return learningItemDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<LearningItem> findObjects(Long[] objectPKs) throws SystemException {
		List<LearningItem> objects = new ArrayList<>();
		LearningItem object = null;
		for(Long l:objectPKs){
			object = learningItemDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<LearningItem> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return learningItemDAO.findAll(filter, order);
	}

	@Override
	public List<LearningItem> findAllCourseCodeName() throws SystemException {
		return learningItemDAO.findAllCourseCodeName();
	}

	@Override
	public List<LearningItem> findAllCourseAndTags(Map<String, Object> params) throws SystemException {
		return learningItemDAO.findAllCourseAndTags(params);
	}

	@Override
	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException {
		return learningItemDAO.getSamePermalink(pk, permalink);
	}
	
	@Override
	public LearningItem findByPermalink(String permalink) throws SystemException {
		return learningItemDAO.findByPermalink(permalink);
	}

	@Override
	public void updateThumb(Long pkLearningItem, String thumbURL) throws SystemException {
		learningItemDAO.updateThumb(pkLearningItem, thumbURL);
	}
}
