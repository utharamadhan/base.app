package id.padiku.app.dao.lookup;

import id.padiku.app.IBaseDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.valueobject.LookupAddress;
import id.padiku.app.valueobject.MasterAddress;

import java.util.List;

public interface IMasterAddressDAO extends IBaseDAO<MasterAddress>{
	
	public List<LookupAddress> findAddressByParent(String addressGroupSource, Long fkLookupAddressParent) throws SystemException;
	
	public List<Integer> searchPostalCode(String keyword) throws SystemException;

}
