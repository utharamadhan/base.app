package id.base.app.service.testimonial;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.testimonial.Testimonial;

import java.util.List;

public interface ITestimonialService extends MaintenanceService<Testimonial> {

	public List<Testimonial> findLatest(int number) throws SystemException;

	public void updateThumb(Long pkTestimonial, String thumbURL) throws SystemException;

}