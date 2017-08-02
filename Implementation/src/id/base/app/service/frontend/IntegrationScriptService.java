package id.base.app.service.frontend;

import id.base.app.dao.frontend.IIntegrationScriptDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.frontend.IntegrationScript;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IntegrationScriptService implements IIntegrationScriptService {

	@Autowired
	private IIntegrationScriptDAO integrationScriptDAO;
	
	@Override
	public IntegrationScript findById(Long id) throws SystemException {
		return integrationScriptDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(IntegrationScript anObject) throws SystemException {
		integrationScriptDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		integrationScriptDAO.delete(objectPKs);	
	}

	@Override
	public List<IntegrationScript> findObjects(Long[] objectPKs)
			throws SystemException {
		return integrationScriptDAO.findObjects(objectPKs);
	}

	@Override
	public PagingWrapper<IntegrationScript> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return integrationScriptDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<IntegrationScript> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return integrationScriptDAO.findAll(filter, order);
	}	
}