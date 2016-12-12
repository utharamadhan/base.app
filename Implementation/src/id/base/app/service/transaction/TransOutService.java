package id.base.app.service.transaction;

import id.base.app.dao.transaction.ITransOutDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.service.master.IStockService;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.sales.TransOut;
import id.base.app.valueobject.sales.TransOutItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.collection.spi.PersistentCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransOutService implements ITransOutService{

	@Autowired
	private ITransOutDAO transOutDAO;
	
	@Autowired
	private IStockService stockService;
	
	@Override
	public TransOut findById(Long id) throws SystemException {
		TransOut obj = transOutDAO.findById(id);
		if(obj.getFees() != null && obj.getFees() instanceof PersistentBag) {
			( (PersistentCollection) obj.getFees() ).forceInitialization();
		}
		if(obj.getItems() != null && obj.getItems() instanceof PersistentBag) {
			( (PersistentCollection) obj.getItems() ).forceInitialization();
		}
		return transOutDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(TransOut anObject) throws SystemException {
		List<TransOutItem> items = new ArrayList<TransOutItem>();
		for (TransOutItem item : anObject.getItems()) {
			if(item.getVolumeInKg().compareTo(BigDecimal.ZERO)==1){
				items.add(item);
			}
		}
		anObject.getItems().clear();
		anObject.setItems(items);
		transOutDAO.saveOrUpdate(anObject);

		for (TransOutItem i : anObject.getItems()) {
			stockService.updateRemainingVolume(i.getStock().getPkStock(), i.getVolumeInKg());
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		transOutDAO.delete(objectPKs);
	}

	@Override
	public List<TransOut> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<TransOut> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return transOutDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<TransOut> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return null;
	}
	
}
