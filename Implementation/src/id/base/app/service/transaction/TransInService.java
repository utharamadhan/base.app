package id.base.app.service.transaction;

import id.base.app.dao.transaction.ITransInDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.service.lookup.ILookupService;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.procurement.TransIn;

import java.util.List;

import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.collection.spi.PersistentCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransInService implements ITransInService{

	@Autowired
	private ITransInDAO transInDAO;
	
	@Autowired
	private ILookupService lookupService;
	
	@Override
	public TransIn findById(Long id) throws SystemException {
		TransIn obj = transInDAO.findById(id);
		if(obj.getFees() != null && obj.getFees() instanceof PersistentBag) {
			( (PersistentCollection) obj.getFees() ).forceInitialization();
		}
		if(obj.getItems() != null && obj.getItems() instanceof PersistentBag) {
			( (PersistentCollection) obj.getItems() ).forceInitialization();
		}
		return obj;
	}

	@Override
	public void saveOrUpdate(TransIn anObject) throws SystemException {
		transInDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		transInDAO.delete(objectPKs);
	}

	@Override
	public List<TransIn> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<TransIn> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return transInDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<TransIn> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return null;
	}
	
	@Override
	public Long countTransIn(Long pkCompany, String transInSourceType){
		return transInDAO.countTransIn(pkCompany, transInSourceType);
	}
}