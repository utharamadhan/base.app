package id.base.app.controller.report;

import id.base.app.controller.SuperController;
import id.base.app.exception.SystemException;
import id.base.app.rest.RestConstant;
import id.base.app.service.MaintenanceService;
import id.base.app.service.report.IVWStudentReportService;
import id.base.app.valueobject.report.VWStudentReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value=RestConstant.RM_STUDENT_REPORT)
public class StudentReportController extends SuperController<VWStudentReport> {

	@Autowired
	private IVWStudentReportService studentReportService;
	
	@Override
	public MaintenanceService<VWStudentReport> getMaintenanceService() {
		return studentReportService;
	}

	@Override
	public VWStudentReport validate(VWStudentReport anObject)
			throws SystemException {
		return null;
	}

}
