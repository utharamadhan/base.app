package id.base.app.webMember.contact;

import id.base.app.paging.PagingWrapper;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.contact.Contact;
import id.base.app.webMember.DataTableCriterias;
import id.base.app.webMember.controller.BaseController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@Controller
@RequestMapping("/contactUs/userContact")
public class ContactUserContactUsWebController extends BaseController<Contact> {

	private final String PATH_LIST = "/contact/contactUsUserContactList";
	
	@Override
	protected RestCaller<Contact> getRestCaller() {
		return new RestCaller<Contact>(RestConstant.REST_SERVICE, RestServiceConstant.CONTACT_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(Map<String, String> paramWrapper) {
		return null;
	}
	
	private void setDefaultFilter(HttpServletRequest request, List<SearchFilter> filters) {
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request, Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		setDefaultFilter(request, filters);
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		return orders;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<Contact>());
		return getListPath();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/downloadXls")
	@ResponseBody
	public Map<String, Object> downloadXls(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = null;
		
		try {
			response.setHeader("Content-Disposition", "attachment; filename=contact.xlsx");
			XSSFWorkbook workbook = generateFile();
			workbook.write(response.getOutputStream());
			response.getOutputStream().close();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return resultMap;
	}
	
	private XSSFWorkbook generateFile(){
		List<SearchFilter> filter = new ArrayList<SearchFilter>();
		List<SearchOrder> order = new ArrayList<SearchOrder>();
		List<Contact> contacts = getRestCaller().findAll(filter, order);
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row headerRow = null;
		Row valueRow = null;
		for(int j=0;j<=contacts.size();j++){
			if(j==0){
				headerRow = sheet.createRow(j);
			}else{
				valueRow = sheet.createRow(j);
			}
			for(int i=0; i<9;i++){
				if(j==0){
					Cell headerCell = headerRow.createCell(i);
					switch (i) {
						case 0:
							headerCell.setCellValue("Name");
							break;
						case 1:
							headerCell.setCellValue("Email");
							break;
						case 2:
							headerCell.setCellValue("Phone");
							break;
						case 3:
							headerCell.setCellValue("Address");
							break;
						case 4:
							headerCell.setCellValue("Instansi");
							break;
						case 5:
							headerCell.setCellValue("Type");
							break;
						case 6:
							headerCell.setCellValue("Learning");
							break;
						case 7:
							headerCell.setCellValue("Tema");
							break;
						case 8:
							headerCell.setCellValue("Message");
							break;
						default:
							break;
					}
				}else{
					Cell valueCell = valueRow.createCell(i);
					switch (i) {
					case 0:
						valueCell.setCellValue(contacts.get(j-1).getName());
						break;
					case 1:
						valueCell.setCellValue(contacts.get(j-1).getEmail());
						break;
					case 2:
						valueCell.setCellValue(contacts.get(j-1).getTelp());
						break;
					case 3:
						valueCell.setCellValue(contacts.get(j-1).getAddress());
						break;
					case 4:
						valueCell.setCellValue(contacts.get(j-1).getInstansi());
						break;
					case 5:
						valueCell.setCellValue(contacts.get(j-1).getHelpLookup().getDescr());
						break;
					case 6:
						valueCell.setCellValue(contacts.get(j-1).getCourse() !=null ? contacts.get(j-1).getCourse().getName() : "");
						break;
					case 7:
						valueCell.setCellValue(contacts.get(j-1).getTemaLookup() !=null ? contacts.get(j-1).getTemaLookup().getName() : "");
						break;
					case 8:
						valueCell.setCellValue(contacts.get(j-1).getMessage());
						break;
					default:
						break;
				}
				}
				
				sheet.autoSizeColumn(i);
			}
		}
		
		return workbook;
	}
	
	@Override
	protected String getListPath() {
		return PATH_LIST;
	}

}
