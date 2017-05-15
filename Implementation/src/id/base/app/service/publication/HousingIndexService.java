package id.base.app.service.publication;

import id.base.app.dao.publication.IHousingIndexDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.publication.HousingIndex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HousingIndexService implements IHousingIndexService {

	@Autowired
	private IHousingIndexDAO housingIndexDAO;
	
	@Override
	public HousingIndex findById(Long id) throws SystemException {
		return housingIndexDAO.findById(id);
	}

	@Override
	public void saveOrUpdate(HousingIndex anObject) throws SystemException {
		housingIndexDAO.saveOrUpdate(anObject);
	}

	@Override
	public void delete(Long[] objectPKs) throws SystemException {
		housingIndexDAO.delete(objectPKs);
	}

	@Override
	public List<HousingIndex> findObjects(Long[] objectPKs)
			throws SystemException {
		List<HousingIndex> objects = new ArrayList<>();
		HousingIndex object = null;
		for(Long l:objectPKs){
			object = housingIndexDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public PagingWrapper<HousingIndex> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return housingIndexDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<HousingIndex> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return housingIndexDAO.findAll(filter, order);
	}

}
