package id.base.app.controller.learning;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.learning.ILearningItemService;
import id.base.app.util.ImageFunction;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.learning.LearningItem;
import id.base.app.valueobject.learning.LearningItemTeacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_LEARNING_ITEM)
public class LearningItemController extends SuperController<LearningItem> {

	@Autowired
	private ILearningItemService learningItemService;

	@Override
	public LearningItem validate(LearningItem anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if (StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(LearningItem.TITLE,
					messageSource.getMessage("error.mandatory", new String[] { "title" }, Locale.ENGLISH)));
		}else{
			String permalink = StringFunction.toPrettyURL(anObject.getTitle());
			List<String> permalinkDBList = learningItemService.getSamePermalink(anObject.getPkLearningItem(), permalink);
			permalink = StringFunction.generatePermalink(permalinkDBList, permalink);
			anObject.setPermalink(permalink);
		}
		if(anObject.getDateFrom()==null) {
			errorList.add(new ErrorHolder(LearningItem.DATE_FROM, messageSource.getMessage("error.mandatory", new String[]{"Event Date From"}, Locale.ENGLISH)));
		}
		if(anObject.getMethodLookup()==null || anObject.getMethodLookup().getPkLookup()==null) {
			errorList.add(new ErrorHolder(LearningItem.METHOD_LOOKUP_PK, messageSource.getMessage("error.mandatory", new String[]{"Method"}, Locale.ENGLISH)));
		}
		if(anObject.getOrganizerLookup()==null || anObject.getOrganizerLookup().getPkLookup()==null) {
			errorList.add(new ErrorHolder(LearningItem.ORGANIZER_LOOKUP_PK, messageSource.getMessage("error.mandatory", new String[]{"Organizer"}, Locale.ENGLISH)));
		}
		if(anObject.getPaymentLookup()==null || anObject.getPaymentLookup().getPkLookup()==null) {
			errorList.add(new ErrorHolder(LearningItem.PAYMENT_LOOKUP_PK, messageSource.getMessage("error.mandatory", new String[]{"Payment"}, Locale.ENGLISH)));
		}
		if (StringFunction.isEmpty(anObject.getDescription())) {
			errorList.add(new ErrorHolder(LearningItem.DESCRIPTION, messageSource.getMessage("error.mandatory", new String[] { "Description" }, Locale.ENGLISH)));
		}
		if (errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}

	@Override
	public MaintenanceService<LearningItem> getMaintenanceService() {
		return learningItemService;
	}

	private void preProcessUpdate(LearningItem anObject) throws SystemException {
		if(anObject.getTeachers()!=null && !anObject.getTeachers().isEmpty()){
			for (LearningItemTeacher teacher : anObject.getTeachers()) {
				teacher.setLearningItem(anObject);
			}
		}
	}
	
	@Override
	public LearningItem preUpdate(LearningItem anObject) throws SystemException {
		preProcessUpdate(anObject);
		return validate(anObject);
	}
	
	@Override
	public void postUpdate(Object oldObject, LearningItem newObject) {
		try {
			if(oldObject != null && oldObject instanceof LearningItem && newObject != null && StringFunction.isNotEmpty(newObject.getImageURL())) {
				if (!((LearningItem)oldObject).getImageURL().equalsIgnoreCase(newObject.getImageURL())) {
					String oldURL = ((LearningItem)oldObject).getImageURL();
					deleteOldImage(oldURL);
					String thumbURL = ImageFunction.createThumbnails(newObject.getImageURL(), SystemConstant.ThumbnailsDimension.FeatureImage.WIDTH, SystemConstant.ThumbnailsDimension.FeatureImage.HEIGHT);
					learningItemService.updateThumb(newObject.getPkLearningItem(), thumbURL);
				}
			}else{
				String thumbURL = ImageFunction.createThumbnails(newObject.getImageURL(), SystemConstant.ThumbnailsDimension.FeatureImage.WIDTH, SystemConstant.ThumbnailsDimension.FeatureImage.HEIGHT);
				learningItemService.updateThumb(newObject.getPkLearningItem(), thumbURL);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	@Override
	public void postDelete(LearningItem oldObject) {
		try {
			if(oldObject!=null && StringFunction.isNotEmpty(oldObject.getImageURL())) {
				deleteOldImage(oldObject.getImageURL());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	@Override
	public LearningItem getOldObject(LearningItem object) throws SystemException {
		LearningItem oldObject = new LearningItem();
		return object.getPkLearningItem() != null ? cloneObject(oldObject, findById(object.getPkLearningItem())) : null;
	}
	
	@RequestMapping(value = "/findAllCourseCodeName")
	@ResponseBody
	public List<LearningItem> findAllCourseCodeName() throws SystemException {
		return learningItemService.findAllCourseCodeName();
	}

	@RequestMapping(value = "/findAllCourseAndTags")
	@ResponseBody
	public List<LearningItem> findAllCourseAndTags(@RequestParam Map<String, Object> paramWrapper) throws SystemException {
		return learningItemService.findAllCourseAndTags(paramWrapper);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findByPermalink/{permalink}")
	@ResponseBody
	public LearningItem findByPermalink(@PathVariable(value="permalink") String permalink) throws SystemException {
		return learningItemService.findByPermalink(permalink);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findForSelectEligibleReg/{pkcategory}")
	@ResponseBody
	public List<LearningItem> findForSelectEligibleReg(@PathVariable(value="pkcategory") Long pkcategory) throws SystemException {
		return learningItemService.findForSelectEligibleReg(pkcategory);
	}

}