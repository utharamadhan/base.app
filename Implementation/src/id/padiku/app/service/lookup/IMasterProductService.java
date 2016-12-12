package id.padiku.app.service.lookup;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.MasterProduct;

import java.util.List;

public interface IMasterProductService extends MaintenanceService<MasterProduct>{

	public List<MasterProduct> findAllActive(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException;

}
