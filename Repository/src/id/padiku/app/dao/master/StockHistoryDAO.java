package id.padiku.app.dao.master;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.master.StockHistory;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class StockHistoryDAO extends AbstractHibernateDAO<StockHistory, Long> implements IStockHistoryDAO {

	@Override
	public StockHistory findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(StockHistory anObject) throws SystemException {
		if(anObject.getPkStockHistory()!=null){
			super.update(anObject);
		}else{
			super.create(anObject);
		}
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		for(Long objectPK : objectPKs){
			StockHistory obj = new StockHistory();
			obj.setPkStockHistory(objectPK);
			super.delete(obj);
		}
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

}
