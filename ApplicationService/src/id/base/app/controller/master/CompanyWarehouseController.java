package id.base.app.controller.master;

import id.base.app.controller.SuperController;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.master.ICompanyWarehouseService;
import id.base.app.valueobject.master.CompanyWarehouse;

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
