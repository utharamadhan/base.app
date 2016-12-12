package id.padiku.app.service.transaction;

import id.padiku.app.dao.transaction.ITransInItemDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.procurement.TransInItem;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import softtech.hong.hce.core.QueryTransformer;
import softtech.hong.hce.model.Expression;

@Service
@Transactional
public class TransInItemService extends QueryTransformer<TransInItem> implements ITransInItemService{

	@Autowired
	private ITransInItemDAO transInItemDAO;
	
	@Override
	public TransInItem findById(Long id) throws SystemException {
		return null;
	}

	@Override
	public void saveOrUpdate(TransInItem anObject) throws SystemException {
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<TransInItem> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<TransInItem> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return transInItemDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<TransInItem> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return null;
	}
	
	@Override
	public TransInItem findByIdForAddToStock(Long pkTransInItem) throws SystemException{
		Expression exp = new Expression();
		exp.add(Expression.eq(TransInItem.ID, pkTransInItem));
		DetachedCriteria detachedCriteria = criteriaByProperty(TransInItem.MAINTENANCE_LIST_FOR_ADD_TO_STOCK, exp);
		return transInItemDAO.first(detachedCriteria);
	}
	
	
	@Override
	public int updateRemainingVolume(Long pkTransInItem, BigDecimal volume) {
		return transInItemDAO.updateRemainingVolume(pkTransInItem, volume);
	}
	
	@Override
	public int updateStatus(Long pkTransInItem, Integer status) {
		return transInItemDAO.updateStatus(pkTransInItem, status);
	}

}
