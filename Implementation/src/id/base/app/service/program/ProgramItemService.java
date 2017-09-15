package id.base.app.service.program;

import id.base.app.dao.program.IProgramItemDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.program.ProgramItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.collection.spi.PersistentCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProgramItemService implements IProgramItemService {

	@Autowired
	private IProgramItemDAO programItemDAO;
    
	public PagingWrapper<ProgramItem> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public ProgramItem findById(Long id) throws SystemException {
		ProgramItem obj = programItemDAO.findById(id);
		if(obj.getCategories() instanceof PersistentBag) {
			((PersistentCollection) obj.getCategories() ).forceInitialization();
		}
		if(obj.getTeachers() instanceof PersistentBag) {
			((PersistentCollection) obj.getTeachers() ).forceInitialization();
		}
		if(obj.getMenus() instanceof PersistentBag) {
			((PersistentCollection) obj.getMenus() ).forceInitialization();
		}
		if(obj.getImages() instanceof PersistentBag) {
			((PersistentCollection) obj.getImages() ).forceInitialization();
		}
		if(obj.getTestimonials() instanceof PersistentBag) {
			((PersistentCollection) obj.getTestimonials() ).forceInitialization();
		}
		
		return obj;
	}

	public void saveOrUpdate(ProgramItem anObject) throws SystemException {
		programItemDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		programItemDAO.delete(objectPKs);
	}

	public PagingWrapper<ProgramItem> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return programItemDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<ProgramItem> findObjects(Long[] objectPKs) throws SystemException {
		List<ProgramItem> objects = new ArrayList<>();
		ProgramItem object = null;
		for(Long l:objectPKs){
			object = programItemDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<ProgramItem> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return programItemDAO.findAll(filter, order);
	}

	@Override
	public List<ProgramItem> findAllCourseCodeName() throws SystemException {
		return programItemDAO.findAllCourseCodeName();
	}

	@Override
	public List<ProgramItem> findAllCourseAndTags(Map<String, Object> params) throws SystemException {
		return programItemDAO.findAllCourseAndTags(params);
	}

	@Override
	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException {
		return programItemDAO.getSamePermalink(pk, permalink);
	}
	
	@Override
	public ProgramItem findByPermalink(String permalink) throws SystemException {
		ProgramItem obj = programItemDAO.findByPermalink(permalink);
		if(obj.getTeachers() instanceof PersistentBag) {
			((PersistentCollection) obj.getTeachers() ).forceInitialization();
		}
		if(obj.getMenus() instanceof PersistentBag) {
			((PersistentCollection) obj.getMenus() ).forceInitialization();
		}
		if(obj.getImages() instanceof PersistentBag) {
			((PersistentCollection) obj.getImages() ).forceInitialization();
		}
		if(obj.getTestimonials() instanceof PersistentBag) {
			((PersistentCollection) obj.getTestimonials() ).forceInitialization();
		}
		return obj;
	}

	@Override
	public void updateAnyUrl(Long pkProgramItem, ProgramItem programItem) throws SystemException {
		programItemDAO.updateAnyUrl(pkProgramItem, programItem);
	}
	
	@Override
	public List<ProgramItem> findForSelectEligibleReg(Long pkCategory) throws SystemException {
		return programItemDAO.findForSelectEligibleReg(pkCategory);
	}
	
	@Override
	public List<ProgramItem> findForSelectEligibleRegByCategoryPermalink(String categoryPermalink) throws SystemException{
		return programItemDAO.findForSelectEligibleRegByCategoryPermalink(categoryPermalink);
	}
	
	@Override
	public List<ProgramItem> findForSelectByType(String type) throws SystemException {
		return programItemDAO.findForSelectByType(type);
	}
	
}
