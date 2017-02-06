package id.base.app.service.advisory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.base.app.dao.advisory.IAdvisoryMenuDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.advisory.AdvisoryMenu;

@Service
@Transactional
public class AdvisoryMenuService implements IAdvisoryMenuService {

	@Autowired
	private IAdvisoryMenuDAO advisoryMenuDAO;
    
	public PagingWrapper<AdvisoryMenu> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public AdvisoryMenu findById(Long id) throws SystemException {
		return advisoryMenuDAO.findById(id);
	}

	public void saveOrUpdate(AdvisoryMenu anObject) throws SystemException {
		advisoryMenuDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		advisoryMenuDAO.delete(objectPKs);
	}

	public PagingWrapper<AdvisoryMenu> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return advisoryMenuDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<AdvisoryMenu> findObjects(Long[] objectPKs) throws SystemException {
		List<AdvisoryMenu> objects = new ArrayList<>();	
		AdvisoryMenu object = null;
		for(Long l:objectPKs){
			object = advisoryMenuDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<AdvisoryMenu> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return advisoryMenuDAO.findAll(filter, order);
	}


}
