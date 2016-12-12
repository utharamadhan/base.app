package id.padiku.app.dao.party;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.ILookupConstant;
import id.padiku.app.ILookupGroupConstant;
import id.padiku.app.SystemConstant;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.Operator;
import id.padiku.app.util.dao.SearchAlias;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.LookupAddress;
import id.padiku.app.valueobject.party.Party;
import id.padiku.app.valueobject.party.PartyAddress;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.hibernate.sql.JoinType;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class TransporterDAO extends AbstractHibernateDAO<Party,Long> implements ITransporterDAO {

	@Override
	public Party findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(Party anObject) throws SystemException {
		if(anObject.getPkParty()!=null){
			super.update(anObject);
		}else{
			super.create(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		for(Long objectPK : objectPKs){
			Party obj = findById(objectPK);
				obj.setStatus(SystemConstant.ValidFlag.INVALID);
			super.update(obj);
		}
	}

	@Override
	public List<Party> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Party> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		SearchAlias[] searchAliases = new SearchAlias[]{
				new SearchAlias("partyRoles","partyRoles"),
				new SearchAlias("partyRoles.role","roleLookup"),
				new SearchAlias("partyCompanies","partyCompanies"),
				new SearchAlias("partyCompanies.company", "company"),
				new SearchAlias("partyAddresses", "partyAddresses", JoinType.LEFT_OUTER_JOIN),
				new SearchAlias("partyAddresses.provinsi", "provinsi", JoinType.LEFT_OUTER_JOIN),
				new SearchAlias("partyAddresses.kabupatenKota", "kabupatenKota", JoinType.LEFT_OUTER_JOIN),
				new SearchAlias("partyAddresses.kelurahan", "kelurahan", JoinType.LEFT_OUTER_JOIN),
				new SearchAlias("partyAddresses.kecamatan", "kecamatan", JoinType.LEFT_OUTER_JOIN)
		};
		
		String[] fetchProperties = new String[]{ Party.ID, Party.CODE, Party.NAME, Party.NPWP, Party.PARTY_ADDRESS_KODEPOS, 
				"provinsi.name", "kabupatenKota.name", "kelurahan.name", "kecamatan.name", "partyAddresses.alamat"};
		filter.add(new SearchFilter("roleLookup.code", Operator.EQUALS, ILookupConstant.PartyRole.TRANSPORTER));
		filter.add(new SearchFilter("roleLookup.lookupGroupString", Operator.EQUALS, ILookupGroupConstant.PARTY_ROLE));
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Party.class);
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
				public Party transformTuple(Object[] tuple, String[] aliases) {
					Party p = new Party();
					try{
						BeanUtils.copyProperty(p, Party.ID, tuple[0]);
						BeanUtils.copyProperty(p, Party.CODE, tuple[1]);
						BeanUtils.copyProperty(p, Party.NAME, tuple[2]);
						BeanUtils.copyProperty(p, Party.NPWP, tuple[3]);
						if(tuple[4]!=null){
							PartyAddress pa = new PartyAddress();
							BeanUtils.copyProperty(pa, "kodepos", tuple[4]);
							if(tuple[5]!=null){
								LookupAddress la = new LookupAddress();
								BeanUtils.copyProperty(la, "name", tuple[5]);
								BeanUtils.copyProperty(pa, "provinsi", la);
							}
							if(tuple[6]!=null){
								LookupAddress la = new LookupAddress();
								BeanUtils.copyProperty(la, "name", tuple[6]);
								BeanUtils.copyProperty(pa, "kabupatenKota", la);
							}
							if(tuple[7]!=null){
								LookupAddress la = new LookupAddress();
								BeanUtils.copyProperty(la, "name", tuple[7]);
								BeanUtils.copyProperty(pa, "kelurahan", la);
							}
							if(tuple[8]!=null){
								LookupAddress la = new LookupAddress();
								BeanUtils.copyProperty(la, "name", tuple[8]);
								BeanUtils.copyProperty(pa, "kecamatan", la);
							}
							BeanUtils.copyProperty(pa, "alamat", tuple[9]);
							BeanUtils.copyProperty(p, "partyAddresses", new ArrayList<>(Arrays.asList(pa)));
						}
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
					return p;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				};
			});
		}

		List<Party> list = new ArrayList<Party>();
		
		for (Criterion criterion : criterionList) {
			criteria.add(criterion);
		}

		for (Order o : orderList) {
			criteria.addOrder(o);
		}

		criteria.setFirstResult(startNo - 1);
		criteria.setMaxResults(offset);
		
		Set<Party> set = new LinkedHashSet<Party>();
		set.addAll(criteria.list());

		Iterator<Party> iterator = set.iterator();

		while (iterator.hasNext()) {
			Party object = (Party) iterator.next();
			list.add(object);
		} 

		return new PagingWrapper<Party>(list, _totalRecords, startNo, offset);
	}
	
}