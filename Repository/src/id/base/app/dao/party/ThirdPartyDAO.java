package id.base.app.dao.party;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.master.VWCompanyThirdParty;
import id.base.app.valueobject.party.Party;

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