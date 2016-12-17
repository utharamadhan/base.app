package id.base.app.controller.publication;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.publication.IResearchReportService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.publication.ResearchReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_RESEARCH_REPORT)
public class ResearchReportController extends SuperController<ResearchReport>{
	
	@Autowired
	private IResearchReportService researchReportService;
	

	@Override
	public ResearchReport validate(ResearchReport anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getCode())) {
			errorList.add(new ErrorHolder(ResearchReport.CODE, messageSource.getMessage("error.mandatory", new String[]{"code"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getDescription())) {
			errorList.add(new ErrorHolder(ResearchReport.DESCRIPTION, messageSource.getMessage("error.mandatory", new String[]{"description"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getTitle())) {
			errorList.add(new ErrorHolder(ResearchReport.TITLE, messageSource.getMessage("error.mandatory", new String[]{"title"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getLink())) {
			errorList.add(new ErrorHolder(ResearchReport.LINK, messageSource.getMessage("error.mandatory", new String[]{"link"}, Locale.ENGLISH)));
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<ResearchReport> getMaintenanceService() {
		return researchReportService;
	}
	
	@Override
	public ResearchReport preUpdate(ResearchReport anObject) throws SystemException{
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		return validate(anObject);
	}
	
}
