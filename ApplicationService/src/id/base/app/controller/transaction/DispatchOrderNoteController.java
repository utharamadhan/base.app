package id.base.app.controller.transaction;

import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.lookup.ILookupService;
import id.base.app.service.transaction.IDispatchOrderNoteService;
import id.base.app.valueobject.inventory.DispatchOrderNote;
import id.base.app.valueobject.inventory.DispatchOrderNoteDetail;
import id.base.app.valueobject.procurement.PurchaseOrder;

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
@RequestMapping(RestConstant.RM_DISPATCH_ORDER_NOTE)
public class DispatchOrderNoteController extends SuperController<DispatchOrderNote> {

	@Autowired
	private IDispatchOrderNoteService dispatchOrderService;
	@Autowired
	private ILookupService lookupService;
	
	@Override
	public MaintenanceService<DispatchOrderNote> getMaintenanceService() {
		return this.dispatchOrderService;
	}
	
	@Override
	public DispatchOrderNote preCreate(DispatchOrderNote anObject) {
		if(anObject.getDispatchOrderNoteDetails() != null){
			for(DispatchOrderNoteDetail item : anObject.getDispatchOrderNoteDetails()) {
				item.setDispatchOrderNote(anObject);
			}
		}
		return validate(anObject);
	}

	@Override
	public DispatchOrderNote validate(DispatchOrderNote anObject) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<>();
		
		if(anObject.getPickingDate() == null) {
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
	
	@RequestMapping(method=RequestMethod.GET, value="/getDispatchOrderNoteById/{maintenancePK}")
	@ResponseBody
	public DispatchOrderNote getDispatchOrderNoteById(@PathVariable(value="maintenancePK") Long maintenancePK) throws SystemException {
		return dispatchOrderService.getGoodsReceiptNote(maintenancePK);
	}
}