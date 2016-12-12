package id.base.app.controller.transaction;

import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.lookup.ILookupService;
import id.base.app.service.transaction.IGoodsReceiptNoteService;
import id.base.app.valueobject.inventory.GoodsReceiptNote;
import id.base.app.valueobject.inventory.GoodsReceiptNoteDetail;
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
@RequestMapping(RestConstant.RM_GOODS_RECEIPT_NOTE)
public class GoodsReceiptNoteController extends SuperController<GoodsReceiptNote> {

	@Autowired
	private IGoodsReceiptNoteService grnService;
	@Autowired
	private ILookupService lookupService;
	
	@Override
	public MaintenanceService<GoodsReceiptNote> getMaintenanceService() {
		return this.grnService;
	}
	
	@Override
	public GoodsReceiptNote preCreate(GoodsReceiptNote anObject) {
		if(anObject.getGoodsReceiptNoteDetails() != null){
			for(GoodsReceiptNoteDetail item : anObject.getGoodsReceiptNoteDetails()) {
				item.setGoodsReceiptNote(anObject);
			}
		}
		return validate(anObject);
	}

	@Override
	public GoodsReceiptNote validate(GoodsReceiptNote anObject) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<>();
		
		if(anObject.getGoodsReceiptNoteDate() == null) {
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
	
	@RequestMapping(method=RequestMethod.GET, value="/getGoodsReceiptNoteById/{maintenancePK}")
	@ResponseBody
	public GoodsReceiptNote getGoodsReceiptNoteById(@PathVariable(value="maintenancePK") Long maintenancePK) throws SystemException {
		return grnService.getGoodsReceiptNote(maintenancePK);
	}
}