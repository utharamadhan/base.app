package id.base.app.controller.transaction;

import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.lookup.ILookupService;
import id.base.app.service.master.ICompanyProductService;
import id.base.app.service.transaction.ITransInService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.procurement.TransIn;
import id.base.app.valueobject.procurement.TransInFee;
import id.base.app.valueobject.procurement.TransInItem;

import java.math.BigDecimal;
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
@RequestMapping(RestConstant.RM_TRANS_IN)
public class TransInController extends SuperController<TransIn> {

	@Autowired
	private ITransInService transInService;
	
	@Autowired
	private ILookupService lookupService;
	
	@Autowired
	private ICompanyProductService companyProductService;
	
	@Override
	public MaintenanceService<TransIn> getMaintenanceService() {
		return transInService;
	}
	
	public TransIn preCreate(TransIn anObject) throws SystemException{
		return validate(preparingObject(anObject));
	}
	
	public TransIn preUpdate(TransIn anObject) throws SystemException{
		return validate(preparingObject(anObject));
	}
	
	private TransIn preparingObject(TransIn anObject) throws SystemException {
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		Lookup uom = lookupService.findByCode(ILookupConstant.Uom.KG, ILookupGroupConstant.UOM);
		if(anObject.getItems() != null && anObject.getItems().size() > 0) {
			BigDecimal mainFee = BigDecimal.ZERO;
			for(TransInItem item : anObject.getItems()){
				item.setTransIn(anObject);
				item.setStatus(SystemConstant.StatusTransInItem.VALID);
				if(item.getUom().getPkLookup().equals(uom.getPkLookup())){
					item.setVolumeInKg(item.getVolume());
					item.setRemainingVolumeInKg(item.getVolume());
				}
				if(item.getTotalFee() != null) {
					mainFee = mainFee.add(item.getTotalFee());
				}
				item.setItemType(companyProductService.findItemType(item.getCompanyProduct().getPkCompanyProduct()));
			}
			anObject.setMainFee(mainFee);
		}
		
		if(anObject.getFees() != null && anObject.getFees().size() > 0) {
			BigDecimal totalInFee = BigDecimal.ZERO;
			for(TransInFee item : anObject.getFees()){
				item.setTransIn(anObject);
				if(item.getFee() != null) {
					totalInFee = totalInFee.add(item.getFee());
				}
			}
			anObject.setTotalInFee(totalInFee);
		}
		return anObject;
	}

	@Override
	public TransIn validate(TransIn anObject) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<>();
		if(anObject.getItems() == null || anObject.getItems().size() < 1) {
			errors.add(new ErrorHolder(messageSource.getMessage("error.message.transIn.mandatory.transInItems", null, Locale.ENGLISH)));
		} else {
			for (int i=0;i<anObject.getItems().size();i++) {
				TransInItem ti = anObject.getItems().get(i);
				if(ti.getCompanyProduct() == null || ti.getCompanyProduct().getPkCompanyProduct() == null) {
					errors.add(new ErrorHolder(String.format(TransIn.TRANS_IN_ITEMS_COMPANY_PRODUCT_VALFIELD, new Integer(i)), messageSource.getMessage("error.message.transInItems.mandatory.companyProduct", null, Locale.ENGLISH)));
				}
				if(ti.getVolume() == null || ti.getVolume().intValue() < 1) {
					errors.add(new ErrorHolder(String.format(TransIn.TRANS_IN_ITEMS_VOLUME_VALFIELD, new Integer(i)), messageSource.getMessage("error.message.transInItems.mandatory.volume", null, Locale.ENGLISH)));
				}
				if(ti.getUom() == null || ti.getUom().getPkLookup() == null) {
					errors.add(new ErrorHolder(String.format(TransIn.TRANS_IN_ITEMS_UOM_VALFIELD, new Integer(i)), messageSource.getMessage("error.message.transInItems.mandatory.uom", null, Locale.ENGLISH)));
				}
				if(SystemConstant.TransInSourceType.BUYING.equalsIgnoreCase(anObject.getSourceType()) && 
						(ti.getFee() == null || (ti.getFee()!=null && ti.getFee().intValue() < 1))) {
					errors.add(new ErrorHolder(String.format(TransIn.TRANS_IN_ITEMS_FEE_VALFIELD, new Integer(i)), messageSource.getMessage("error.message.transInItems.mandatory.fee", null, Locale.ENGLISH)));
				}
			}
		}
		
		if(anObject.getFees() == null || anObject.getFees().size() < 1) {
			errors.add(new ErrorHolder(messageSource.getMessage("error.message.transIn.mandatory.masterFee", null, Locale.ENGLISH)));
		} else {
			for (int i=0;i<anObject.getFees().size();i++) {
				TransInFee fee = anObject.getFees().get(i);
				if(StringFunction.isEmpty(fee.getDescr())) {
					errors.add(new ErrorHolder(String.format(TransIn.TRANS_IN_FEES_DESCR_VALFIELD, new Integer(i)), messageSource.getMessage("error.message.transInFee.mandatory.description", null, Locale.ENGLISH)));
				}
				if(fee.getFee() == null || fee.getFee().intValue() < 1) {
					errors.add(new ErrorHolder(String.format(TransIn.TRANS_IN_FEES_TOTAL_VALUE_VALFIELD, new Integer(i)), messageSource.getMessage("error.message.transInFee.mandatory.biaya", null, Locale.ENGLISH)));
				}
			}
		}
		
		if(errors != null && errors.size() > 0) {
			throw new SystemException(errors);
		}
		return anObject;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/countTransIn/{pkCompany}/{transInSourceType}")
	@ResponseBody
	public Long countTransIn(@PathVariable(value="pkCompany") Long pkCompany, @PathVariable(value="transInSourceType") String transInSourceType) throws SystemException {
		return transInService.countTransIn(pkCompany, transInSourceType);
	}
	
}
