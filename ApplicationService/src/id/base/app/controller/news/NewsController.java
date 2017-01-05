package id.base.app.controller.news;

import id.base.app.SystemConstant;
import id.base.app.controller.SuperController;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.news.INewsService;
import id.base.app.util.StringFunction;
import id.base.app.valueobject.news.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstant.RM_NEWS)
public class NewsController extends SuperController<News>{
	
	@Autowired
	private INewsService newsService;
	

	@Override
	public News validate(News anObject) throws SystemException {
		List<ErrorHolder> errorList = new ArrayList<>();
		if(StringFunction.isEmpty(anObject.getContent())) {
			errorList.add(new ErrorHolder(News.CONTENT, messageSource.getMessage("error.mandatory", new String[]{"content"}, Locale.ENGLISH)));
		}
		if(errorList.size() > 0) {
			throw new SystemException(errorList);
		}
		return anObject;
	}
	
	@Override
	public MaintenanceService<News> getMaintenanceService() {
		return newsService;
	}
	
	@Override
	public News preUpdate(News anObject) throws SystemException{
		anObject.setStatus(SystemConstant.ValidFlag.VALID);
		return validate(anObject);
	}
	
}
