package id.base.app.controller;

import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.testimonial.ITestimonialService;
import id.base.app.util.ImageFunction;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.testimonial.Testimonial;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		if(StringFunction.isEmpty(anObject.getName())) {
			errors.add(new ErrorHolder(Testimonial.NAME, messageSource.getMessage("error.mandatory", new String[]{"Name"}, Locale.ENGLISH)));
		}else{
			String permalink = StringFunction.toPrettyURL(anObject.getName());
			List<String> permalinkDBList = testimonialService.getSamePermalink(anObject.getPkTestimonial(), permalink);
			permalink = StringFunction.generatePermalink(permalinkDBList, permalink);
			anObject.setPermalink(permalink);
		}
		if(StringFunction.isEmpty(anObject.getContent())) {
			errors.add(new ErrorHolder(Testimonial.CONTENT, messageSource.getMessage("error.mandatory", new String[]{"Testimonial"}, Locale.ENGLISH)));
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
	
	@Override
	public void postUpdate(Object oldObject, Testimonial newObject) {
		try {
			if(oldObject != null && oldObject instanceof Testimonial && newObject != null && StringFunction.isNotEmpty(newObject.getPhotoURL())) {
				if (!((Testimonial)oldObject).getPhotoURL().equalsIgnoreCase(newObject.getPhotoURL())) {
					String oldURL = ((Testimonial)oldObject).getPhotoURL();
					deleteOldImage(oldURL);
					String thumbURL = ImageFunction.createThumbnails(newObject.getPhotoURL(), SystemConstant.ThumbnailsDimension.Photo.WIDTH, SystemConstant.ThumbnailsDimension.Photo.HEIGHT);
					testimonialService.updateThumb(newObject.getPkTestimonial(), thumbURL);
				}
			}else{
				String thumbURL = ImageFunction.createThumbnails(newObject.getPhotoURL(), SystemConstant.ThumbnailsDimension.Photo.WIDTH, SystemConstant.ThumbnailsDimension.Photo.HEIGHT);
				testimonialService.updateThumb(newObject.getPkTestimonial(), thumbURL);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	@Override
	public Testimonial getOldObject(Testimonial object) throws SystemException {
		Testimonial oldObject = new Testimonial();
		return object.getPkTestimonial() != null ? cloneObject(oldObject, findById(object.getPkTestimonial())) : null;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findLatest")
	@ResponseBody
	public List<Testimonial> findLatest(@RequestParam(value="number") int number) throws SystemException {
		try {
			return testimonialService.findLatest(number);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(new ErrorHolder("error finding your data"));
		}
	}
	
}