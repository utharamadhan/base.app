package id.base.app.dao.transaction;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchAlias;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.master.CompanyProduct;
import id.base.app.valueobject.party.Party;
import id.base.app.valueobject.procurement.TransIn;
import id.base.app.valueobject.procurement.TransInItem;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.SerializationUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class TransInItemDAO extends AbstractHibernateDAO<TransInItem, Long> implements ITransInItemDAO {

	@Override
	public TransInItem findById(Long id) throws SystemException {
		return null;
	}

	@Override
	public void saveOrUpdate(TransInItem anObject) throws SystemException {
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<TransInItem> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<TransInItem> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		SearchAlias[] searchAliases = new SearchAlias[]{
				new SearchAlias(TransInItem.TRANS_IN,TransInItem.TRANS_IN),
				new SearchAlias(TransInItem.COMPANY_PRODUCT_COMPANY,TransInItem.COMPANY_PRODUCT_COMPANY_ALIAS),
				new SearchAlias(TransInItem.COMPANY_PRODUCT,TransInItem.COMPANY_PRODUCT),
				new SearchAlias(TransInItem.TRANS_IN_THIRD_PARTY,TransInItem.TRANS_IN_THIRD_PARTY_ALIAS),
				new SearchAlias(TransInItem.ITEM_TYPE,TransInItem.ITEM_TYPE),
				new SearchAlias(TransInItem.UOM,TransInItem.UOM)
			};
		String[] fetchProperties = new String[]{
				TransInItem.ID,
				TransInItem.TRANS_IN_IN_NO,
				TransInItem.TRANS_IN_IN_DATE,
				TransInItem.TRANS_IN_THIRD_PARTY_ALIAS_NAME,
				TransInItem.ITEM_TYPE_ID,
				TransInItem.COMPANY_PRODUCT_NAME,
				TransInItem.VOLUME_IN_KG,
				TransInItem.REMAINING_VOLUME_IN_KG,
				TransInItem.UOM_ID,
				TransInItem.UOM_NAME_ID,
				TransInItem.STATUS};
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TransInItem.class);
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

		for (SearchAlias searchAlias : searchAliases) {
			if(searchAlias.getJoinType()!=null){
				criteria.createAlias(searchAlias
						.getAliasProperty(), searchAlias
						.getAliasName(), searchAlias.getJoinType());
			}else{
				criteria.createAlias(searchAlias
						.getAliasProperty(), searchAlias
						.getAliasName());
			}
			
		}

		if (fetchProperties != null) {
			ProjectionList projectionList = Projections.projectionList();
			for (String fetchedProperty : fetchProperties) {
				projectionList.add(Projections.property(fetchedProperty));
			}
			criteria.setProjection(projectionList);
			criteria.setResultTransformer(new ResultTransformer() {
				
				@Override
				public TransInItem transformTuple(Object[] tuple, String[] aliases) {
					TransInItem ii = new TransInItem();
					try {
						BeanUtils.copyProperty(ii, TransInItem.ID, tuple[0]);
						TransIn i = new TransIn();
						BeanUtils.copyProperty(i, TransIn.IN_NO, tuple[1]);
						BeanUtils.copyProperty(i, TransIn.IN_DATE, tuple[2]);
						
						Party p = new Party();
						BeanUtils.copyProperty(p, Party.NAME, tuple[3]);
						i.setThirdParty(p);
						
						Lookup lit = new Lookup();
						BeanUtils.copyProperty(lit, Lookup.ID, tuple[4]);
						ii.setItemType(lit);
						ii.setTransIn(i);
						
						CompanyProduct cp = new CompanyProduct();
						BeanUtils.copyProperty(cp, CompanyProduct.NAME, tuple[5]);
						ii.setCompanyProduct(cp);
						
						if(tuple[6]!=null){
							BeanUtils.copyProperty(ii, TransInItem.VOLUME_IN_KG, tuple[6]);	
						}else{
							ii.setVolumeInKg(BigDecimal.ZERO);
						}
						if(tuple[7]!=null){
							BeanUtils.copyProperty(ii, TransInItem.REMAINING_VOLUME_IN_KG, tuple[7]);
						}else{
							ii.setRemainingVolumeInKg(BigDecimal.ZERO);
						}
						
						Lookup l = new Lookup();
						BeanUtils.copyProperty(l, Lookup.ID, tuple[8]);
						BeanUtils.copyProperty(l, Lookup.NAME_ID, tuple[9]);
						ii.setUom(l);
						
						BeanUtils.copyProperty(ii, TransInItem.STATUS, tuple[10]);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
					return ii;
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

		List<TransInItem> list = new ArrayList<TransInItem>();

		criteria.setFirstResult(startNo - 1);
		criteria.setMaxResults(offset);

		Set<TransInItem> set = new LinkedHashSet<TransInItem>();
		set.addAll(criteria.list());

		Iterator<TransInItem> iterator = set.iterator();

		while (iterator.hasNext()) {
			TransInItem object = (TransInItem) iterator.next();
			list.add(object);
		}
		return new PagingWrapper<TransInItem>(list, _totalRecords, startNo, offset);
	}

	@Override
	public int updateRemainingVolume(Long pkTransInItem, BigDecimal volume) {
		String updateQuery = "UPDATE TRANS_IN_ITEM SET REMAINING_VOLUME_IN_KG = REMAINING_VOLUME_IN_KG - ? "
				+ "WHERE PK_TRANS_IN_ITEM = ?";
		
		SQLQuery sqlQuery = getSession().createSQLQuery(updateQuery);
		sqlQuery.setBigDecimal(0, volume);
		sqlQuery.setLong(1, pkTransInItem);
		return sqlQuery.executeUpdate();
	}
	
	@Override
	public int updateStatus(Long pkTransInItem, Integer status) {
		String updateQuery = "UPDATE TRANS_IN_ITEM SET STATUS = ? "
				+ "WHERE PK_TRANS_IN_ITEM = ?";
		
		SQLQuery sqlQuery = getSession().createSQLQuery(updateQuery);
		sqlQuery.setInteger(0, status);
		sqlQuery.setLong(1, pkTransInItem);
		return sqlQuery.executeUpdate();
	}
}
