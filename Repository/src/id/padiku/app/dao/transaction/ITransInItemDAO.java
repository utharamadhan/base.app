package id.padiku.app.dao.transaction;

import id.padiku.app.IBaseDAO;
import id.padiku.app.valueobject.procurement.TransInItem;

import java.math.BigDecimal;

public interface ITransInItemDAO extends IBaseDAO<TransInItem>{

	public int updateRemainingVolume(Long pkTransInItem, BigDecimal volume);

	public int updateStatus(Long pkTransInItem, Integer status);
}
