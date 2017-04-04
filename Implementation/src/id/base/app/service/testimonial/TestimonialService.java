package id.base.app.service.testimonial;

import id.base.app.dao.testimonial.ITestimonialDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.testimonial.Testimonial;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestimonialService implements ITestimonialService {

	@Autowired
	private ITestimonialDAO testimonialDAO;
    
	public PagingWrapper<Testimonial> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public Testimonial findById(Long id) throws SystemException {
		return testimonialDAO.findById(id);
	}

	public void saveOrUpdate(Testimonial anObject) throws SystemException {
		testimonialDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		testimonialDAO.delete(objectPKs);
	}

	public PagingWrapper<Testimonial> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return testimonialDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<Testimonial> findObjects(Long[] objectPKs) throws SystemException {
		List<Testimonial> objects = new ArrayList<>();
		Testimonial object = null;
		for(Long l:objectPKs){
			object = testimonialDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<Testimonial> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return testimonialDAO.findAll(filter, order);
	}

	@Override
	public List<Testimonial> findLatest(int number) throws SystemException {
		return testimonialDAO.findLatest(number);
	}
}
