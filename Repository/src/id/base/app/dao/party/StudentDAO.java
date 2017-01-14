package id.base.app.dao.party;

import id.base.app.AbstractHibernateDAO;
import id.base.app.SystemConstant;
import id.base.app.SystemParameter;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.AppParameter;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.course.Course;
import id.base.app.valueobject.course.StudentCourse;
import id.base.app.valueobject.party.Student;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO extends AbstractHibernateDAO<Student,Long> implements IStudentDAO {

	public void delete(Long[] objectPKs) throws SystemException {
		for(Long objectPK : objectPKs){
			Student obj = findById(objectPK);
				obj.setStatus(SystemConstant.ValidFlag.INVALID);
			super.update(obj);
		}
	}
	
	public List<Student> findAll(){
		return super.findAll();
	}

	public void saveOrUpdate(AppParameter anObject) throws SystemException {
    	 super.update(anObject);
    	 SystemParameter.updateSystemEnvironment(anObject.getName(), anObject.getValue());
	}

	public PagingWrapper<Student> findAllParameterWithFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public Student findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(Student anObject) throws SystemException {
		if(anObject.getPkStudent()!=null){
			super.update(anObject);
		}else{
			super.create(anObject);
		}
	}

	@Override
	public List<Student> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Student> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public void enrollCourse(StudentCourse anObject) throws SystemException {
		if(anObject.getPkStudentCourse()!=null){
			super.update(anObject);
		}else{
			super.create(anObject);
		}
	}

	@Override
	public List<StudentCourse> findAllStudentCourses(Long pkStudent) throws SystemException {
		Criteria crit = getSession().createCriteria(StudentCourse.class);
			crit.createAlias("student", "student");
			crit.createAlias("course", "course");
			crit.createAlias("studentCourseStatusLookup", "studentCourseStatusLookup");
			crit.add(Restrictions.eq("student.pkStudent", pkStudent));
			crit.setProjection(Projections.projectionList().
					add(Projections.property("pkStudentCourse")).
					add(Projections.property("course.pkCourse")).
					add(Projections.property("course.name")).
					add(Projections.property("enrollDate")).
					add(Projections.property("studentCourseStatusLookup.pkLookup")).
					add(Projections.property("studentCourseStatusLookup.code")).
					add(Projections.property("studentCourseStatusLookup.name")).
					add(Projections.property("lastActionDate")));
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					StudentCourse sc = StudentCourse.getInstance();
					try {
						BeanUtils.copyProperty(sc, "pkStudentCourse", tuple[0]);
						if(tuple[1]!=null) {
							Course c = Course.getInstance();
							BeanUtils.copyProperty(c, "pkCourse", tuple[1]);
							BeanUtils.copyProperty(c, "name", tuple[2]);
							BeanUtils.copyProperty(sc, "course", c);
						}
						BeanUtils.copyProperty(sc, "enrollDate", tuple[3]);
						if(tuple[4]!=null) {
							Lookup l = new Lookup();
							BeanUtils.copyProperty(l, "pkLookup", tuple[4]);
							BeanUtils.copyProperty(l, "code", tuple[5]);
							BeanUtils.copyProperty(l, "name", tuple[6]);
							BeanUtils.copyProperty(sc, "studentCourseStatusLookup", l);
						}
						BeanUtils.copyProperty(sc, "lastActionDate", tuple[7]);
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
					}
					return sc;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		return crit.list();
	}
		
}