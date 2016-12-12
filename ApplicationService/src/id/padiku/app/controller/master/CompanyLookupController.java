package id.padiku.app.controller.master;

import id.padiku.app.SystemConstant;
import id.padiku.app.controller.SuperController;
import id.padiku.app.exception.ErrorHolder;
import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.service.master.ICompanyLookupService;
import id.padiku.app.util.StringFunction;
import id.padiku.app.util.dao.Operator;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.util.dao.SearchOrder.Sort;
import id.padiku.app.valueobject.Lookup;
import id.padiku.app.valueobject.master.CompanyLookup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_COMPANY_LOOKUP)
public class CompanyLookupController extends SuperController<CompanyLookup>{
	
	private static final Map<String,String> ORDER_BY = new HashMap<String,String>();
	static{
		ORDER_BY.put(Lookup.ID, Lookup.ID);
		ORDER_BY.put(Lookup.CODE, Lookup.CODE);
		ORDER_BY.put(Lookup.STATUS, Lookup.STATUS);
		ORDER_BY.put(Lookup.NAME_ID, Lookup.NAME_ID);
		ORDER_BY.put(Lookup.NAME_EN, Lookup.NAME_EN);
		ORDER_BY.put(Lookup.ORDER_NO_STRING, Lookup.ORDER_NO_STRING);
		ORDER_BY.put(Lookup.LOOKUP_GROUP_STRING, Lookup.LOOKUP_GROUP_STRING);
	}
	
	@Autowired
	private ICompanyLookupService companyLookupService;

	@RequestMapping(method=RequestMethod.GET, value="/findCompanyLookupByLookupGroup/{pkCompany}/{lookupGroup}")
	@ResponseBody
	public List<CompanyLookup> findByLookupGroup(@PathVariable Long pkCompany, @PathVariable("lookupGroup") String lookupGroup) throws SystemException {
		return companyLookupService.findByLookupGroup(pkCompany, lookupGroup);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findByLookupGroups")
	@ResponseBody
	public List<CompanyLookup> findByLookupGroup(@RequestParam(value="lg") List<String> lookupGroups) throws SystemException {
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
			filter.add(new SearchFilter(CompanyLookup.STATUS, Operator.EQUALS, SystemConstant.ValidFlag.VALID));
			filter.add(new SearchFilter(CompanyLookup.LOOKUP_GROUP_STRING, Operator.IN, lookupGroups));
		List<SearchOrder> order = new ArrayList<SearchOrder>();
			order.add(new SearchOrder(CompanyLookup.LOOKUP_GROUP_STRING, Sort.ASC));
			order.add(new SearchOrder(CompanyLookup.ORDER_NO, Sort.ASC));
		return companyLookupService.findAll(filter, order);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findCompanyLookupByLookupGroup/{pkCompany}/{lookupGroup}/{orderBy}/{desc}")
	@ResponseBody
	public List<CompanyLookup> findByLookupGroupOrderBy(@PathVariable("pkCompany") Long pkCompany, @PathVariable("lookupGroup") String lookupGroup, @PathVariable("orderBy") String orderBy, @PathVariable("desc") boolean desc) throws SystemException {
		return companyLookupService.findByLookupGroupOrderBy(pkCompany, lookupGroup, ORDER_BY.get(orderBy), desc);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findById/{id}")
	@ResponseBody
	public CompanyLookup findLookupById(@PathVariable("id") Long id) throws SystemException {
		return companyLookupService.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findCompanyLookupByCodeAndLookupGroup/{pkCompany}/{code}/{lookupGroup}")
	@ResponseBody
	public CompanyLookup findLookupByCodeAndLookupGroup(@PathVariable("pkCompany") Long pkCompany, @PathVariable("code") String code, @PathVariable("lookupGroup") String lookupGroup) throws SystemException {
		return companyLookupService.findByCode(pkCompany, code, lookupGroup);
	}
	
	@Override
	public MaintenanceService<CompanyLookup> getMaintenanceService() {
		return this.companyLookupService;
	}
	
	@Override
	public CompanyLookup preUpdate(CompanyLookup anObject) throws SystemException {
		return validate(anObject);
	}
	
	@Override
	public CompanyLookup preCreate(CompanyLookup anObject) throws SystemException {
		return validate(anObject);
	}
	
	@Override
	public Long[] preDelete(Long[] objectPKs) throws SystemException {
		return objectPKs;
	}
	
	@Override
	public CompanyLookup validate(CompanyLookup anObject){
		List<ErrorHolder> errors = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getLookupGroupString())){
			errors.add(new ErrorHolder(CompanyLookup.LOOKUP_GROUP_STRING, messageSource.getMessage("error.message.company.lookup.mandatory.lookupGroup", null, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getNameId())){
			errors.add(new ErrorHolder(CompanyLookup.NAME_ID, messageSource.getMessage("error.message.company.lookup.mandatory.nameId", null, Locale.ENGLISH)));
		}
		if(anObject.getOrderNo() == null){
			errors.add(new ErrorHolder(CompanyLookup.ORDER_NO, messageSource.getMessage("error.message.company.lookup.mandatory.orderNo", null, Locale.ENGLISH)));
		}
		if(errors != null && errors.size() > 0) {
			throw new SystemException(errors);
		}
		return anObject;
	}

}
