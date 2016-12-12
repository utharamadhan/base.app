package id.padiku.app.service.transaction;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.procurement.PurchaseOrder;

import java.util.List;

public interface IPurchaseOrderService extends MaintenanceService<PurchaseOrder>{

	public PurchaseOrder getPurchaseOrderById(Long maintenancePK) throws SystemException;
	
	public List<PurchaseOrder> findAllByStock(Long pkStock) throws SystemException;
	
}
