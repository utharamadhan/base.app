package id.padiku.app.service.party;

import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.party.PartyAddress;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class PartyAddressService implements MaintenanceService<PartyAddress>, IPartyAddressService{

	@Override
	public PartyAddress findById(Long id) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(PartyAddress anObject) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PartyAddress> findObjects(Long[] objectPKs)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingWrapper<PartyAddress> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PartyAddress> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
	
}