package id.base.app.service.aboutUs;

import id.base.app.dao.aboutUs.ITutorDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.aboutUs.Tutor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TutorService implements ITutorService {

	@Autowired
	private ITutorDAO tutorDAO;
    
	public PagingWrapper<Tutor> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public Tutor findById(Long id) throws SystemException {
		return tutorDAO.findById(id);
	}

	public void saveOrUpdate(Tutor anObject) throws SystemException {
		tutorDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		tutorDAO.delete(objectPKs);
	}

	public PagingWrapper<Tutor> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return tutorDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<Tutor> findObjects(Long[] objectPKs) throws SystemException {
		List<Tutor> objects = new ArrayList<>();
		Tutor object = null;
		for(Long l:objectPKs){
			object = tutorDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<Tutor> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return tutorDAO.findAll(filter, order);
	}

	@Override
	public List<Tutor> findAllTutorCodeAndName() throws SystemException {
		return tutorDAO.findAllTutorCodeAndName();
	}

}
