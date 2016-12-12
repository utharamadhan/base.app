package id.padiku.app.controller.transaction;

import id.padiku.app.controller.SuperController;
import id.padiku.app.exception.ErrorHolder;
import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.service.lookup.ILookupService;
import id.padiku.app.service.transaction.IGoodsReceiptNoteService;
import id.padiku.app.valueobject.inventory.GoodsReceiptNote;
import id.padiku.app.valueobject.inventory.GoodsReceiptNoteDetail;
import id.padiku.app.valueobject.procurement.PurchaseOrder;

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