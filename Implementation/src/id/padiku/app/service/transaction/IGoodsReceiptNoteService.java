package id.padiku.app.service.transaction;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.inventory.GoodsReceiptNote;

public interface IGoodsReceiptNoteService extends MaintenanceService<GoodsReceiptNote>{

	public GoodsReceiptNote getGoodsReceiptNote(Long maintenancePK) throws SystemException;
	
}
