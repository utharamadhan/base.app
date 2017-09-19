package id.base.app.web.controller.advisory;

import id.base.app.ILookupConstant;
import id.base.app.ILookupGroupConstant;
import id.base.app.SystemConstant;
import id.base.app.exception.ErrorHolder;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.util.StringFunction;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.AppUser;
import id.base.app.valueobject.Lookup;
import id.base.app.valueobject.party.Party;
import id.base.app.valueobject.program.ProgramItem;
import id.base.app.valueobject.program.ProgramItemImage;
import id.base.app.valueobject.program.ProgramItemMenu;
import id.base.app.valueobject.program.ProgramItemTeacher;
import id.base.app.valueobject.program.ProgramItemTestimonial;
import id.base.app.valueobject.testimonial.Testimonial;
import id.base.app.valueobject.util.SelectHelper;
import id.base.app.web.DataTableCriterias;
import id.base.app.web.controller.BaseController;
import id.base.app.web.rest.LookupRestCaller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@Controller
@RequestMapping("/advisory/item")
public class AdvisoryItemWebController extends BaseController<ProgramItem> {

	private final String PATH_LIST = "/advisory/advisoryItemList";
	private final String PATH_DETAIL = "/advisory/advisoryItemDetail";
	
	@Override
	protected RestCaller<ProgramItem> getRestCaller() {
		return new RestCaller<ProgramItem>(RestConstant.REST_SERVICE, RestServiceConstant.PROGRAM_ITEM_SERVICE);
	}
	
	private RestCaller<Testimonial> getTestimonialRestCaller() {
		return new RestCaller<Testimonial>(RestConstant.REST_SERVICE, RestServiceConstant.TESTIMONIAL_SERVICE);
	}
	
	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		return null;
	}
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
		filters.add(new SearchFilter(ProgramItem.TYPE, Operator.EQUALS, SystemConstant.CategoryType.ADVISORY, String.class));
		filters.add(new SearchFilter(ProgramItem.STATUS, Operator.NOT_EQUAL, ILookupConstant.Status.DELETE, Integer.class));
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		if(StringFunction.isNotEmpty(columns.getSearch().get(DataTableCriterias.SearchCriterias.value))){
			filters.add(new SearchFilter(ProgramItem.TITLE, Operator.LIKE, columns.getSearch().get(DataTableCriterias.SearchCriterias.value)));
		}
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(ProgramItem.PK_PROGRAM_ITEM, SearchOrder.Sort.DESC));
		return orders;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<AppUser>());
		return getListPath();
	}
	
	public void setDefaultData(ModelMap model, ProgramItem obj) {
		LookupRestCaller lrc = new LookupRestCaller();
		model.addAttribute("methodOptions", lrc.findByLookupGroup(ILookupGroupConstant.PROGRAM_METHOD));
		model.addAttribute("organizerOptions", lrc.findByLookupGroup(ILookupGroupConstant.PROGRAM_ORGANIZER));
		model.addAttribute("paymentOptions", lrc.findByLookupGroup(ILookupGroupConstant.PROGRAM_PAYMENT));
		model.addAttribute("displayOptions", lrc.findByLookupGroup(ILookupGroupConstant.PROGRAM_DISPLAY));
		model.addAttribute("menuOptions", lrc.findByLookupGroup(ILookupGroupConstant.ITEM_MENU));
		model.addAttribute("statusOptions", lrc.findByLookupGroup(ILookupGroupConstant.STATUS));
		List<Lookup> booleanList = new ArrayList<>();
		booleanList.add(new Lookup().getInstanceShort("0", "No"));
		booleanList.add(new Lookup().getInstanceShort("1", "Yes"));
		model.addAttribute("booleanOptions", booleanList);
		List<SelectHelper> backImageSizeList = new ArrayList<>();
		backImageSizeList.add(new SelectHelper().getInstanceValueInteger(SystemConstant.BackgroundImageSize.SMALL, SystemConstant.BackgroundImageSize.SMALL_STR));
		backImageSizeList.add(new SelectHelper().getInstanceValueInteger(SystemConstant.BackgroundImageSize.BIG, SystemConstant.BackgroundImageSize.BIG_STR));
		model.addAttribute("backImageSizeOptions", backImageSizeList);
		model.addAttribute("learningAdvisoryUser", getAllLearningAdvisoryUser());
		List<SearchFilter> filters = new ArrayList<>();
		List<SearchOrder> orders = new ArrayList<>();
		filters.add(new SearchFilter(Testimonial.STATUS, Operator.EQUALS, ILookupConstant.Status.PUBLISH, Integer.class));
		orders.add(new SearchOrder(Testimonial.NAME, SearchOrder.Sort.ASC));
		model.addAttribute("testimonials", getTestimonialRestCaller().findAll(filters, orders));
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showAdd")
	public String showAdd(ModelMap model, HttpServletRequest request){
		ProgramItem pi = ProgramItem.getInstance();
		List<ProgramItemImage> piiList = new ArrayList<>();
		piiList.add(ProgramItemImage.getInstance());
		piiList.add(ProgramItemImage.getInstance());
		piiList.add(ProgramItemImage.getInstance());
		piiList.add(ProgramItemImage.getInstance());
		piiList.add(ProgramItemImage.getInstance());
		pi.setImages(piiList);
		model.addAttribute("detail", pi);
		setDefaultData(model, null);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showEdit")
	public String showEdit(@RequestParam(value="maintenancePK") final Long maintenancePK, @RequestParam Map<String, String> paramWrapper, ModelMap model, HttpServletRequest request){
		ProgramItem detail = getRestCaller().findById(maintenancePK);
		Collections.sort(detail.getMenus(), new ProgramItemMenu());
		Collections.sort(detail.getTeachers(), new ProgramItemTeacher());
		Collections.sort(detail.getTestimonials(), new ProgramItemTestimonial());
		if(detail.getImages()!=null && detail.getImages().isEmpty()){
			List<ProgramItemImage> piiList = new ArrayList<>();
			piiList.add(ProgramItemImage.getInstance());
			piiList.add(ProgramItemImage.getInstance());
			piiList.add(ProgramItemImage.getInstance());
			piiList.add(ProgramItemImage.getInstance());
			piiList.add(ProgramItemImage.getInstance());
			detail.setImages(piiList);
		}else{
			Collections.sort(detail.getImages(), new ProgramItemImage());	
		}
		setDefaultData(model, detail);
		model.addAttribute("detail", detail);
		return PATH_DETAIL;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="saveItem")
	@ResponseBody
	public Map<String, Object> saveItem(final ProgramItem anObject, HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		List<ErrorHolder> errors = new ArrayList<>();
		try{
			anObject.setType(SystemConstant.CategoryType.ADVISORY);
			errors = new SpecificRestCaller<ProgramItem>(RestConstant.REST_SERVICE, RestServiceConstant.PROGRAM_ITEM_SERVICE).performPut("/update", anObject);
			if(errors != null && errors.size() > 0){
				resultMap.put(SystemConstant.ERROR_LIST, errors);
			}
		}catch(Exception e){
			LOGGER.error(e.getMessage(), e);
		}
		return resultMap;
	}
	
	@Override
	protected String getListPath() {
		return PATH_LIST;
	}
	
	private List<Party> getAllLearningAdvisoryUser() throws SystemException {
		return new SpecificRestCaller<Party>(RestConstant.REST_SERVICE, RestConstant.RM_PARTY, Party.class).executeGetList(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/getAllLearningAdvisoryUser";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				return map;
			}
		});
	}
	
}