package id.base.app.service.course;

import id.base.app.dao.course.IGroupCourseDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.course.GroupCourse;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GroupCourseService implements IGroupCourseService {

	@Autowired
	private IGroupCourseDAO groupCourseDAO;
    
	public PagingWrapper<GroupCourse> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public GroupCourse findById(Long id) throws SystemException {
		return groupCourseDAO.findById(id);
	}

	public void saveOrUpdate(GroupCourse anObject) throws SystemException {
		groupCourseDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		groupCourseDAO.delete(objectPKs);
	}

	public PagingWrapper<GroupCourse> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return groupCourseDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<GroupCourse> findObjects(Long[] objectPKs) throws SystemException {
		List<GroupCourse> objects = new ArrayList<>();
		GroupCourse object = null;
		for(Long l:objectPKs){
			object = groupCourseDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<GroupCourse> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return groupCourseDAO.findAll(filter, order);
	}

}
