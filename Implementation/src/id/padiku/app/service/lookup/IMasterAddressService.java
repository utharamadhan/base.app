package id.padiku.app.service.lookup;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.LookupAddress;
import id.padiku.app.valueobject.MasterAddress;

import java.util.List;
import java.util.Map;

public interface IMasterAddressService extends MaintenanceService<MasterAddress>{

	public List<LookupAddress> findAddressByParent(String addressGroupSource, Long fkLookupAddressParent) throws SystemException;

	public MasterAddress findByKodepos(Integer kodepos) throws SystemException;

	public Map<String, Object> findByKodeposWithList(Integer kodepos) throws SystemException;
	
	public List<Integer> searchPostalCode(String keyword) throws SystemException;

	public Integer getKodepos(Long fkLookupAddressKelurahan) throws SystemException;

}
