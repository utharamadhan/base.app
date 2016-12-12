package id.padiku.app.dao.party;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.master.VWCompanyThirdParty;
import id.padiku.app.valueobject.party.Party;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ThirdPartyDAO extends AbstractHibernateDAO<VWCompanyThirdParty,Long> implements IThirdPartyDAO {

	@Override
	public VWCompanyThirdParty findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	@Deprecated
	public void saveOrUpdate(VWCompanyThirdParty anObject) throws SystemException {}

	@Override
	@Deprecated
	public void delete(Long[] objectPKs) throws SystemException {}

	@Override
	public List<VWCompanyThirdParty> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<VWCompanyThirdParty> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

	@Override
	public Party getThirdCompanyParty(Long maintenancePK) throws SystemException {
		return null;
	}
	
}