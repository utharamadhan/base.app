package id.base.app.controller.report;

import id.base.app.controller.SuperController;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.report.IVWResearchDevelopmentReportService;
import id.base.app.valueobject.report.VWResearchDevelopmentReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value=RestConstant.RM_RESEARCH_DEVELOPMENT_REPORT)
public class ResearchDevelopmentReportController extends SuperController<VWResearchDevelopmentReport> {

	@Autowired
	private IVWResearchDevelopmentReportService ResearchDevelopmentReportService;
	
	@Override
	public MaintenanceService<VWResearchDevelopmentReport> getMaintenanceService() {
		return ResearchDevelopmentReportService;
	}

	@Override
	public VWResearchDevelopmentReport validate(VWResearchDevelopmentReport anObject)
			throws SystemException {
		return null;
	}

}
