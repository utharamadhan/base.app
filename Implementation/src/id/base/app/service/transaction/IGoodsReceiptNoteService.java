package id.base.app.service.transaction;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.inventory.GoodsReceiptNote;

public interface IGoodsReceiptNoteService extends MaintenanceService<GoodsReceiptNote>{

	public GoodsReceiptNote getGoodsReceiptNote(Long maintenancePK) throws SystemException;
	
}
