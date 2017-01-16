package id.base.app.service.aboutUs;

import id.base.app.dao.aboutUs.IProgramPostDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.aboutUs.ProgramPost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProgramPostService implements IProgramPostService {

	@Autowired
	private IProgramPostDAO programPostDAO;
    
	public PagingWrapper<ProgramPost> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public ProgramPost findById(Long id) throws SystemException {
		return programPostDAO.findById(id);
	}

	public void saveOrUpdate(ProgramPost anObject) throws SystemException {
		programPostDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		programPostDAO.delete(objectPKs);
	}

	public PagingWrapper<ProgramPost> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return programPostDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<ProgramPost> findObjects(Long[] objectPKs) throws SystemException {
		List<ProgramPost> objects = new ArrayList<>();
		ProgramPost object = null;
		for(Long l:objectPKs){
			object = programPostDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<ProgramPost> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return programPostDAO.findAll(filter, order);
	}

	@Override
	public List<ProgramPost> findLatest(int number) throws SystemException {
		return programPostDAO.findLatest(number);
	}

}
