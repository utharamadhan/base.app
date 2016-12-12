package id.padiku.app.service.lookup;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.Lookup;

import java.util.List;

public interface ILookupService extends MaintenanceService<Lookup> {

	List<Lookup> findByLookupGroup(String lookupGroup) throws SystemException;

	Lookup findByCode(String code, String lookupGroup) throws SystemException;
	
	Lookup findById(Long id) throws SystemException;
	
	public abstract List<Lookup> findByLookupGroupOrderBy(String lookupGroup, String orderBy,boolean desc) throws SystemException;
	
	List<Lookup> findByLookupGroupAndUsage(String lookupGroup, String usage)
			throws SystemException;

}