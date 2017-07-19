package id.base.app.controller.learning;

import id.base.app.controller.SuperController;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.learning.IVWLearningItemService;
import id.base.app.valueobject.learning.VWLearningItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_VW_LEARNING_ITEM)
public class VWLearningItemController extends SuperController<VWLearningItem> {

	@Autowired
	private IVWLearningItemService vwLearningItemService;
	
	@Override
	public MaintenanceService<VWLearningItem> getMaintenanceService() {
		return vwLearningItemService;
	}

	@Override
	public VWLearningItem validate(VWLearningItem anObject)
			throws SystemException {
		return null;
	}
	
}