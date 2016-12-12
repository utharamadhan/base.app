package id.base.app.service.lookup;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.MasterMachinery;

import java.util.List;

public interface IMasterMachineryService extends MaintenanceService<MasterMachinery>{

	public List<MasterMachinery> findAllActive(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException;

}
