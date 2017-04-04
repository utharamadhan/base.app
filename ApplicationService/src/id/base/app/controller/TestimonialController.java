package id.base.app.controller;

import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.testimonial.ITestimonialService;
import id.base.app.valueobject.publication.News;
import id.base.app.valueobject.testimonial.Testimonial;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_TESTIMONIAL)
public class TestimonialController extends SuperController<Testimonial>{
	
	@Autowired
	private ITestimonialService testimonialService;
	
	@Override
	public MaintenanceService<Testimonial> getMaintenanceService() {
		return testimonialService;
	}

	@Override
	public Testimonial validate(Testimonial anObject) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<>();
		if(anObject.getName() == null){
			errors.add(new ErrorHolder("Name is Mandatory"));
		}
		if(errors != null && errors.size() > 0){
			throw new SystemException(errors);
		}
		return anObject;
	}	
	
	@Override
	public Testimonial preUpdate(Testimonial anObject) throws SystemException{
		return validate(anObject);
	}
}