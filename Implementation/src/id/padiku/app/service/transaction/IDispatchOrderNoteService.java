package id.padiku.app.service.transaction;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.inventory.DispatchOrderNote;

public interface IDispatchOrderNoteService extends MaintenanceService<DispatchOrderNote>{

	public DispatchOrderNote getGoodsReceiptNote(Long maintenancePK) throws SystemException;
	
}
