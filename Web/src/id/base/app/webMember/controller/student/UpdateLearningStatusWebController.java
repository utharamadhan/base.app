package id.base.app.webMember.controller.student;

import id.base.app.paging.PagingWrapper;
import id.base.app.rest.RestCaller;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.party.Student;
import id.base.app.webMember.DataTableCriterias;
import id.base.app.webMember.controller.BaseController;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Scope(value="request")
@Controller
@RequestMapping("/student/updateLearningStatus")
public class UpdateLearningStatusWebController extends BaseController<Student> {

	private final String PATH_LIST = "/student/updateLearningStatusList";
	private final String PATH_DETAIL = "/student/updateLearningStatusList";
	@Override
	protected RestCaller<Student> getRestCaller() {
		return null;
	}

	@Override
	protected List<SearchFilter> convertForFilter(
			Map<String, String> paramWrapper) {
		return null;
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request,
			Map<String, String> paramWrapper, DataTableCriterias columns) {
		return null;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		return null;
	}

	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<Student>());
		return getListPath();
	}

}
