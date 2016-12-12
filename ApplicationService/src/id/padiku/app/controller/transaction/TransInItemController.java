package id.padiku.app.controller.transaction;

import id.padiku.app.controller.SuperController;
import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.service.transaction.ITransInItemService;
import id.padiku.app.valueobject.procurement.TransInItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_TRANS_IN_ITEM)
public class TransInItemController extends SuperController<TransInItem> {

	@Autowired
	private ITransInItemService transInItemService;
	
	@Override
	public MaintenanceService<TransInItem> getMaintenanceService() {
		return transInItemService;
	}

	@Override
	public TransInItem validate(TransInItem anObject) throws SystemException {
		return null;
	}

}
