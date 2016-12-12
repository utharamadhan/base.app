package id.padiku.app.service.master;

import id.padiku.app.dao.master.ICompanyProductDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.Lookup;
import id.padiku.app.valueobject.master.CompanyProduct;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import softtech.hong.hce.core.QueryTransformer;
import softtech.hong.hce.model.Expression;

@Service
@Transactional
public class CompanyProductService extends QueryTransformer<CompanyProduct> implements ICompanyProductService {

	@Autowired
	private ICompanyProductDAO productDAO;
	
	@Override
	public CompanyProduct findById(Long id) throws SystemException {
		return productDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(CompanyProduct anObject) throws SystemException {
		anObject.setPredefined(Boolean.FALSE);
		productDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		productDAO.delete(objectPKs);
	}

	@Override
	public List<CompanyProduct> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<CompanyProduct> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return productDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<CompanyProduct> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return null;
	}

	@Override
	public List<CompanyProduct> findAll(Long pkCompany) throws SystemException {
		return productDAO.findAll(pkCompany);
	}
	
	@Override
	public List<CompanyProduct> findAll(Long pkCompany, String keyword) throws SystemException {
		return productDAO.findAll(pkCompany, keyword);
	}

	@Override
	public Boolean isMachineryUsedThisProduct(Long pkCompanyProduct) throws SystemException {
		return productDAO.isMachineryUsedThisProduct(pkCompanyProduct);
	}

	@Override
	public List<CompanyProduct> findAllProductByUsageType(Long pkCompany, String usageItemType) throws SystemException {
		return productDAO.findAllProductByUsageType(pkCompany, usageItemType);
	}
	
	@Override
	public Lookup findItemType(Long pkCompanyProduct) throws SystemException {
		Expression exp = new Expression();
		exp.add(Expression.eq(CompanyProduct.ID, pkCompanyProduct));
		DetachedCriteria detachedCriteria = criteriaByProperty(new String[]{CompanyProduct.ITEM_TYPE_ID}, exp);
		CompanyProduct cp = productDAO.first(detachedCriteria);
		return cp.getType();
	}
	
	@Override
	public List<CompanyProduct> findAllExistInStock(Long pkCompany) throws SystemException {
		return productDAO.findAllExistInStock(pkCompany);
	}
	
	@Override
	public Long countNotPredefinedAllByCompany(Long pkCompany) throws SystemException{
		return productDAO.countNotPredefinedAllByCompany(pkCompany);
	}
	
	@Override
	public int deleteAllByCompany(Long pkCompany){
		return productDAO.deleteAllByCompany(pkCompany);
	}
	
}
