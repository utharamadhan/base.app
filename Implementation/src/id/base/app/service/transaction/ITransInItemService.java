package id.base.app.service.transaction;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.procurement.TransInItem;

import java.math.BigDecimal;

public interface ITransInItemService extends MaintenanceService<TransInItem>{

	public TransInItem findByIdForAddToStock(Long pkTransInItem) throws SystemException;

	public int updateRemainingVolume(Long pkTransInItem, BigDecimal volume);

	public int updateStatus(Long pkTransInItem, Integer status);

}
