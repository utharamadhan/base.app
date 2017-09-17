package id.base.app.controller;

import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.faq.IFaqService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.Faq;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_FAQ)
public class FaqController extends SuperController<Faq>{
	
	@Autowired
	private IFaqService faqService;
	
	@Override
	public MaintenanceService<Faq> getMaintenanceService() {
		return faqService;
	}

	@Override
	public Faq validate(Faq anObject) throws SystemException {
		List<ErrorHolder> errors = new ArrayList<>();
		if(anObject.getFaqCategory()==null || anObject.getFaqCategory().getPkCategory()==null) {
			errors.add(new ErrorHolder(Faq.FAQ_CATEGORY_PK, messageSource.getMessage("error.mandatory", new String[]{"Category"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getQuestion())) {
			errors.add(new ErrorHolder(Faq.QUESTION, messageSource.getMessage("error.mandatory", new String[]{"Question"}, Locale.ENGLISH)));
		}
		if(StringFunction.isEmpty(anObject.getAnswer())) {
			errors.add(new ErrorHolder(Faq.ANSWER, messageSource.getMessage("error.mandatory", new String[]{"Answer"}, Locale.ENGLISH)));
		}
		if(errors != null && errors.size() > 0){
			throw new SystemException(errors);
		}
		return anObject;
	}
	
	@Override
	public Faq preUpdate(Faq anObject) throws SystemException{
		return validate(anObject);
	}
	
}
