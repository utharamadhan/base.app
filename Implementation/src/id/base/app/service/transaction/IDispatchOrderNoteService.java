package id.base.app.service.transaction;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.inventory.DispatchOrderNote;

public interface IDispatchOrderNoteService extends MaintenanceService<DispatchOrderNote>{

	public DispatchOrderNote getGoodsReceiptNote(Long maintenancePK) throws SystemException;
	
}
