package id.padiku.app.service.master;

import id.padiku.app.exception.SystemException;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.valueobject.master.CompanyLookup;

import java.util.List;

public interface ICompanyLookupService extends MaintenanceService<CompanyLookup> {

	public List<CompanyLookup> findByLookupGroup(Long pkCompany, String lookupGroup) throws SystemException;
	
	public List<CompanyLookup> findByLookupGroupOrderBy(Long pkCompany, String lookupGroup, String orderBy, boolean desc) throws SystemException;

	public CompanyLookup findByCode(Long pkCompany, String code, String lookupGroup) throws SystemException;
	
	public Boolean isAlreadyExist(Long pkCompany, String lookupGroup, String code, Long orderNo, Long pkCompanyLookup) throws SystemException;
	
}