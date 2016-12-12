package id.padiku.app.controller.transaction;

import id.padiku.app.controller.SuperController;
import id.padiku.app.exception.ErrorHolder;
import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.service.lookup.ILookupService;
import id.padiku.app.service.transaction.IPurchaseOrderService;
import id.padiku.app.valueobject.procurement.PurchaseOrder;
import id.padiku.app.valueobject.procurement.PurchaseOrderDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_PURCHASE_ORDER)
public class PurchaseOrderController extends SuperController<PurchaseOrder> {

	@Autowired
	private IPurchaseOrderService poService;
	
	@Autowired
	private ILookupService lookupService;
	
	@Override
	public MaintenanceService<PurchaseOrder> getMaintenanceService() {
		return this.poService;
	}
	
	@Override
	public PurchaseOrder preCreate(PurchaseOrder anObject) {
		if(anObject.getPurchaseOrderElements() != null) {
			for(PurchaseOrderDetail item : anObject.getPurchaseOrderElements()) {
				item.setPurchaseOrder(anObject);
			}
		}
		return validate(anObject);
	}

	@Override
	public PurchaseOrder validate(PurchaseOrder anObject) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<>();
		if(anObject.getPurchaseOrderDate() == null) {
			errors.add(new ErrorHolder(PurchaseOrder.PURCHASE_ORDER_DATE, messageSource.getMessage("error.message.purchaseOrder.mandatory.date", null, Locale.ENGLISH)));
		}
		if(anObject.getVatPercent() == null) {
			errors.add(new ErrorHolder(PurchaseOrder.VAT_PERCENT, messageSource.getMessage("error.message.purchaseOrder.mandatory.vatPercent", null, Locale.ENGLISH)));
		}
		if(errors.size() > 0) {
			throw new SystemException(errors);
		}
		return anObject;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getPurchaseOrderById/{maintenancePK}")
	@ResponseBody
	public PurchaseOrder getPurchaseOrderById(@PathVariable(value="maintenancePK") Long maintenancePK) throws SystemException {
		return poService.getPurchaseOrderById(maintenancePK);
	}
}