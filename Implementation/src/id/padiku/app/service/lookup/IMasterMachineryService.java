package id.padiku.app.service.lookup;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.MasterMachinery;

import java.util.List;

public interface IMasterMachineryService extends MaintenanceService<MasterMachinery>{

	public List<MasterMachinery> findAllActive(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException;

}
