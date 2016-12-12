package id.base.app.dao.transaction;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.inventory.DispatchOrderNote;

public interface IDispatchOrderNoteDAO extends IBaseDAO<DispatchOrderNote>{

	public DispatchOrderNote getDispatchOrderNoteById(Long maintenancePK) throws SystemException;
	
	public String generatePickingNumber(Long pkCompany) throws SystemException;
	
}
