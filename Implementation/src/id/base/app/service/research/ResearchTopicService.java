package id.base.app.service.research;

import id.base.app.dao.research.IResearchTopicDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.research.ResearchTopic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResearchTopicService implements IResearchTopicService {

	@Autowired
	private IResearchTopicDAO researchTopicDAO;
    
	public PagingWrapper<ResearchTopic> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public ResearchTopic findById(Long id) throws SystemException {
		return researchTopicDAO.findById(id);
	}

	public void saveOrUpdate(ResearchTopic anObject) throws SystemException {
		researchTopicDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		researchTopicDAO.delete(objectPKs);
	}

	public PagingWrapper<ResearchTopic> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return researchTopicDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<ResearchTopic> findObjects(Long[] objectPKs) throws SystemException {
		List<ResearchTopic> objects = new ArrayList<>();
		ResearchTopic object = null;
		for(Long l:objectPKs){
			object = researchTopicDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<ResearchTopic> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return researchTopicDAO.findAll(filter, order);
	}

}
