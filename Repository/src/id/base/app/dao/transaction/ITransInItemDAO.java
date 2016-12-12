package id.base.app.dao.transaction;

import id.base.app.IBaseDAO;
import id.base.app.valueobject.procurement.TransInItem;

import java.math.BigDecimal;

public interface ITransInItemDAO extends IBaseDAO<TransInItem>{

	public int updateRemainingVolume(Long pkTransInItem, BigDecimal volume);

	public int updateStatus(Long pkTransInItem, Integer status);
}
