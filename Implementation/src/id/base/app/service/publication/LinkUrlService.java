package id.base.app.service.publication;

import id.base.app.dao.publication.ILinkUrlDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.publication.LinkUrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LinkUrlService implements ILinkUrlService {

	@Autowired
	private ILinkUrlDAO linkUrlDAO;
    
	public PagingWrapper<LinkUrl> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public LinkUrl findById(Long id) throws SystemException {
		return linkUrlDAO.findById(id);
	}

	public void saveOrUpdate(LinkUrl anObject) throws SystemException {
		linkUrlDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		linkUrlDAO.delete(objectPKs);
	}

	public PagingWrapper<LinkUrl> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return linkUrlDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<LinkUrl> findObjects(Long[] objectPKs) throws SystemException {
		List<LinkUrl> objects = new ArrayList<>();
		LinkUrl object = null;
		for(Long l:objectPKs){
			object = linkUrlDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<LinkUrl> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return linkUrlDAO.findAll(filter, order);
	}
	
	@Override
	public List<String> getSamePermalink(Long pk, String permalink) throws SystemException {
		return linkUrlDAO.getSamePermalink(pk, permalink);
	}
	
	@Override
	public List<LinkUrl> findByPermalinkParent(String permalink) throws SystemException {
		return linkUrlDAO.findByPermalinkParent(permalink);
	}
	
	@Override
	public String getTitleByPermalink(String permalink) throws SystemException {
		return linkUrlDAO.getTitleByPermalink(permalink);
	}
	
	@Override
	public Long getPkByPermalink(String permalink) throws SystemException {
		return linkUrlDAO.getPkByPermalink(permalink);
	}
	
}