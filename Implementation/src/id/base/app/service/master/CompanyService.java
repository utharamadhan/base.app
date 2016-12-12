package id.base.app.service.master;

import id.base.app.SystemConstant;
import id.base.app.dao.master.ICompanyDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.service.lookup.ILookupAddressService;
import id.base.app.service.lookup.IMasterFeeService;
import id.base.app.service.lookup.IMasterMachineryService;
import id.base.app.service.lookup.IMasterProductService;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.MasterFee;
import id.base.app.valueobject.MasterMachinery;
import id.base.app.valueobject.MasterProduct;
import id.base.app.valueobject.master.Company;
import id.base.app.valueobject.master.CompanyMachinery;
import id.base.app.valueobject.master.CompanyMasterFee;
import id.base.app.valueobject.master.CompanyProduct;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.collection.spi.PersistentCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyService implements ICompanyService{

	@Autowired
	private ICompanyDAO companyDAO;
	
	@Autowired
	private ILookupAddressService lookupAddressService;
	
	@Autowired
	private ICompanyProductService companyProductService;
	
	@Autowired
	private ICompanyMachineryService companyMachineryService;
	
	@Autowired
	private ICompanyMasterFeeService companyMasterFeeService;
	
	@Autowired
	private IMasterProductService masterProductService;
	
	@Autowired
	private IMasterMachineryService masterMachineryService;
	
	@Autowired
	private IMasterFeeService masterFeeService;
	
	@Override
	public Company findById(Long id) throws SystemException {
		Company obj = companyDAO.findById(id);
		//used for crud screen
		if(obj != null && obj.getCompanyAddresses() != null && obj.getCompanyAddresses() instanceof PersistentBag) {
			( (PersistentCollection) obj.getCompanyAddresses() ).forceInitialization();
		}
		if(obj != null && obj.getCompanyContacts() != null && obj.getCompanyAddresses() instanceof PersistentBag) {
			( (PersistentCollection) obj.getCompanyContacts() ).forceInitialization();
		}
		return obj;
	}
	
	@Override
	public void saveOrUpdate(Company anObject) throws SystemException {
		Boolean isAdd = (anObject.getPkCompany()==null);
		companyDAO.saveOrUpdate(anObject);
		if(isAdd){
			List<MasterProduct> mpList = masterProductService.findAllActive(new ArrayList<SearchFilter>(), new ArrayList<SearchOrder>());
			List<MasterMachinery> mmList = masterMachineryService.findAllActive(new ArrayList<SearchFilter>(), new ArrayList<SearchOrder>());
			List<MasterFee> mfList = masterFeeService.findAllActive(new ArrayList<SearchFilter>(), new ArrayList<SearchOrder>());
			
			for (MasterProduct mp : mpList) {
				CompanyProduct cp = new CompanyProduct();
				cp.setCompany(anObject);
				cp.setName(mp.getName());
				cp.setUom(mp.getUom());
				cp.setType(mp.getType());
				cp.setStatus(SystemConstant.ValidFlag.VALID);
				cp.setPredefined(Boolean.TRUE);
				companyProductService.saveOrUpdate(cp);
			}
			
			for (MasterMachinery mm : mmList) {
				CompanyMachinery cm = new CompanyMachinery();
				cm.setCompany(anObject);
				cm.setName(mm.getName());
				cm.setModel(mm.getModel());
				cm.setCapacity(mm.getCapacity());
				cm.setWeighting(mm.getWeighting());
				cm.setCapacityUOM(mm.getCapacityUOM());
				cm.setPowerSource(mm.getPowerSource());
				cm.setStatus(SystemConstant.ValidFlag.VALID);
				cm.setPredefined(Boolean.TRUE);
				companyMachineryService.saveOrUpdate(cm);
			}
			
			for (MasterFee mf : mfList) {
				CompanyMasterFee cmf = new CompanyMasterFee();
				cmf.setCompany(anObject);
				cmf.setFeeType(mf.getFeeType());
				cmf.setDescr(mf.getDescr());
				cmf.setUnitFee(mf.getUnitFee());
				cmf.setUom(mf.getUom());
				cmf.setStatus(SystemConstant.ValidFlag.VALID);
				cmf.setPredefined(Boolean.TRUE);
				companyMasterFeeService.saveOrUpdate(cmf);
			}
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		for (Long pk : objectPKs) {
			if(companyProductService.countNotPredefinedAllByCompany(pk)==0){
				companyProductService.deleteAllByCompany(pk);
			}
			if(companyMachineryService.countNotPredefinedAllByCompany(pk)==0){
				companyMachineryService.deleteAllByCompany(pk);
			}
			if(companyMasterFeeService.countNotPredefinedAllByCompany(pk)==0){
				companyMasterFeeService.deleteAllByCompany(pk);
			}
		}
		companyDAO.delete(objectPKs);
	}

	@Override
	public List<Company> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<Company> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return companyDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<Company> findAll(List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return null;
	}

	@Override
	public Company getCompanyByUser(Long pkAppUser) throws SystemException {
		return companyDAO.getCompanyByUser(pkAppUser);
	}

	@Override
	public List<Company> getCompaniesByUser(Long pkAppUser) throws SystemException {
		return companyDAO.getCompaniesByUser(pkAppUser);
	}
}
