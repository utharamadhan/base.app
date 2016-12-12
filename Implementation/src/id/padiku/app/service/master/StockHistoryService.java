package id.padiku.app.service.master;

import id.padiku.app.dao.master.IStockHistoryDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.master.StockHistory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StockHistoryService implements IStockHistoryService{

	@Autowired
	private IStockHistoryDAO stockHistoryDAO;
	
	@Override
	public StockHistory findById(Long id) throws SystemException {
		return stockHistoryDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(StockHistory anObject) throws SystemException {
		stockHistoryDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		stockHistoryDAO.delete(objectPKs);
	}

	@Override
	public List<StockHistory> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<StockHistory> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return null;
	}

	@Override
	public List<StockHistory> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return null;
	}

}
