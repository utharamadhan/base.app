package id.base.app.site.controller.web;

import id.base.app.ILookupConstant;
import id.base.app.SystemConstant;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.rest.PathInterfaceRestCaller;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.rest.SpecificRestCaller;
import id.base.app.site.controller.BaseSiteController;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.util.dao.SearchOrder.Sort;
import id.base.app.valueobject.Category;
import id.base.app.valueobject.Pages;
import id.base.app.valueobject.research.Research;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@RequestMapping(value="main-program/research-development")
@Controller
public class ResearchDevWebController extends BaseSiteController<Pages>{
	
	static Logger LOGGER = LoggerFactory.getLogger(ResearchDevWebController.class);
	
	protected RestCaller<Research> getRestCaller() {
		return new RestCaller<Research>(RestConstant.REST_SERVICE, RestServiceConstant.RESEARCH_SERVICE);
	}
	
	protected RestCaller<Category> getRestCallerCategories() {
		return new RestCaller<Category>(RestConstant.REST_SERVICE, RestServiceConstant.CATEGORY_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		return "redirect:/page/main-program/research-development/"+getFirstPermalinkData();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{permalink}")
	public String main(ModelMap model, HttpServletRequest request, HttpServletResponse response, @PathVariable(value="permalink") String permalink,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="6") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson){
		setCommonData(request,model);
		model.addAttribute("permalink", permalink);
		List<Category> categoryList = getCategoryList();
		model.addAttribute("categories", categoryList);
		for (Category category : categoryList) {
			if(permalink.equalsIgnoreCase(category.getPermalink())){
				model.addAttribute("category", category);
				break;
			}
		}
		return "/research-development/main";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/research/list")
	public String listResearch(ModelMap model, HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="12") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson){
		setCommonData(request,model);
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		filter.add(new SearchFilter(Research.STATUS, Operator.EQUALS, ILookupConstant.Status.PUBLISH, Integer.class));
		if(StringUtils.isNotEmpty(filterJson)){
			filter.add(new SearchFilter(Research.TITLE, Operator.LIKE, filterJson));
		}
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		order.add(new SearchOrder(Research.YEAR_FROM, Sort.DESC));
		PagingWrapper<Research> researches = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		model.addAttribute("researches", researches);
		return "/research-development/research/list";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/research/detail/{permalink}")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="permalink") String permalink){
		Research detail = findByPermalink(permalink);
		if(detail!=null){
			setCommonData(request,model);
			model.addAttribute("detail", detail);
			return "/research-development/research/detail";
		}
		LOGGER.error("ERROR DATA NOT FOUND");
		return "redirect:/page/notfound";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/research/list/load")
	@ResponseBody
	public Map<String, Object> load(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="10") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
		){
		Map<String, Object> resultMap = new HashMap<>();
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		PagingWrapper<Research> researches = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		resultMap.put("researches", researches);
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/research/downloadFile/{id}")
	@ResponseBody
	public Map<String, Object> downloadTemplate(HttpServletRequest request, HttpServletResponse response, @PathVariable(value="id") Long id){
		Map<String, Object> resultMap = null;
		
		try {
			Research detail = Research.getInstance();
			detail = getRestCaller().findById(id);
			/*String fileUrl = detail.getFileURL();
			String newFileUrl = fileUrl.replace("\\", "/");
			String[] fileNames = newFileUrl.split("/");
			String fileName = fileNames[fileNames.length-1];
			response.setHeader("Content-Disposition", "attachment; filename="+fileName);
			URL url = new URL(newFileUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
	        int responseCode = httpConn.getResponseCode();
	        
	        // always check HTTP response code first
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	        	// opens input stream from the HTTP connection
	            InputStream inputStream = httpConn.getInputStream();
	            OutputStream outputStream = response.getOutputStream();
	            
	            int bytesRead = -1;
	            byte[] buffer = new byte[1024 * 4];
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }
	 
	            outputStream.close();
	            inputStream.close();
	        }*/
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return resultMap;
	}
	
	private Research findByPermalink(final String permalink){
		Research detail = new Research();
		try{
			detail = new SpecificRestCaller<Research>(RestConstant.REST_SERVICE, RestConstant.RM_RESEARCH, Research.class).executeGet(new PathInterfaceRestCaller() {	
				@Override
				public String getPath() {
					return "/findByPermalink/{permalink}";
				}
				
				@Override
				public Map<String, Object> getParameters() {
					Map<String,Object> map = new HashMap<String, Object>();
					map.put("permalink", permalink);
					return map;
				}
			});
			
		}catch(Exception e){
			detail = null;
		}
		return detail;
	}
	
	private String getFirstPermalinkData() throws SystemException {
		return new SpecificRestCaller<String>(RestConstant.REST_SERVICE, RestConstant.RM_CATEGORY, String.class).executeGet(new PathInterfaceRestCaller() {
			@Override
			public String getPath() {
				return "/getFirstPermalinkData/{type}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("type", SystemConstant.CategoryType.RESEARCH_DEVELOPMENT);
				return map;
			}
		});
	}
	
	private List<Category> getCategoryList(){
		SpecificRestCaller<Category> rc = new SpecificRestCaller<Category>(RestConstant.REST_SERVICE, RestServiceConstant.CATEGORY_SERVICE);
		List<Category> list = rc.executeGetList(new PathInterfaceRestCaller() {
			
			@Override
			public String getPath() {
				return "/findSimpleDataForList/{type}";
			}
			
			@Override
			public Map<String, Object> getParameters() {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("type", SystemConstant.CategoryType.RESEARCH_DEVELOPMENT);
				return map;
			}
		});
		return list;
	}
	
}
