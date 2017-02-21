package id.base.app.service.aboutUs;

import id.base.app.dao.aboutUs.IEngagementDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.aboutUs.Engagement;
import id.base.app.valueobject.publication.DigitalBook;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EngagementService implements IEngagementService {

	@Autowired
	private IEngagementDAO engagementDAO;
    
	public PagingWrapper<Engagement> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public Engagement findById(Long id) throws SystemException {
		return engagementDAO.findById(id);
	}

	public void saveOrUpdate(Engagement anObject) throws SystemException {
		engagementDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		engagementDAO.delete(objectPKs);
	}

	public PagingWrapper<Engagement> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return engagementDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<Engagement> findObjects(Long[] objectPKs) throws SystemException {
		List<Engagement> objects = new ArrayList<>();
		Engagement object = null;
		for(Long l:objectPKs){
			object = engagementDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<Engagement> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return engagementDAO.findAll(filter, order);
	}

	@Override
	public List<Engagement> findLatest(int number) throws SystemException {
		return engagementDAO.findLatest(number);
	}

}