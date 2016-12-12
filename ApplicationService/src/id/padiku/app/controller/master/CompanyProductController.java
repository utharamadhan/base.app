package id.padiku.app.controller.master;

import id.padiku.app.controller.SuperController;
import id.padiku.app.exception.ErrorHolder;
import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.service.master.ICompanyProductService;
import id.padiku.app.util.StringFunction;
import id.padiku.app.valueobject.master.CompanyProduct;

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
@RequestMapping(RestConstant.RM_COMPANY_PRODUCT)
public class CompanyProductController extends SuperController<CompanyProduct> {

	@Autowired
	private ICompanyProductService productService;
	
	@Override
	public MaintenanceService<CompanyProduct> getMaintenanceService() {
		return this.productService;
	}
	
	@Override
	public CompanyProduct preCreate(CompanyProduct anObject) throws SystemException {
		return validate(anObject);
	}
	
	@Override
	public Long[] preDelete(Long[] maintenancePKs) throws SystemException {
		return validateDelete(maintenancePKs);
	}

	@Override
	public CompanyProduct validate(CompanyProduct anObject) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<>();
		
		if(anObject.getType() == null || anObject.getType().getPkLookup() == null){
			errors.add(new ErrorHolder(messageSource.getMessage("error.message.product.mandatory.category", null, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getName())){
			errors.add(new ErrorHolder(CompanyProduct.NAME, messageSource.getMessage("error.message.product.mandatory.name", null, Locale.ENGLISH)));
		}
		if(anObject.getUom() == null || anObject.getUom().getPkLookup() == null){
			errors.add(new ErrorHolder(messageSource.getMessage("error.message.product.mandatory.uom", null, Locale.ENGLISH)));
		}
		
		if(errors != null && errors.size() > 0){
			throw new SystemException(errors);
		}
		return anObject;
	}
	
	public Long[] validateDelete(Long[] maintenancePKs) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<>();
		if(maintenancePKs != null) {
			for(Long maintenancePK : maintenancePKs) {
				if(productService.isMachineryUsedThisProduct(maintenancePK)){
					errors.add(new ErrorHolder(messageSource.getMessage("error.message.product.delete.inused.machinery", null, Locale.ENGLISH)));
					break;
				}
			}
		}
		if(errors != null && errors.size() > 0){
			throw new SystemException(errors);
		}
		return maintenancePKs;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findAll/{pkCompany}")
	@ResponseBody
	public List<CompanyProduct> findAll(@PathVariable(value="pkCompany") Long pkCompany) throws SystemException {
		return productService.findAll(pkCompany);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/searchProduct/{pkCompany}/{keyword}")
	@ResponseBody
	public List<CompanyProduct> searchProduct(@PathVariable(value="pkCompany") Long pkCompany, @PathVariable(value="keyword") String keyword) throws SystemException {
		return productService.findAll(pkCompany, keyword);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findAllProductByUsageType/{pkCompany}/{usageItemType}")
	@ResponseBody
	public List<CompanyProduct> findAllProductByUsageType(@PathVariable(value="pkCompany") final Long pkCompany, @PathVariable(value="usageItemType") String usageItemType) throws SystemException {
		return productService.findAllProductByUsageType(pkCompany, usageItemType);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findAllExistInStock/{pkCompany}")
	@ResponseBody
	public List<CompanyProduct> findAllExistInStock(@PathVariable(value="pkCompany") Long pkCompany) throws SystemException {
		return productService.findAllExistInStock(pkCompany);
	}
}