package id.base.app.dao.testimonial;

import id.base.app.IBaseDAO;
import id.base.app.exception.SystemException;
import id.base.app.valueobject.testimonial.Testimonial;

import java.util.List;

public interface ITestimonialDAO extends IBaseDAO<Testimonial> {

	public List<Testimonial> findLatest(int number) throws SystemException;

}