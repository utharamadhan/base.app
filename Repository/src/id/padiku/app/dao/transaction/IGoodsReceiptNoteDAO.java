package id.padiku.app.dao.transaction;

import id.padiku.app.IBaseDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.inventory.GoodsReceiptNote;

public interface IGoodsReceiptNoteDAO extends IBaseDAO<GoodsReceiptNote>{

	public GoodsReceiptNote getGoodsReceiptNoteById(Long maintenancePK) throws SystemException;
	
	public String generateGoodsReceiptNoteNumber(Long pkCompany) throws SystemException;
	
}
