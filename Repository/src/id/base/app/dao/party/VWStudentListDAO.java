package id.base.app.dao.party;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchAlias;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.party.VWStudentList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.SerializationUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class VWStudentListDAO extends AbstractHibernateDAO<VWStudentList, Long> implements IVWStudentListDAO {

	public void delete(Long[] objectPKs) throws SystemException {}
	
	public List<VWStudentList> findAll(){
		return super.findAll();
	}

	public PagingWrapper<VWStudentList> findAllParameterWithFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public VWStudentList findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(VWStudentList anObject) throws SystemException {}

	@Override
	public List<VWStudentList> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<VWStudentList> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		SearchAlias[] searchAliases = new SearchAlias[0];
		String[] fetchProperties = new String[]{
				VWStudentList.NAME,
				VWStudentList.PHONE_NUMBER,
				VWStudentList.EMAIL,
				VWStudentList.STUDENT_STATUS_LOOKUP_NAME};
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(VWStudentList.class);
		DetachedCriteria clonedDetachedCriteria = (DetachedCriteria)SerializationUtils.clone(detachedCriteria);
		List<Criterion> criterionList = new LinkedList<Criterion>();
		List<Order> orderList = new LinkedList<Order>();
		if (filter != null) {
			for (SearchFilter f : filter) {
				criterionList.add(buildCriterion(f));
			}
		}
		if (order != null) {
			for (SearchOrder o : order) {
				orderList.add(buildOrder(o));
			}
		}
		long _totalRecords = getRowCount(detachedCriteria, searchAliases ,criterionList);
		
		Criteria criteria = clonedDetachedCriteria.getExecutableCriteria(getSession());
		if (fetchProperties != null) {
			ProjectionList projectionList = Projections.projectionList().add(Projections.distinct(Projections.property(VWStudentList.PK_STUDENT)));
			for (String fetchedProperty : fetchProperties) {
				projectionList.add(Projections.property(fetchedProperty));
			}
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new ResultTransformer() {
				@Override
				public VWStudentList transformTuple(Object[] tuple, String[] aliases) {
					VWStudentList stu = VWStudentList.getInstance();
					try {
						BeanUtils.copyProperty(stu, VWStudentList.PK_STUDENT, tuple[0]);
						BeanUtils.copyProperty(stu, VWStudentList.NAME, tuple[1]);
						BeanUtils.copyProperty(stu, VWStudentList.PHONE_NUMBER, tuple[2]);
						BeanUtils.copyProperty(stu, VWStudentList.EMAIL, tuple[3]);
						BeanUtils.copyProperty(stu, VWStudentList.STUDENT_STATUS_LOOKUP_NAME, tuple[4]);
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
					}
					return stu;
				}
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		}
		
		for (Criterion criterion : criterionList) {
			criteria.add(criterion);
		}

		for (Order o : orderList) {
			criteria.addOrder(o);
		}

		List<VWStudentList> list = new ArrayList<>();

		criteria.setFirstResult(startNo - 1);
		criteria.setMaxResults(offset);

		Set<VWStudentList> set = new LinkedHashSet<>();
		set.addAll(criteria.list());

		Iterator<VWStudentList> iterator = set.iterator();

		while (iterator.hasNext()) {
			VWStudentList object = (VWStudentList) iterator.next();
			list.add(object);
		} 
		return new PagingWrapper<VWStudentList>(list, _totalRecords, startNo, offset);
	}

}