package id.padiku.app.controller.transaction;

import id.padiku.app.ILookupConstant;
import id.padiku.app.ILookupGroupConstant;
import id.padiku.app.SystemConstant;
import id.padiku.app.controller.SuperController;
import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.service.lookup.ILookupService;
import id.padiku.app.service.master.IStockService;
import id.padiku.app.service.transaction.ITransProdService;
import id.padiku.app.util.BigDecimalFunction;
import id.padiku.app.util.DateTimeFunction;
import id.padiku.app.valueobject.Lookup;
import id.padiku.app.valueobject.production.TransProd;
import id.padiku.app.valueobject.production.TransProdFee;
import id.padiku.app.valueobject.production.TransProdMachinery;
import id.padiku.app.valueobject.production.TransProdOtherItem;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_TRANS_PROD)
public class TransProdController extends SuperController<TransProd> {

	@Autowired
	private ITransProdService transProdService;
	
	@Autowired
	private ILookupService lookupService;
	
	@Autowired
	private IStockService stockService;
	
	@Override
	public MaintenanceService<TransProd> getMaintenanceService() {
		return transProdService;
	}

	@Override
	public TransProd validate(TransProd anObject) throws SystemException {
		return anObject;
	}
	
	public TransProd preCreate(TransProd anObject) throws SystemException{
		return validate(preparingObject(anObject));
	}
	
	public TransProd preUpdate(TransProd anObject) throws SystemException{
		return validate(preparingObject(anObject));
	}
	
	private TransProd preparingObject(TransProd anObject) throws SystemException {
		if(anObject.getStatus()!=SystemConstant.StatusProd.COMPLETE){
			anObject.setStatus(SystemConstant.StatusProd.VALID);
		}
		if(anObject.getActorProd()!=null && anObject.getActorProd().getCode()!=null && anObject.getActorProd().getPkLookup()==null){
			Lookup actorProd = lookupService.findByCode(anObject.getActorProd().getCode(), ILookupGroupConstant.ACTOR_PRODUCTION);
			anObject.setActorProd(actorProd);
		}
		Lookup uom = lookupService.findByCode(ILookupConstant.Uom.KG, ILookupGroupConstant.UOM);
		anObject.setVolume(anObject.getVolumeInKg());
		anObject.setUom(uom);
		anObject.setVolumeTo(anObject.getVolumeToInKg());
		anObject.setUomTo(uom);
		if(anObject.getMachineries() != null && anObject.getMachineries().size() > 0) {
			for (TransProdMachinery m : anObject.getMachineries()) {
				m.setTransProd(anObject);
			}
		};
		BigDecimal otherFee = BigDecimal.ZERO;
		if(anObject.getFees() != null && anObject.getFees().size() > 0) {
			for (TransProdFee f : anObject.getFees()) {
				f.setTransProd(anObject);
				otherFee = otherFee.add(f.getFee());
			}
		};
		
		if(anObject.getOtherItems() != null && anObject.getOtherItems().size() > 0) {
			for (TransProdOtherItem o : anObject.getOtherItems()) {
				o.setTransProd(anObject);
				o.setStatus(SystemConstant.ValidFlag.VALID);
			}
		};
		anObject.setTotalFee(otherFee);
		BigDecimal hpp = stockService.findHppByPk(anObject.getStockFrom().getPkStock());
		BigDecimal totalFee = anObject.getVolumeInKg().multiply(hpp);
		totalFee = totalFee.add(otherFee);
		BigDecimal newHpp = BigDecimalFunction.divide(totalFee, anObject.getVolumeInKg());
		anObject.setHpp(newHpp);
		if(anObject.getEstDays()>0){
			anObject.setProdDateTo(DateTimeFunction.addDate(anObject.getProdDateFrom(), anObject.getEstDays()-1, Calendar.DATE));			
		}else{
			anObject.setProdDateTo(anObject.getProdDateFrom());
		}
		
		return anObject;
	}

	@RequestMapping(method=RequestMethod.GET, value="/countTransProd/{pkCompany}")
	@ResponseBody
	public Long countTransProd(@PathVariable(value="pkCompany") Long pkCompany) throws SystemException {
		return transProdService.countTransProd(pkCompany);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findAllByYearMonth")
	@ResponseBody
	public List<TransProd> findAllByYearMonth(@RequestParam("pkCompany") Long pkCompany, @RequestParam("year") Integer year, @RequestParam("month") Integer month) {
		return transProdService.findAllByYearMonth(pkCompany, year, month);
	}
}
