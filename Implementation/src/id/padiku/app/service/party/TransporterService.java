package id.padiku.app.service.party;

import id.padiku.app.ILookupAddressGroupConstant;
import id.padiku.app.dao.party.ITransporterDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.service.lookup.IMasterAddressService;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.LookupAddress;
import id.padiku.app.valueobject.master.CompanyAddress;
import id.padiku.app.valueobject.party.Party;
import id.padiku.app.valueobject.party.PartyAddress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.collection.spi.PersistentCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransporterService implements ITransporterService{

	@Autowired
	private IMasterAddressService masterAddressService;
	@Autowired
	private ITransporterDAO transporterDAO;
	
	@Override
	public Party findById(Long id) throws SystemException {
		Party obj = transporterDAO.findById(id);
		//used for crud screen
		if(obj != null && obj.getPartyAddresses() != null && obj.getPartyAddresses() instanceof PersistentBag) {
			( (PersistentCollection) obj.getPartyAddresses() ).forceInitialization();
			preparingOptions(obj.getPartyAddresses());
		}
		if(obj != null && obj.getPartyContacts() != null && obj.getPartyContacts() instanceof PersistentBag) {
			( (PersistentCollection) obj.getPartyContacts() ).forceInitialization();
		}
		return obj;
	}
	
	private void preparingOptions(List<PartyAddress> partyAddress) {
		for(PartyAddress addrs : partyAddress) {
			addrs.setProvinsiOptions(new ArrayList<LookupAddress>(Arrays.asList(addrs.getProvinsi())));
			addrs.setKabupatenKotaOptions(new ArrayList<LookupAddress>(Arrays.asList(addrs.getKabupatenKota())));
			addrs.setKecamatanOptions(masterAddressService.findAddressByParent(ILookupAddressGroupConstant.KAB_KOTA, addrs.getKabupatenKota().getPkLookupAddress()));
			addrs.setKelurahanOptions(masterAddressService.findAddressByParent(ILookupAddressGroupConstant.KECAMATAN, addrs.getKecamatan().getPkLookupAddress()));
		}
	}

	@Override
	public void saveOrUpdate(Party anObject) throws SystemException {
		transporterDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		transporterDAO.delete(objectPKs);
	}

	@Override
	public List<Party> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Party> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return transporterDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<Party> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return null;
	}

}