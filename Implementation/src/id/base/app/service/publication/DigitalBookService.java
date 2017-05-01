package id.base.app.service.publication;

import id.base.app.dao.publication.IDigitalBookDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.publication.DigitalBook;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DigitalBookService implements IDigitalBookService {

	@Autowired
	private IDigitalBookDAO digitalBookDAO;
    
	public PagingWrapper<DigitalBook> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public DigitalBook findById(Long id) throws SystemException {
		return digitalBookDAO.findById(id);
	}

	public void saveOrUpdate(DigitalBook anObject) throws SystemException {
		digitalBookDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		digitalBookDAO.delete(objectPKs);
	}

	public PagingWrapper<DigitalBook> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return digitalBookDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<DigitalBook> findObjects(Long[] objectPKs) throws SystemException {
		List<DigitalBook> objects = new ArrayList<>();
		DigitalBook object = null;
		for(Long l:objectPKs){
			object = digitalBookDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<DigitalBook> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return digitalBookDAO.findAll(filter, order);
	}

	@Override
	public List<DigitalBook> findLatestEbook(int number) throws SystemException {
		return digitalBookDAO.findLatestEbook(number);
	}

	@Override
	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException {
		return digitalBookDAO.getSamePermalink(pk, permalink);
	}
	
	@Override
	public DigitalBook findByPermalink(String permalink) throws SystemException {
		return digitalBookDAO.findByPermalink(permalink);
	}
}
