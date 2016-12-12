package id.base.app.service.transaction;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.procurement.PurchaseOrder;

import java.util.List;

public interface IPurchaseOrderService extends MaintenanceService<PurchaseOrder>{

	public PurchaseOrder getPurchaseOrderById(Long maintenancePK) throws SystemException;
	
	public List<PurchaseOrder> findAllByStock(Long pkStock) throws SystemException;
	
}
