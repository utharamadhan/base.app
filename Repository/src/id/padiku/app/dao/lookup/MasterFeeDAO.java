package id.padiku.app.dao.lookup;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.SystemConstant;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.MasterFee;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MasterFeeDAO extends AbstractHibernateDAO<MasterFee,Long> implements IMasterFeeDAO {

	@Override
	public MasterFee findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(MasterFee anObject) throws SystemException {
		if(anObject.getPkMasterFee()==null)
			super.create(anObject);
		else
		    super.update(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		for(Long objectPK : objectPKs){
			MasterFee obj = findById(objectPK);
				obj.setStatus(SystemConstant.ValidFlag.INVALID);
			super.update(obj);
		}
	}

	@Override
	public List<MasterFee> findObjects(Long[] objectPKs) throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<MasterFee> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findFetchedPropertyWithPagingWrapper(startNo, offset, null, MasterFee.MAINTENANCE_LIST_FIELDS ,filter, order, null);
	}

}
