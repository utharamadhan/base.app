package id.padiku.app.service.master;

import id.padiku.app.dao.master.ICompanyWarehouseDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.master.CompanyWarehouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CompanyWarehouseService implements ICompanyWarehouseService{

	@Autowired
	private ICompanyWarehouseDAO companyWarehouseDAO;
	
	@Override
	public CompanyWarehouse findById(Long id) throws SystemException {
		return companyWarehouseDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(CompanyWarehouse anObject) throws SystemException {
		companyWarehouseDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		companyWarehouseDAO.delete(objectPKs);
	}

	@Override
	public List<CompanyWarehouse> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<CompanyWarehouse> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return companyWarehouseDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<CompanyWarehouse> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return null;
	}

	@Override
	public List<CompanyWarehouse> findAllByCompany(Long pkCompany) throws SystemException {
		return companyWarehouseDAO.findAllByCompany(pkCompany);
	}
	
}
