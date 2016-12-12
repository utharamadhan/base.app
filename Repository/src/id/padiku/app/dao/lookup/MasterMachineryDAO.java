package id.padiku.app.dao.lookup;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.MasterMachinery;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class MasterMachineryDAO extends AbstractHibernateDAO<MasterMachinery,Long> implements IMasterMachineryDAO {

	@Override
	public MasterMachinery findById(Long id) throws SystemException {
		return super.findByPK(id);
	}

	@Override
	public void saveOrUpdate(MasterMachinery anObject) throws SystemException {
		if(anObject.getPkMasterMachinery()==null)
			super.create(anObject);
		else
		    super.update(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<MasterMachinery> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<MasterMachinery> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return super.findAllWithPagingWrapper(startNo, offset, filter, order, null);
	}

}
