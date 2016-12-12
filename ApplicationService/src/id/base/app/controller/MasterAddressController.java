package id.base.app.controller;

import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.lookup.IMasterAddressService;
import id.base.app.valueobject.LookupAddress;
import id.base.app.valueobject.MasterAddress;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_MASTER_ADDRESS)
public class MasterAddressController extends SuperController<MasterAddress>{

	@Autowired
	@Qualifier("masterAddressService")
	private MaintenanceService<MasterAddress> maintenanceService;
	
	@Autowired
	@Qualifier("masterAddressService")
	private IMasterAddressService masterAddressService;
	
	@RequestMapping(method=RequestMethod.GET, value="/findAddressByParent/{addressGroupSource}/{fkLookupAddressParent}")
	@ResponseBody
	public List<LookupAddress> findAddressByParent(@PathVariable("addressGroupSource") String addressGroupSource, @PathVariable("fkLookupAddressParent") Long fkLookupAddressParent) throws SystemException {
		return masterAddressService.findAddressByParent(addressGroupSource, fkLookupAddressParent);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findByKodepos")
	@ResponseBody
	public MasterAddress findByKodepos(@RequestParam("kodepos") Integer kodepos) throws SystemException {
		return masterAddressService.findByKodepos(kodepos);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findByKodeposWithList")
	@ResponseBody
	public Map<String, Object> findByKodeposWithList(@RequestParam("kodepos") Integer kodepos) throws SystemException {
		return masterAddressService.findByKodeposWithList(kodepos);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/searchPostalCode/{keyword}")
	@ResponseBody
	public List<Integer> searchPostalCode(@PathVariable("keyword") final String keyword) throws SystemException {
		return masterAddressService.searchPostalCode(keyword);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getKodepos")
	@ResponseBody
	public Integer getKodepos(@RequestParam("fkLookupAddressKelurahan") Long fkLookupAddressKelurahan) throws SystemException {
		return masterAddressService.getKodepos(fkLookupAddressKelurahan);
	}

	@Override
	public MaintenanceService<MasterAddress> getMaintenanceService() {
		return maintenanceService;
	}

	@Override
	public MasterAddress validate(MasterAddress anObject) throws SystemException {
		return null;
	}

}
