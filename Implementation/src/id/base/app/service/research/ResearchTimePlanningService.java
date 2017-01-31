package id.base.app.service.research;

import id.base.app.dao.research.IResearchTimePlanningDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.research.ResearchTimePlanning;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResearchTimePlanningService implements IResearchTimePlanningService{

	@Autowired
	private IResearchTimePlanningDAO researchTimePlanningDAO;

	@Override
	public ResearchTimePlanning findById(Long id) throws SystemException {
		return researchTimePlanningDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(ResearchTimePlanning anObject)
			throws SystemException {
		researchTimePlanningDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		researchTimePlanningDAO.delete(objectPKs);
	}

	@Override
	public List<ResearchTimePlanning> findObjects(Long[] objectPKs)
			throws SystemException {
		List<ResearchTimePlanning> objects = new ArrayList<>();
		ResearchTimePlanning object = null;
		for(Long l:objectPKs){
			object = researchTimePlanningDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public PagingWrapper<ResearchTimePlanning> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return researchTimePlanningDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<ResearchTimePlanning> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return researchTimePlanningDAO.findAll(filter, order);
	}
	
}
