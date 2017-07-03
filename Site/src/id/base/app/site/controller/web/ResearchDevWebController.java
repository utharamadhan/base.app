package id.base.app.site.controller.web;

import id.base.app.paging.PagingWrapper;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.site.controller.BaseSiteController;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.Category;
import id.base.app.valueobject.Pages;
import id.base.app.valueobject.research.Research;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	@RequestMapping(method=RequestMethod.GET, value="/research")
	public String research(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		setMenu(model);
		return "/research-development/research";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/development")
	public String development(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		setMenu(model);
		return "/research-development/development";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/research/list")
	public String listResearch(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		setMenu(model);
		return "/research-development/research/list";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/research/detail")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response){
		setMenu(model);
		return "/research-development/research/detail";
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
	
}
