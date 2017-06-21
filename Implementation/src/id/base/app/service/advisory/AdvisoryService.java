package id.base.app.service.advisory;

import id.base.app.dao.advisory.IAdvisoryDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.advisory.AdvisoryConsulting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdvisoryService implements IAdvisoryService {

	@Autowired
	private IAdvisoryDAO advisoryDAO;
    
	public PagingWrapper<AdvisoryConsulting> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public AdvisoryConsulting findById(Long id) throws SystemException {
		return advisoryDAO.findById(id);
	}

	public void saveOrUpdate(AdvisoryConsulting anObject) throws SystemException {
		advisoryDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		advisoryDAO.delete(objectPKs);
	}

	public PagingWrapper<AdvisoryConsulting> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return advisoryDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<AdvisoryConsulting> findObjects(Long[] objectPKs) throws SystemException {
		List<AdvisoryConsulting> objects = new ArrayList<>();
		AdvisoryConsulting object = null;
		for(Long l:objectPKs){
			object = advisoryDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<AdvisoryConsulting> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return advisoryDAO.findAll(filter, order);
	}

}
