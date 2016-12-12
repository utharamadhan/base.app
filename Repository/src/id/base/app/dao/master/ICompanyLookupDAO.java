package id.base.app.dao.master;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.master.CompanyLookup;

import java.util.List;


public interface ICompanyLookupDAO extends IBaseDAO<CompanyLookup> {
	
	public List<CompanyLookup> findLookupByLookupGroup(Long pkLookup, String lookupGroup) throws SystemException;
	
	public List<CompanyLookup> findLookupByLookupGroupOrderBy(Long pkCompany, String lookupGroup, String orderBy, boolean desc) throws SystemException;
	
	public CompanyLookup findByCode(Long pkCompany, String code, String lookupGroup);
	
	public Boolean isAlreadyExist(Long pkCompany, String lookupGroup, String code, Long orderNo, Long pkCompanyLookup) throws SystemException;

}
