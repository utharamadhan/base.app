package id.base.app.service.aboutUs;

import id.base.app.dao.aboutUs.ICommonPostDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.aboutUs.CommonPost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommonPostService implements ICommonPostService {

	@Autowired
	private ICommonPostDAO commonPostDAO;
    
	public PagingWrapper<CommonPost> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public CommonPost findById(Long id) throws SystemException {
		return commonPostDAO.findById(id);
	}

	public void saveOrUpdate(CommonPost anObject) throws SystemException {
		commonPostDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		commonPostDAO.delete(objectPKs);
	}

	public PagingWrapper<CommonPost> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return commonPostDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<CommonPost> findObjects(Long[] objectPKs) throws SystemException {
		List<CommonPost> objects = new ArrayList<>();
		CommonPost object = null;
		for(Long l:objectPKs){
			object = commonPostDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<CommonPost> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return commonPostDAO.findAll(filter, order);
	}

}
