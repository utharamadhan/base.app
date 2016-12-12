package id.padiku.app.service.transaction;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.procurement.TransInItem;

import java.math.BigDecimal;

public interface ITransInItemService extends MaintenanceService<TransInItem>{

	public TransInItem findByIdForAddToStock(Long pkTransInItem) throws SystemException;

	public int updateRemainingVolume(Long pkTransInItem, BigDecimal volume);

	public int updateStatus(Long pkTransInItem, Integer status);

}
