package id.base.app.service.lookup;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.MasterProduct;

import java.util.List;

public interface IMasterProductService extends MaintenanceService<MasterProduct>{

	public List<MasterProduct> findAllActive(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException;

}
