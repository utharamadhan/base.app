package id.padiku.app.dao.transaction;

import id.padiku.app.IBaseDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.procurement.PurchaseOrder;

import java.util.List;

public interface IPurchaseOrderDAO extends IBaseDAO<PurchaseOrder>{

	public PurchaseOrder getPurchaseOrderById(Long maintenancePK) throws SystemException;
	
	public List<PurchaseOrder> findAllByStock(Long pkStock) throws SystemException;
	
	public List<PurchaseOrder> findAllBuyingResultByRmuAndCategory(Long pkRmu, Long pkCategoryLookup) throws SystemException;
	
	public String generatePurchaseOrderNumber(Long pkCompany) throws SystemException;
	
}
