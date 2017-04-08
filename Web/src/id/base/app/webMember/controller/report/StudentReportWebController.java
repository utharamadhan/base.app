package id.base.app.webMember.controller.report;

import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.report.VWStudentReport;
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
@RequestMapping("/report/VWStudentReport")
public class StudentReportWebController extends BaseController<VWStudentReport> {

	private final String PATH_LIST = "/report/VWStudentReportReportList";
	
	@Override
	protected RestCaller<VWStudentReport> getRestCaller() {
		return new RestCaller<VWStudentReport>(RestConstant.REST_SERVICE, RestServiceConstant.STUDENT_REPORT_SERVICE);
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
		if(orders != null) {
			orders.clear();
		}
		orders.add(new SearchOrder(VWStudentReport.NAME, SearchOrder.Sort.ASC));
		return orders;
	}

	@RequestMapping(method=RequestMethod.GET, value="/downloadXls")
	@ResponseBody
	public Map<String, Object> downloadXls(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = null;
		
		try {
			response.setHeader("Content-Disposition", "attachment; filename=VWStudentReportReport.xlsx");
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
		List<VWStudentReport> students = getRestCaller().findAll(filter, order);
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row headerRow = null;
		Row valueRow = null;
		for(int j=0;j<=students.size();j++){
			if(j==0){
				headerRow = sheet.createRow(j);
			}else{
				valueRow = sheet.createRow(j);
			}
			for(int i=0; i<21;i++){
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
							headerCell.setCellValue("Telp No.");
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
							headerCell.setCellValue("Latest Learning");
							break;
						case 20:
							headerCell.setCellValue("Latest Learning Status");
							break;
						default:
							break;
					}
				}else{
					Cell valueCell = valueRow.createCell(i);
					switch (i) {
						case 0:
							valueCell.setCellValue(students.get(j-1).getName());
							break;
						case 1:
							valueCell.setCellValue(students.get(j-1).getBirthDate());
							break;
						case 2:
							valueCell.setCellValue(students.get(j-1).getBirthPlace());
							break;
						case 3:
							valueCell.setCellValue(students.get(j-1).getGender());
							break;
						case 4:
							valueCell.setCellValue(students.get(j-1).getTelpNo());
							break;
						case 5:
							valueCell.setCellValue(students.get(j-1).getEmail());
							break;
						case 6:
							valueCell.setCellValue(students.get(j-1).getAddress());
							break;
						case 7:
							valueCell.setCellValue(students.get(j-1).getStatus());
							break;
						case 8:
							valueCell.setCellValue(students.get(j-1).getLatestWorkCompany());
							break;
						case 9:
							valueCell.setCellValue(students.get(j-1).getLatestWorkPosition());
							break;
						case 10:
							valueCell.setCellValue(students.get(j-1).getLatestWorkCity());
							break;
						case 11:
							valueCell.setCellValue(students.get(j-1).getLatestWorkDescription());
							break;
						case 12:
							valueCell.setCellValue(students.get(j-1).getLatestEducationSchool());
							break;
						case 13:
							valueCell.setCellValue(students.get(j-1).getLatestEducationDatesAttended());
							break;
						case 14:
							valueCell.setCellValue(students.get(j-1).getLatestEducationDegree());
							break;
						case 15:
							valueCell.setCellValue(students.get(j-1).getLatestEducationFieldOfStudy());
							break;
						case 16:
							valueCell.setCellValue(students.get(j-1).getLatestEducationGrade());
							break;
						case 17:
							valueCell.setCellValue(students.get(j-1).getLatestEducationActivities());
							break;
						case 18:
							valueCell.setCellValue(students.get(j-1).getLatestEducationDescription());
							break;
						case 19:
							valueCell.setCellValue(students.get(j-1).getLatestLearning());
							break;
						case 20:
							valueCell.setCellValue(students.get(j-1).getLatestLearningStatus());
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