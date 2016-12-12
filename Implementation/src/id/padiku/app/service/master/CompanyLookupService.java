package id.padiku.app.service.master;

import id.padiku.app.dao.master.ICompanyLookupDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.Lookup;
import id.padiku.app.valueobject.master.CompanyLookup;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyLookupService implements ICompanyLookupService {

	private static final Logger logger = LoggerFactory.getLogger(CompanyLookupService.class);
	
	@Autowired
	protected  ICompanyLookupDAO companyLookupDAO;	
    
	public PagingWrapper<Lookup> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public CompanyLookup findById(Long id) throws SystemException {
		return companyLookupDAO.findById(id);
	}

	public void saveOrUpdate(CompanyLookup anObject) throws SystemException {
		companyLookupDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		companyLookupDAO.delete(objectPKs);
	}
	
	@Override
	public List<CompanyLookup> findByLookupGroup(Long pkCompany, String lookupGroup) throws SystemException{
		return companyLookupDAO.findLookupByLookupGroup(pkCompany, lookupGroup);
	}
	
	@Override
	public List<CompanyLookup> findByLookupGroupOrderBy(Long pkCompany, String lookupGroup, String orderBy, boolean desc) throws SystemException{
		return companyLookupDAO.findLookupByLookupGroupOrderBy(pkCompany, lookupGroup, orderBy, desc);
	}

	public PagingWrapper<CompanyLookup> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return companyLookupDAO.findAllByFilter(startNo, offset, filter, order);
	}

	public CompanyLookup findByCode(Long pkCompany, String code, String lookupGroup) {
		return companyLookupDAO.findByCode(pkCompany, code, lookupGroup);
	}
	
	@Override
	public List<CompanyLookup> findObjects(Long[] objectPKs) throws SystemException {
		List<CompanyLookup> lookups=new ArrayList<>();
		CompanyLookup lookup=null;
		for(Long l:objectPKs){
			lookup=companyLookupDAO.findById(l);
			lookups.add(lookup);
		}
		return lookups;
	}

	@Override
	public List<CompanyLookup> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return companyLookupDAO.findAll(filter, order);
	}

	@Override
	public Boolean isAlreadyExist(Long pkCompany, String lookupGroup, String code, Long orderNo, Long pkCompanyLookup) throws SystemException {
		return companyLookupDAO.isAlreadyExist(pkCompany, lookupGroup, code, orderNo, pkCompanyLookup);
	}
}