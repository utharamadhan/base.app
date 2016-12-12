package id.base.app.controller.transaction;

import id.base.app.controller.SuperController;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.transaction.ITransInItemService;
import id.base.app.valueobject.procurement.TransInItem;

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
