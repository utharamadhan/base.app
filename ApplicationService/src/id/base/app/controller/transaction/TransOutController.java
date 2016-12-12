package id.base.app.controller.transaction;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.transaction.ITransOutService;
import id.base.app.valueobject.sales.TransOut;
import id.base.app.valueobject.sales.TransOutFee;
import id.base.app.valueobject.sales.TransOutItem;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_TRANS_OUT)
public class TransOutController extends SuperController<TransOut> {

	@Autowired
	private ITransOutService transOutService;
	
	@Override
	public MaintenanceService<TransOut> getMaintenanceService() {
		return transOutService;
	}
	
	public TransOut preCreate(TransOut anObject) throws SystemException{
		return validate(preparingObject(anObject));
	}
	
	public TransOut preUpdate(TransOut anObject) throws SystemException{
		return validate(preparingObject(anObject));
	}
	
	public TransOut preparingObject(TransOut anObject) throws SystemException {
		// set trans in property on its items to its trans in itself
		if(anObject.getItems() != null && anObject.getItems().size() > 0) {
			BigDecimal volumeCount = BigDecimal.ZERO;
			BigDecimal subTotalCount = BigDecimal.ZERO;
			for(TransOutItem item : anObject.getItems()){
				item.setTransOut(anObject);
				if(item.getVolume() != null) {
					item.setVolumeInKg(item.getVolume());
					volumeCount = volumeCount.add(item.getVolume());
				}
				if(item.getTotalFee() != null) {
					subTotalCount = subTotalCount.add(item.getTotalFee());
				}
			}
		}
		// set trans in property on its fees to its trans in itself
		if(anObject.getFees() != null && anObject.getFees().size() > 0) {
			BigDecimal count = BigDecimal.ZERO;
			for(TransOutFee item : anObject.getFees()){
				item.setTransOut(anObject);
				if(item.getFee() != null) {
					count = count.add(item.getFee());
				}
			}
			anObject.setTotalOutFee(count);
		}
		anObject.setSourceType(SystemConstant.TransOutSourceType.SALES);
		return anObject;
	}

	@Override
	public TransOut validate(TransOut anObject) throws SystemException {
		return anObject;
	}

}
