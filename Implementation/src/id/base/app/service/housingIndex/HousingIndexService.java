package id.base.app.service.housingIndex;

import id.base.app.dao.housingIndex.IHousingIndexDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.housingIndex.HousingIndex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HousingIndexService implements IHousingIndexService {

	@Autowired
	private IHousingIndexDAO HousingIndexDAO;
    
	public PagingWrapper<HousingIndex> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public HousingIndex findById(Long id) throws SystemException {
		return HousingIndexDAO.findById(id);
	}

	public void saveOrUpdate(HousingIndex anObject) throws SystemException {
		HousingIndexDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		HousingIndexDAO.delete(objectPKs);
	}

	public PagingWrapper<HousingIndex> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return HousingIndexDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<HousingIndex> findObjects(Long[] objectPKs) throws SystemException {
		List<HousingIndex> objects = new ArrayList<>();
		HousingIndex object = null;
		for(Long l:objectPKs){
			object = HousingIndexDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<HousingIndex> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return HousingIndexDAO.findAll(filter, order);
	}
}