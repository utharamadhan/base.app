package id.base.app.service.advisory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.base.app.dao.advisory.IAdvisorDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.aboutUs.Tutor;
import id.base.app.valueobject.advisory.Advisor;

@Service
@Transactional
public class AdvisorService implements IAdvisorService {

	@Autowired
	private IAdvisorDAO advisorDAO;
    
	public PagingWrapper<Tutor> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public Advisor findById(Long id) throws SystemException {
		return advisorDAO.findById(id);
	}

	public void saveOrUpdate(Advisor anObject) throws SystemException {
		advisorDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		advisorDAO.delete(objectPKs);
	}

	public PagingWrapper<Advisor> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return advisorDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<Advisor> findObjects(Long[] objectPKs) throws SystemException {
		List<Advisor> objects = new ArrayList<>();
		Advisor object = null;
		for(Long l:objectPKs){
			object = advisorDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<Advisor> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return advisorDAO.findAll(filter, order);
	}

}
