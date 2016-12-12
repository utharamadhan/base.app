package id.base.app.service.transaction;

import id.base.app.dao.transaction.IGoodsReceiptNoteDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.inventory.GoodsReceiptNote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GoodsReceiptNoteService implements IGoodsReceiptNoteService {
	
	@Autowired
	private IGoodsReceiptNoteDAO grnDAO;
	
	@Override
	public GoodsReceiptNote findById(Long id) throws SystemException {
		return null;
	}

	@Override
	public void saveOrUpdate(GoodsReceiptNote anObject) throws SystemException {
		grnDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		grnDAO.delete(objectPKs);
	}

	@Override
	public List<GoodsReceiptNote> findObjects(Long[] objectPKs)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingWrapper<GoodsReceiptNote> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return grnDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<GoodsReceiptNote> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return grnDAO.findAll(filter, order);
	}

	@Override
	public GoodsReceiptNote getGoodsReceiptNote(Long maintenancePK) throws SystemException {
		return grnDAO.getGoodsReceiptNoteById(maintenancePK);
	}
	
}