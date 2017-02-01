package id.base.app.controller.research;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.research.IResearchBudgetingService;
import id.base.app.service.research.IResearchGoalTargetService;
import id.base.app.service.research.IResearchMemoService;
import id.base.app.service.research.IResearchService;
import id.base.app.service.research.IResearchTimePlanningService;
import id.base.app.util.StringFunction;
import id.base.app.validation.InvalidRequestException;
import id.base.app.valueobject.research.Research;
import id.base.app.valueobject.research.ResearchBudgeting;
import id.base.app.valueobject.research.ResearchGoalTarget;
import id.base.app.valueobject.research.ResearchMemo;
import id.base.app.valueobject.research.ResearchTimePlanning;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
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
	private IResearchTimePlanningService researchTimePlanningService;
	@Autowired
	private IResearchBudgetingService researchBudgetingService;
	@Autowired
	private IResearchGoalTargetService researchGoalTargetService;
	@Autowired
	private IResearchMemoService researchMemoService;

	@Override
	public Research validate(Research anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(Research.TITLE, messageSource.getMessage("error.mandatory", new String[]{"title"}, Locale.ENGLISH)));
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
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		return validate(anObject);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findTimePlanningByFkResearch/{fkResearch}")
	@ResponseBody
	public List<ResearchTimePlanning> findTimePlanningByFkResearch(@PathVariable(value="fkResearch") Long fkResearch, HttpServletResponse response){
		return researchTimePlanningService.findTimePlanningByFkResearch(fkResearch);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findTimePlanningById/{id}")
	@ResponseBody
	public ResearchTimePlanning findTimePlanningById(@PathVariable(value="id") Long id, HttpServletResponse response){
		return researchTimePlanningService.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/deleteTimePlanning")
	@ResponseStatus( HttpStatus.OK )
	public void deleteTimePlanning(@RequestParam(value="objectPKs") Long[] objectPKs) throws SystemException {
		researchTimePlanningService.delete(objectPKs);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findBudgetingByFkResearch/{fkResearch}")
	@ResponseBody
	public List<ResearchBudgeting> findBudgetingByFkResearch(@PathVariable(value="fkResearch") Long fkResearch, HttpServletResponse response){
		return researchBudgetingService.findBudgetingByFkResearch(fkResearch);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findBudgetingById/{id}")
	@ResponseBody
	public ResearchBudgeting findBudgetingById(@PathVariable(value="id") Long id, HttpServletResponse response){
		return researchBudgetingService.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/deleteBudgeting")
	@ResponseStatus( HttpStatus.OK )
	public void deleteBudgeting(@RequestParam(value="objectPKs") Long[] objectPKs) throws SystemException {
		researchBudgetingService.delete(objectPKs);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findGoalTargetByFkResearch/{fkResearch}")
	@ResponseBody
	public List<ResearchGoalTarget> findGoalTargetByFkResearch(@PathVariable(value="fkResearch") Long fkResearch, HttpServletResponse response){
		return researchGoalTargetService.findGoalTargetByFkResearch(fkResearch);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findGoalTargetById/{id}")
	@ResponseBody
	public ResearchGoalTarget findGoalTargetById(@PathVariable(value="id") Long id, HttpServletResponse response){
		return researchGoalTargetService.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/deleteGoalTarget")
	@ResponseStatus( HttpStatus.OK )
	public void deleteGoalTarget(@RequestParam(value="objectPKs") Long[] objectPKs) throws SystemException {
		researchGoalTargetService.delete(objectPKs);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findMemoByFkResearch/{fkResearch}")
	@ResponseBody
	public List<ResearchMemo> findMemoByFkResearch(@PathVariable(value="fkResearch") Long fkResearch, HttpServletResponse response){
		return researchMemoService.findMemoByFkResearch(fkResearch);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/findMemoById/{id}")
	@ResponseBody
	public ResearchMemo findMemoById(@PathVariable(value="id") Long id, HttpServletResponse response){
		return researchMemoService.findById(id);	
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/deleteMemo")
	@ResponseStatus( HttpStatus.OK )
	public void deleteMemo(@RequestParam(value="objectPKs") Long[] objectPKs) throws SystemException {
		researchMemoService.delete(objectPKs);
	}
	
	private ResearchTimePlanning validateTimePlanning(ResearchTimePlanning anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(anObject.getDateFrom()==null) {
			errorList.add(new ErrorHolder(ResearchTimePlanning.DATE_FROM, messageSource.getMessage("error.mandatory", new String[]{"Date From"}, Locale.ENGLISH)));
		}
		if(anObject.getDateTo()==null) {
			errorList.add(new ErrorHolder(ResearchTimePlanning.DATE_TO, messageSource.getMessage("error.mandatory", new String[]{"Date To"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(ResearchTimePlanning.TITLE, messageSource.getMessage("error.mandatory", new String[]{"Title"}, Locale.ENGLISH)));
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	private ResearchTimePlanning preUpdateTimePlanning(ResearchTimePlanning anObject) throws SystemException{
		Research research = new Research();
		research.setPkResearch(anObject.getFkResearch());
		anObject.setResearch(research);
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		return validateTimePlanning(anObject);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/saveTimePlanning")
	@ResponseStatus( HttpStatus.OK )
	public void saveTimePlanning(@RequestBody ResearchTimePlanning anObject, BindingResult bindingResult) throws SystemException {
		if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid validation", bindingResult);
        }
		researchTimePlanningService.saveOrUpdate(preUpdateTimePlanning(anObject));
	}
	
	private ResearchBudgeting validateBudgeting(ResearchBudgeting anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(anObject.getBudgetDate()==null) {
			errorList.add(new ErrorHolder(ResearchBudgeting.BUDGET_DATE, messageSource.getMessage("error.mandatory", new String[]{"Budget Date"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(ResearchBudgeting.TITLE, messageSource.getMessage("error.mandatory", new String[]{"Title"}, Locale.ENGLISH)));
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	private ResearchBudgeting preUpdateBudgeting(ResearchBudgeting anObject) throws SystemException{
		Research research = new Research();
		research.setPkResearch(anObject.getFkResearch());
		anObject.setResearch(research);
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		return validateBudgeting(anObject);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/saveBudgeting")
	@ResponseStatus( HttpStatus.OK )
	public void saveBudgeting(@RequestBody ResearchBudgeting anObject, BindingResult bindingResult) throws SystemException {
		if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid validation", bindingResult);
        }
		researchBudgetingService.saveOrUpdate(preUpdateBudgeting(anObject));
	}
	
	private ResearchGoalTarget validateGoalTarget(ResearchGoalTarget anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(ResearchGoalTarget.TITLE, messageSource.getMessage("error.mandatory", new String[]{"Title"}, Locale.ENGLISH)));
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	private ResearchGoalTarget preUpdateGoalTarget(ResearchGoalTarget anObject) throws SystemException{
		Research research = new Research();
		research.setPkResearch(anObject.getFkResearch());
		anObject.setResearch(research);
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		return validateGoalTarget(anObject);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/saveGoalTarget")
	@ResponseStatus( HttpStatus.OK )
	public void saveGoalTarget(@RequestBody ResearchGoalTarget anObject, BindingResult bindingResult) throws SystemException {
		if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid validation", bindingResult);
        }
		researchGoalTargetService.saveOrUpdate(preUpdateGoalTarget(anObject));
	}
	
	private ResearchMemo validateMemo(ResearchMemo anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(ResearchMemo.TITLE, messageSource.getMessage("error.mandatory", new String[]{"Title"}, Locale.ENGLISH)));
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	private ResearchMemo preUpdateMemo(ResearchMemo anObject) throws SystemException{
		Research research = new Research();
		research.setPkResearch(anObject.getFkResearch());
		anObject.setResearch(research);
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		return validateMemo(anObject);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/saveMemo")
	@ResponseStatus( HttpStatus.OK )
	public void saveMemo(@RequestBody ResearchMemo anObject, BindingResult bindingResult) throws SystemException {
		if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid validation", bindingResult);
        }
		researchMemoService.saveOrUpdate(preUpdateMemo(anObject));
	}
}
