package id.base.app.controller.research;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.research.IResearchImplementationService;
import id.base.app.service.research.IResearchOfficerService;
import id.base.app.service.research.IResearchService;
import id.base.app.util.StringFunction;
import id.base.app.validation.InvalidRequestException;
import id.base.app.valueobject.UpdateEntity;
import id.base.app.valueobject.research.Research;
import id.base.app.valueobject.research.ResearchKeyword;
import id.base.app.valueobject.research.ResearchOfficer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_RESEARCH)
public class ResearchController extends SuperController<Research>{
	
	@Autowired
	private IResearchService researchService;
	@Autowired
	private IResearchOfficerService researchOfficerService;
	@Autowired
	private IResearchImplementationService researchImplementationService;

	@Override
	public Research validate(Research anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(Research.TITLE, messageSource.getMessage("error.mandatory", new String[]{"title"}, Locale.ENGLISH)));
		}else{
			String permalink = StringFunction.toPrettyURL(anObject.getTitle());
			List<String> permalinkDBList = researchService.getSamePermalink(anObject.getPkResearch(), permalink);
			permalink = StringFunction.generatePermalink(permalinkDBList, permalink);
			anObject.setPermalink(permalink);	
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<Research> getMaintenanceService() {
		return researchService;
	}
	
	@Override
	public Research preUpdate(Research anObject) throws SystemException{
		if(anObject.getPkResearch()!=null){
			if(SystemConstant.TabResearchManagement.ORGANIZER.equals(anObject.getFromTab()) || 
					SystemConstant.TabResearchManagement.PRE.equals(anObject.getFromTab())){
				Research newObject = getMaintenanceService().findById(anObject.getPkResearch());
				if(SystemConstant.TabResearchManagement.ORGANIZER.equals(anObject.getFromTab())){
					newObject.setIsInternal(anObject.getIsInternal());
				}
				if(SystemConstant.TabResearchManagement.PRE.equals(anObject.getFromTab())){
					newObject.setTitle(anObject.getTitle());
					newObject.setSubtitle(anObject.getSubtitle());
					newObject.setImageURL(anObject.getImageURL());
					newObject.setDescription(anObject.getDescription());
					newObject.setPrinciplePermitDescription(anObject.getPrinciplePermitDescription());
					newObject.setPrinciplePermitFile(anObject.getPrinciplePermitFile());
					newObject.setProcurementDescription(anObject.getProcurementDescription());
					newObject.setProcurementFile(anObject.getProcurementFile());
					newObject.setWorkOrderDescription(anObject.getWorkOrderDescription());
					newObject.setWorkOrderFile(anObject.getWorkOrderFile());
					newObject.setVendor(anObject.getVendor());
					newObject.setProjectValue(anObject.getProjectValue());
				}
				anObject = newObject;
			}
			
		} else if(StringFunction.isEmpty(anObject.getTitle())){
			anObject.setTitle(SystemConstant.DEFAULT_TITLE_RESEARCH);
		}
		if(anObject.getOfficers()!=null && !anObject.getOfficers().isEmpty()){
			for (ResearchOfficer officer : anObject.getOfficers()) {
				officer.setResearch(anObject);
			}
		}
		if(anObject.getKeywords()!=null && !anObject.getKeywords().isEmpty()){
			for (ResearchKeyword keyword : anObject.getKeywords()) {
				keyword.setResearch(anObject);
			}
		}
		return validate(anObject);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/updateReturn")
	@ResponseBody
	@ResponseStatus( HttpStatus.OK )
	public Long updateReturn(@RequestBody @Validated(UpdateEntity.class) Research research, BindingResult bindingResult) throws SystemException {
		if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid validation", bindingResult);
        }
	    getMaintenanceService().saveOrUpdate(preUpdate(research));
	    return research.getPkResearch();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findOfficerByFkResearch/{fkResearch}")
	@ResponseBody
	public List<ResearchOfficer> findOfficerByFkResearch(@PathVariable(value="fkResearch") Long fkResearch, HttpServletResponse response){
		return researchOfficerService.findByFkResearch(fkResearch);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findOfficerById/{id}")
	@ResponseBody
	public ResearchOfficer findOfficerById(@PathVariable(value="id") Long id, HttpServletResponse response){
		return researchOfficerService.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/deleteOfficer")
	@ResponseStatus( HttpStatus.OK )
	public void deleteOfficer(@RequestParam(value="objectPKs") Long[] objectPKs) throws SystemException {
		researchOfficerService.delete(objectPKs);
	}
	
	private ResearchOfficer validateOfficer(ResearchOfficer anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getOfficerName())) {
			errorList.add(new ErrorHolder(ResearchOfficer.OFFICER_NAME, messageSource.getMessage("error.mandatory", new String[]{"Officer Name"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getOfficerPosition())) {
			errorList.add(new ErrorHolder(ResearchOfficer.OFFICER_POSITION, messageSource.getMessage("error.mandatory", new String[]{"Officer Position"}, Locale.ENGLISH)));
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	private ResearchOfficer preUpdateOfficer(ResearchOfficer anObject) throws SystemException{
		Research research = Research.getInstance();
		research.setPkResearch(anObject.getFkResearch());
		anObject.setResearch(research);
		return validateOfficer(anObject);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/saveOfficer")
	@ResponseStatus( HttpStatus.OK )
	public void saveOfficer(@RequestBody ResearchOfficer anObject, BindingResult bindingResult) throws SystemException {
		if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid validation", bindingResult);
        }
		researchOfficerService.saveOrUpdate(preUpdateOfficer(anObject));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findByPermalink/{permalink}")
	@ResponseBody
	public Research findByPermalink(@PathVariable(value="permalink") String permalink) throws SystemException {
		return researchService.findByPermalink(permalink);
	}
}