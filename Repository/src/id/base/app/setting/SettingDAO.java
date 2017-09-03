package id.base.app.setting;

import id.base.app.AbstractHibernateDAO;
import id.base.app.ILookupConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.frontend.Setting;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class SettingDAO extends AbstractHibernateDAO<Setting, Long> implements ISettingDAO {

	@Override
	public Setting findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(Setting anObject) throws SystemException {
		if (anObject.getPkSetting()==null) {
			super.create(anObject);
		} else {
		    super.update(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<Setting> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Setting> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}
	
	@Override
	public List<Setting> findAllSetting(List<Integer> typeList) throws SystemException {
		Criteria crit = this.getSession().createCriteria(domainClass);
		crit.add(Restrictions.eq(Setting.STATUS, ILookupConstant.Status.PUBLISH));
		crit.add(Restrictions.in(Setting.TYPE, typeList));
		crit.addOrder(Order.asc(Setting.TYPE));
		crit.addOrder(Order.asc(Setting.ORDER_NO));
		crit.setProjection(Projections.projectionList().
				add(Projections.property(Setting.PK_SETTING)).
				add(Projections.property(Setting.TYPE)).
				add(Projections.property(Setting.DATA_FROM)).
				add(Projections.property(Setting.LABEL1)).
				add(Projections.property(Setting.LABEL2)).
				add(Projections.property(Setting.VALUE)).
				add(Projections.property(Setting.ORDER_NO)).
				add(Projections.property(Setting.STATUS)));
		crit.setResultTransformer(new ResultTransformer() {
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				Setting obj = new Setting();
				try {
					BeanUtils.copyProperty(obj, Setting.PK_SETTING, tuple[0]);
					BeanUtils.copyProperty(obj, Setting.TYPE, tuple[1]);
					BeanUtils.copyProperty(obj, Setting.DATA_FROM, tuple[2]);
					BeanUtils.copyProperty(obj, Setting.LABEL1, tuple[3]);
					BeanUtils.copyProperty(obj, Setting.LABEL2, tuple[4]);
					BeanUtils.copyProperty(obj, Setting.VALUE, tuple[5]);
					BeanUtils.copyProperty(obj, Setting.ORDER_NO, tuple[6]);
					BeanUtils.copyProperty(obj, Setting.STATUS, tuple[7]);
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
				}
				return obj;
			}
			
			@Override
			public List transformList(List collection) {
				return collection;
			}
		});
		return crit.list();
	}

}