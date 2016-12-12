package id.base.app.dao.transaction;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.inventory.GoodsReceiptNote;

public interface IGoodsReceiptNoteDAO extends IBaseDAO<GoodsReceiptNote>{

	public GoodsReceiptNote getGoodsReceiptNoteById(Long maintenancePK) throws SystemException;
	
	public String generateGoodsReceiptNoteNumber(Long pkCompany) throws SystemException;
	
}
