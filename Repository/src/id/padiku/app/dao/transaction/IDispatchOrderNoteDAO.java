package id.padiku.app.dao.transaction;

import id.padiku.app.IBaseDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.inventory.DispatchOrderNote;

public interface IDispatchOrderNoteDAO extends IBaseDAO<DispatchOrderNote>{

	public DispatchOrderNote getDispatchOrderNoteById(Long maintenancePK) throws SystemException;
	
	public String generatePickingNumber(Long pkCompany) throws SystemException;
	
}
