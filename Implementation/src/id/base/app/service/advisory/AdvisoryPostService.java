package id.base.app.service.advisory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.base.app.dao.advisory.IAdvisoryPostDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.advisory.AdvisoryPost;

@Service
@Transactional
public class AdvisoryPostService implements IAdvisoryPostService {

	@Autowired
	private IAdvisoryPostDAO advisoryPostDAO;
    
	public PagingWrapper<AdvisoryPost> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public AdvisoryPost findById(Long id) throws SystemException {
		return advisoryPostDAO.findById(id);
	}

	public void saveOrUpdate(AdvisoryPost anObject) throws SystemException {
		advisoryPostDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		advisoryPostDAO.delete(objectPKs);
	}

	public PagingWrapper<AdvisoryPost> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return advisoryPostDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<AdvisoryPost> findObjects(Long[] objectPKs) throws SystemException {
		List<AdvisoryPost> objects = new ArrayList<>();	
		AdvisoryPost object = null;
		for(Long l:objectPKs){
			object = advisoryPostDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<AdvisoryPost> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return advisoryPostDAO.findAll(filter, order);
	}


}
