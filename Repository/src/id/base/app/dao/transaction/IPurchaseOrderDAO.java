package id.base.app.dao.transaction;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.procurement.PurchaseOrder;

import java.util.List;

public interface IPurchaseOrderDAO extends IBaseDAO<PurchaseOrder>{

	public PurchaseOrder getPurchaseOrderById(Long maintenancePK) throws SystemException;
	
	public List<PurchaseOrder> findAllByStock(Long pkStock) throws SystemException;
	
	public List<PurchaseOrder> findAllBuyingResultByRmuAndCategory(Long pkRmu, Long pkCategoryLookup) throws SystemException;
	
	public String generatePurchaseOrderNumber(Long pkCompany) throws SystemException;
	
}
