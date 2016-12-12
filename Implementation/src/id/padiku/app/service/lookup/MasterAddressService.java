package id.padiku.app.service.lookup;

import id.padiku.app.ILookupAddressGroupConstant;
import id.padiku.app.dao.lookup.ILookupAddressDAO;
import id.padiku.app.dao.lookup.IMasterAddressDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.LookupAddress;
import id.padiku.app.valueobject.MasterAddress;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import softtech.hong.hce.core.QueryTransformer;
import softtech.hong.hce.model.Expression;

@Service
@Transactional
public class MasterAddressService extends QueryTransformer<MasterAddress> implements IMasterAddressService {

	@Autowired
	private IMasterAddressDAO masterAddressDAO;
	
	@Autowired
	private ILookupAddressDAO lookupAddressDAO;
	
	@Override
	public MasterAddress findById(Long id) throws SystemException {
		return null;
	}

	@Override
	public void saveOrUpdate(MasterAddress anObject) throws SystemException {
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
	}

	@Override
	public List<MasterAddress> findObjects(Long[] objectPKs)
			throws SystemException {
		return null;
	}

	@Override
	public PagingWrapper<MasterAddress> findAllByFilter(int startNo,
			int offset, List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return null;
	}

	@Override
	public List<MasterAddress> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return null;
	}

	@Override
	public MasterAddress findByKodepos(Integer kodepos) throws SystemException {
		Expression exp = new Expression();
		exp.add(Expression.eq(MasterAddress.KODEPOS, kodepos));
		DetachedCriteria detachedCriteria = criteriaByProperty(MasterAddress.ALL_ID_BY_KODEPOS_PROPERTIES, exp);
		return masterAddressDAO.first(detachedCriteria);
	}
	
	@Override
	public List<LookupAddress> findAddressByParent(String addressGroupSource, Long fkLookupAddressParent) throws SystemException {
		return masterAddressDAO.findAddressByParent(addressGroupSource, fkLookupAddressParent);
	}
	
	@Override
	public Map<String, Object> findByKodeposWithList(Integer kodepos) throws SystemException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		MasterAddress ma = findByKodepos(kodepos);
		map.put("masterAddress", ma);
		map.put("provinsi", ma.getProvinsi());
		map.put("kabupatenKota", ma.getKabupatenKota());
		map.put("kecamatanList", findAddressByParent(ILookupAddressGroupConstant.KAB_KOTA, ma.getKabupatenKota().getPkLookupAddress()));
		map.put("kelurahanList", findAddressByParent(ILookupAddressGroupConstant.KECAMATAN, ma.getKecamatan().getPkLookupAddress()));	
		return map;
	}

	@Override
	public List<Integer> searchPostalCode(String keyword) throws SystemException {
		return masterAddressDAO.searchPostalCode(keyword);
	}
	
	@Override
	public Integer getKodepos(Long fkLookupAddressKelurahan) throws SystemException {
		Expression exp = new Expression();
		exp.add(Expression.eq(MasterAddress.KELURAHAN_ID, fkLookupAddressKelurahan));
		DetachedCriteria detachedCriteria = criteriaByProperty(new String[]{MasterAddress.KODEPOS}, exp);
		MasterAddress ma = masterAddressDAO.first(detachedCriteria);
		return ma.getKodepos();
	}
}