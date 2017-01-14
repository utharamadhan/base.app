package id.base.app.webMember.controller.site;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import id.base.app.paging.PagingWrapper;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.publication.DigitalBook;

@Scope(value="request")
@RequestMapping(value="/ebook")
@Controller
public class EbookWebController {
	
	static Logger LOGGER = LoggerFactory.getLogger(EbookWebController.class);
	
	protected ObjectMapper mapper = new ObjectMapper();
	
	protected RestCaller<DigitalBook> getRestCaller() {
		return new RestCaller<DigitalBook>(RestConstant.REST_SERVICE, RestServiceConstant.DIGITAL_BOOK_SERVICE);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String view(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="12") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
	){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		if(StringUtils.isNotEmpty(filterJson)){
			filter.add(new SearchFilter(DigitalBook.TITLE, Operator.LIKE, filterJson));
		}
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		PagingWrapper<DigitalBook> books = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		model.addAttribute("books", books);
		return "/ebook/main";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/load")
	@ResponseBody
	public Map<String, Object> load(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startNo",defaultValue="1") int startNo, 
			@RequestParam(value="offset",defaultValue="12") int offset,
			@RequestParam(value="filter", defaultValue="", required=false) String filterJson
	){
		Map<String, Object> resultMap = new HashMap<>();
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		if(StringUtils.isNotEmpty(filterJson)){
			filter.add(new SearchFilter(DigitalBook.TITLE, Operator.LIKE, filterJson));
		}
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		PagingWrapper<DigitalBook> books = getRestCaller().findAllByFilter(startNo, offset, filter, order);
		resultMap.put("books", books);
		return resultMap;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/detail/{id}")
	public String detail(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value="id") Long id
	){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		DigitalBook detail = DigitalBook.getInstance();
		detail = getRestCaller().findById(id);
		model.addAttribute("detail", detail);
		return "/ebook/detail";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/downloadFile/{id}")
	@ResponseBody
	public Map<String, Object> downloadTemplate(HttpServletRequest request, HttpServletResponse response, @PathVariable(value="id") Long id){
		Map<String, Object> resultMap = null;
		
		try {
			DigitalBook detail = DigitalBook.getInstance();
			detail = getRestCaller().findById(id);
			String fileUrl = detail.getFileURL();
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
	        }
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return resultMap;
	}
	
}
