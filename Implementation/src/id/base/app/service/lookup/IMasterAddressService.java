package id.base.app.service.lookup;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.LookupAddress;
import id.base.app.valueobject.MasterAddress;

import java.util.List;
import java.util.Map;

public interface IMasterAddressService extends MaintenanceService<MasterAddress>{

	public List<LookupAddress> findAddressByParent(String addressGroupSource, Long fkLookupAddressParent) throws SystemException;

	public MasterAddress findByKodepos(Integer kodepos) throws SystemException;

	public Map<String, Object> findByKodeposWithList(Integer kodepos) throws SystemException;
	
	public List<Integer> searchPostalCode(String keyword) throws SystemException;

	public Integer getKodepos(Long fkLookupAddressKelurahan) throws SystemException;

}
