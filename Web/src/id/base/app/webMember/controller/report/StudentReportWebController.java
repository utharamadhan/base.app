package id.base.app.webMember.controller.report;

import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.StringFunction;
import id.base.app.util.dao.Operator;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.contact.Contact;
import id.base.app.valueobject.party.Student;
import id.base.app.valueobject.party.VWStudentList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value="request")
@Controller
@RequestMapping("/report/student")
public class StudentReportWebController extends BaseController<Student> {

	private final String PATH_LIST = "/report/studentReportList";
	
	@Override
	protected RestCaller<Student> getRestCaller() {
		return new RestCaller<Student>(RestConstant.REST_SERVICE, RestServiceConstant.STUDENT_SERVICE);
	}

	@Override
	protected List<SearchFilter> convertForFilter(
			Map<String, String> paramWrapper) {
		return null;
	}

	@Override
	protected List<SearchFilter> convertForFilter(HttpServletRequest request,
			Map<String, String> paramWrapper, DataTableCriterias columns) {
		List<SearchFilter> filters = new ArrayList<>();
		if(StringFunction.isNotEmpty(columns.getCustomFilters().get("filterStatus"))){
			filters.add(new SearchFilter(VWStudentList.STUDENT_STATUS_LOOKUP_PK, Operator.EQUALS, columns.getCustomFilters().get("filterStatus"), Long.class));
		}
		if(StringFunction.isNotEmpty(columns.getCustomFilters().get("filterCurrentLearning"))) {
			filters.add(new SearchFilter(VWStudentList.CURRENT_LEARNING, Operator.LIKE, "%" + columns.getCustomFilters().get("filterCurrentLearning") + "%"));
		}
		if(StringFunction.isNotEmpty(columns.getCustomFilters().get("filterPassedLearning"))) {
			filters.add(new SearchFilter(VWStudentList.CURRENT_LEARNING, Operator.LIKE, "%" + columns.getCustomFilters().get("filterPassedLearning") + "%"));
		}
		if(StringFunction.isNotEmpty(columns.getCustomFilters().get("filterEnrollDateFrom"))) {
			filters.add(new SearchFilter(VWStudentList.ENROLL_DATE, Operator.EQUALS_OR_GREATER_THAN, columns.getCustomFilters().get("filterEnrollDateFrom")));
		}
		if(StringFunction.isNotEmpty(columns.getCustomFilters().get("filterEnrollDateTo"))) {
			filters.add(new SearchFilter(VWStudentList.ENROLL_DATE, Operator.EQUALS_OR_LESS_THAN, columns.getCustomFilters().get("filterEnrollDateTo")));
		}
		return filters;
	}

	@Override
	protected List<SearchOrder> getSearchOrder() {
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(VWStudentList.NAME, SearchOrder.Sort.ASC));
		return orders;
	}

	@RequestMapping(method=RequestMethod.GET, value="/downloadXls")
	@ResponseBody
	public Map<String, Object> downloadXls(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = null;
		
		try {
			response.setHeader("Content-Disposition", "attachment; filename=StudentReport.xlsx");
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
		//List<Contact> contacts = getRestCaller().findAll(filter, order);
		List<Contact> contacts = new ArrayList<>();
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
							headerCell.setCellValue("Birth Date");
							break;
						case 2:
							headerCell.setCellValue("Birth Place");
							break;
						case 3:
							headerCell.setCellValue("Gender");
							break;
						case 4:
							headerCell.setCellValue("No. Telp");
							break;
						case 5:
							headerCell.setCellValue("Email");
							break;
						case 6:
							headerCell.setCellValue("Address");
							break;
						case 7:
							headerCell.setCellValue("Status");
							break;
						case 8:
							headerCell.setCellValue("Latest Work Company");
							break;
						case 9:
							headerCell.setCellValue("Latest Work Position");
							break;
						case 10:
							headerCell.setCellValue("Latest Work City/Town");
							break;
						case 11:
							headerCell.setCellValue("Latest Work Description");
							break;
						case 12:
							headerCell.setCellValue("Latest Education School");
							break;
						case 13:
							headerCell.setCellValue("Latest Education Dates Attended");
							break;
						case 14:
							headerCell.setCellValue("Latest Education Degree");
							break;
						case 15:
							headerCell.setCellValue("Latest Education Field of Study");
							break;
						case 16:
							headerCell.setCellValue("Latest Education Grade");
							break;
						case 17:
							headerCell.setCellValue("Latest Education Activities & Societies");
							break;
						case 18:
							headerCell.setCellValue("Latest Education Description");
							break;
						case 19:
							headerCell.setCellValue("Latest Education Description");
							break;
						case 20:
							headerCell.setCellValue("Current Learning");
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