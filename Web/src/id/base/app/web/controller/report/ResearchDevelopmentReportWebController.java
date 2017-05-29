package id.base.app.web.controller.report;

import id.base.app.paging.PagingWrapper;
import id.base.app.rest.RestCaller;
import id.base.app.rest.RestConstant;
import id.base.app.rest.RestServiceConstant;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.contact.Contact;
import id.base.app.valueobject.report.VWResearchDevelopmentReport;
import id.base.app.web.DataTableCriterias;
import id.base.app.web.controller.BaseController;

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
@RequestMapping("/report/researchDevelopment")
public class ResearchDevelopmentReportWebController extends BaseController<VWResearchDevelopmentReport> {
	
	private final String PATH_LIST = "/report/research/researchReportDataList";

	@Override
	protected RestCaller<VWResearchDevelopmentReport> getRestCaller() {
		return new RestCaller<VWResearchDevelopmentReport>(RestConstant.REST_SERVICE, RestServiceConstant.RESEARCH_DEVELOPMENT_REPORT_SERVICE);
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
	
	@RequestMapping(method=RequestMethod.GET, value="showList")
	public String showList(ModelMap model, HttpServletRequest request){
		model.addAttribute("pagingWrapper", new PagingWrapper<VWResearchDevelopmentReport>());
		return getListPath();
	}

	@RequestMapping(method=RequestMethod.GET, value="/downloadXls")
	@ResponseBody
	public Map<String, Object> downloadXls(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> resultMap = null;
		
		try {
			response.setHeader("Content-Disposition", "attachment; filename=ResearchReport.xlsx");
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
		List<VWResearchDevelopmentReport> rnds = getRestCaller().findAll(filter, order);
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row headerRow = null;
		Row valueRow = null;
		for(int j=0;j<=rnds.size();j++){
			if(j==0){
				headerRow = sheet.createRow(j);
			}else{
				valueRow = sheet.createRow(j);
			}
			for(int i=0; i<18;i++){
				if(j==0){
					Cell headerCell = headerRow.createCell(i);
					switch (i) {
						case 0:
							headerCell.setCellValue("Type");
							break;
						case 1:
							headerCell.setCellValue("Title");
							break;
						case 2:
							headerCell.setCellValue("Sub Title");
							break;
						case 3:
							headerCell.setCellValue("Date From");
							break;
						case 4:
							headerCell.setCellValue("Date To");
							break;
						case 5:
							headerCell.setCellValue("Description");
							break;
						case 6:
							headerCell.setCellValue("Theme");
							break;
						case 7:
							headerCell.setCellValue("Project Officer");
							break;
						case 8:
							headerCell.setCellValue("Principle Permit File");
							break;
						case 9:
							headerCell.setCellValue("Principle Permit Description");
							break;
						case 10:
							headerCell.setCellValue("Procurement File");
							break;
						case 11:
							headerCell.setCellValue("Procurement Description");
							break;
						case 12:
							headerCell.setCellValue("Work Order File");
							break;
						case 13:
							headerCell.setCellValue("Work Order Description");
							break;
						case 14:
							headerCell.setCellValue("Vendor");
							break;
						case 15:
							headerCell.setCellValue("Project Value");
							break;
						case 16:
							headerCell.setCellValue("Implementation Status");
							break;
						case 17:
							headerCell.setCellValue("Result");
							break;
						default:
							break;
					}
				}else{
					Cell valueCell = valueRow.createCell(i);
					switch (i) {
						case 0:
							valueCell.setCellValue(rnds.get(j-1).getType());
							break;
						case 1:
							valueCell.setCellValue(rnds.get(j-1).getTitle());
							break;
						case 2:
							valueCell.setCellValue(rnds.get(j-1).getSubtitle());
							break;
						case 3:
							valueCell.setCellValue(rnds.get(j-1).getDateFrom());
							break;
						case 4:
							valueCell.setCellValue(rnds.get(j-1).getDateTo());
							break;
						case 5:
							valueCell.setCellValue(rnds.get(j-1).getDescription());
							break;
						case 6:
							valueCell.setCellValue(rnds.get(j-1).getTheme());
							break;
						case 7:
							valueCell.setCellValue(rnds.get(j-1).getProjectOfficer());
							break;
						case 8:
							valueCell.setCellValue(rnds.get(j-1).getPrinciplePermitFile());
							break;
						case 9:
							valueCell.setCellValue(rnds.get(j-1).getPrinciplePermitDescription());
							break;
						case 10:
							valueCell.setCellValue(rnds.get(j-1).getProcurementFile());
							break;
						case 11:
							valueCell.setCellValue(rnds.get(j-1).getProcurementDescription());
							break;
						case 12:
							valueCell.setCellValue(rnds.get(j-1).getWorkOrderFile());
							break;
						case 13:
							valueCell.setCellValue(rnds.get(j-1).getWorkOrderDescription());
							break;
						case 14:
							valueCell.setCellValue(rnds.get(j-1).getVendor());
							break;
						case 15:
							valueCell.setCellValue(rnds.get(j-1).getProjectValue());
							break;
						case 16:
							valueCell.setCellValue(rnds.get(j-1).getImplementationStatus());
							break;
						case 17:
							valueCell.setCellValue(rnds.get(j-1).getResult());
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
