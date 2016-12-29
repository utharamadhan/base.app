package id.base.app.controller.research;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.research.IResearchService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.research.Research;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_RESEARCH)
public class ResearchController extends SuperController<Research>{
	
	@Autowired
	private IResearchService researchService;
	

	@Override
	public Research validate(Research anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getCode())) {
			errorList.add(new ErrorHolder(Research.CODE, messageSource.getMessage("error.mandatory", new String[]{"code"}, Locale.ENGLISH)));
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
	
}
