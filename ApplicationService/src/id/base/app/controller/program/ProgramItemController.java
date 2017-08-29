package id.base.app.controller.program;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.program.IProgramItemService;
import id.base.app.util.ImageFunction;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.program.ProgramItem;
import id.base.app.valueobject.program.ProgramItemMenu;
import id.base.app.valueobject.program.ProgramItemTeacher;

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
@RequestMapping(RestConstant.RM_PROGRAM_ITEM)
public class ProgramItemController extends SuperController<ProgramItem> {

	@Autowired
	private IProgramItemService programItemService;

	@Override
	public ProgramItem validate(ProgramItem anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if (StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(ProgramItem.TITLE,
					messageSource.getMessage("error.mandatory", new String[] { "title" }, Locale.ENGLISH)));
		}else{
			String permalink = StringFunction.toPrettyURL(anObject.getTitle());
			List<String> permalinkDBList = programItemService.getSamePermalink(anObject.getPkProgramItem(), permalink);
			permalink = StringFunction.generatePermalink(permalinkDBList, permalink);
			anObject.setPermalink(permalink);
		}
		if(anObject.getDateFrom()==null) {
			errorList.add(new ErrorHolder(ProgramItem.DATE_FROM, messageSource.getMessage("error.mandatory", new String[]{"Event Date From"}, Locale.ENGLISH)));
		}
		if(anObject.getMethodLookup()==null || anObject.getMethodLookup().getPkLookup()==null) {
			errorList.add(new ErrorHolder(ProgramItem.METHOD_LOOKUP_PK, messageSource.getMessage("error.mandatory", new String[]{"Method"}, Locale.ENGLISH)));
		}
		if(anObject.getOrganizerLookup()==null || anObject.getOrganizerLookup().getPkLookup()==null) {
			errorList.add(new ErrorHolder(ProgramItem.ORGANIZER_LOOKUP_PK, messageSource.getMessage("error.mandatory", new String[]{"Organizer"}, Locale.ENGLISH)));
		}
		if(anObject.getPaymentLookup()==null || anObject.getPaymentLookup().getPkLookup()==null) {
			errorList.add(new ErrorHolder(ProgramItem.PAYMENT_LOOKUP_PK, messageSource.getMessage("error.mandatory", new String[]{"Payment"}, Locale.ENGLISH)));
		}
		if (StringFunction.isEmpty(anObject.getDescription())) {
			errorList.add(new ErrorHolder(ProgramItem.DESCRIPTION, messageSource.getMessage("error.mandatory", new String[] { "Description" }, Locale.ENGLISH)));
		}
		if (errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}

	@Override
	public MaintenanceService<ProgramItem> getMaintenanceService() {
		return programItemService;
	}

	private void preProcessUpdate(ProgramItem anObject) throws SystemException {
		if(anObject.getPkProgramItem()!=null){
			if(SystemConstant.CategoryType.ADVISORY.equalsIgnoreCase(anObject.getType())){
				ProgramItem oldObject = findById(anObject.getPkProgramItem());
				anObject.setCategories(oldObject.getCategories());
			}
		}
		if(anObject.getTeachers()!=null && !anObject.getTeachers().isEmpty()){
			for (ProgramItemTeacher teacher : anObject.getTeachers()) {
				teacher.setProgramItem(anObject);
			}
		}
		if(anObject.getMenus()!=null && !anObject.getMenus().isEmpty()){
			for (ProgramItemMenu menu : anObject.getMenus()) {
				menu.setProgramItem(anObject);
			}
		}
		
	}
	
	@Override
	public ProgramItem preUpdate(ProgramItem anObject) throws SystemException {
		preProcessUpdate(anObject);
		return validate(anObject);
	}
	
	@Override
	public void postUpdate(Object oldObject, ProgramItem newObject) {
		try {
			if(oldObject != null && oldObject instanceof ProgramItem && newObject != null){
				ProgramItem objUpdate = new ProgramItem();
				Boolean isUpdate = false;
				if(StringFunction.isNotEmpty(newObject.getImageURL())){
					if (!((ProgramItem)oldObject).getImageURL().equalsIgnoreCase(newObject.getImageURL())) {
						String oldURL = ((ProgramItem)oldObject).getImageURL();
						deleteOldImage(oldURL);
						String thumbURL = ImageFunction.createThumbnails(newObject.getImageURL(), SystemConstant.ThumbnailsDimension.FeatureImage.WIDTH, SystemConstant.ThumbnailsDimension.FeatureImage.HEIGHT);
						objUpdate.setImageURL(thumbURL);
						isUpdate = true;
					}
				}else{
					String thumbURL = ImageFunction.createThumbnails(newObject.getImageURL(), SystemConstant.ThumbnailsDimension.FeatureImage.WIDTH, SystemConstant.ThumbnailsDimension.FeatureImage.HEIGHT);
					objUpdate.setImageURL(thumbURL);
					isUpdate = true;
				}
				
				if(StringFunction.isNotEmpty(newObject.getBrochureURL())){
					if (!((ProgramItem)oldObject).getBrochureURL().equalsIgnoreCase(newObject.getBrochureURL())) {
						String oldURL = ((ProgramItem)oldObject).getBrochureURL();
						deleteOldImage(oldURL);
						String brochureURL = ImageFunction.createThumbnails(newObject.getBrochureURL(), SystemConstant.ThumbnailsDimension.FeatureImage.WIDTH, SystemConstant.ThumbnailsDimension.FeatureImage.HEIGHT);
						objUpdate.setBrochureURL(brochureURL);
						isUpdate = true;
					}
				}else{
					String brochureURL = ImageFunction.createThumbnails(newObject.getBrochureURL(), SystemConstant.ThumbnailsDimension.FeatureImage.WIDTH, SystemConstant.ThumbnailsDimension.FeatureImage.HEIGHT);
					objUpdate.setBrochureURL(brochureURL);
					isUpdate = true;
				}
				if(isUpdate){
					programItemService.updateAnyUrl(newObject.getPkProgramItem(), objUpdate);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	@Override
	public void postDelete(ProgramItem oldObject) {
		try {
			if(oldObject!=null){
				if(StringFunction.isNotEmpty(oldObject.getImageURL())) {
					deleteOldImage(oldObject.getImageURL());
				}
				if(StringFunction.isNotEmpty(oldObject.getBrochureURL())) {
					deleteOldImage(oldObject.getBrochureURL());
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	@Override
	public ProgramItem getOldObject(ProgramItem object) throws SystemException {
		ProgramItem oldObject = new ProgramItem();
		return object.getPkProgramItem() != null ? cloneObject(oldObject, findById(object.getPkProgramItem())) : null;
	}
	
	@RequestMapping(value = "/findAllCourseCodeName")
	@ResponseBody
	public List<ProgramItem> findAllCourseCodeName() throws SystemException {
		return programItemService.findAllCourseCodeName();
	}

	@RequestMapping(value = "/findAllCourseAndTags")
	@ResponseBody
	public List<ProgramItem> findAllCourseAndTags(@RequestParam Map<String, Object> paramWrapper) throws SystemException {
		return programItemService.findAllCourseAndTags(paramWrapper);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findByPermalink/{permalink}")
	@ResponseBody
	public ProgramItem findByPermalink(@PathVariable(value="permalink") String permalink) throws SystemException {
		return programItemService.findByPermalink(permalink);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findForSelectEligibleReg/{pkcategory}")
	@ResponseBody
	public List<ProgramItem> findForSelectEligibleReg(@PathVariable(value="pkcategory") Long pkcategory) throws SystemException {
		return programItemService.findForSelectEligibleReg(pkcategory);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findForSelectEligibleRegByCategoryPermalink/{categoryPermalink}")
	@ResponseBody
	public List<ProgramItem> findForSelectEligibleRegByCategoryPermalink(@PathVariable(value="categoryPermalink") String categoryPermalink) throws SystemException {
		return programItemService.findForSelectEligibleRegByCategoryPermalink(categoryPermalink);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findForSelectByType/{type}")
	@ResponseBody
	public List<ProgramItem> findForSelectByType(@PathVariable(value="type") String type) throws SystemException {
		return programItemService.findForSelectByType(type);
	}

}