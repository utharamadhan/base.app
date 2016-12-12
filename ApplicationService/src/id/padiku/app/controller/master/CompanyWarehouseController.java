package id.padiku.app.controller.master;

import id.padiku.app.controller.SuperController;
import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.service.master.ICompanyWarehouseService;
import id.padiku.app.valueobject.master.CompanyWarehouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_COMPANY_WAREHOUSE)
public class CompanyWarehouseController extends SuperController<CompanyWarehouse> {

	@Autowired
	private ICompanyWarehouseService companyWarehouseService;
	
	@Override
	public MaintenanceService<CompanyWarehouse> getMaintenanceService() {
		return companyWarehouseService;
	}

	@Override
	public CompanyWarehouse validate(CompanyWarehouse anObject)
			throws SystemException {
		return null;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findAllByCompany/{pkCompany}")
	@ResponseBody
	public List<CompanyWarehouse> findAllByCompany(@PathVariable(value="pkCompany") Long pkCompany) throws SystemException {
		return companyWarehouseService.findAllByCompany(pkCompany);
	}

}
