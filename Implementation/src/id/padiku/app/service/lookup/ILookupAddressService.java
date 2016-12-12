package id.padiku.app.service.lookup;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.LookupAddress;

import java.util.List;

public interface ILookupAddressService extends MaintenanceService<LookupAddress>{

	public List<LookupAddress> findByLookupAddressGroup(String lookupAddressGroup)
				throws SystemException;
		
	public List<LookupAddress> findByGroupOrderBy(String lookupAddressGroup,
			String orderBy, boolean desc) throws SystemException;
}