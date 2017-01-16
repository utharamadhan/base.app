package id.base.app.service.aboutUs;

import java.util.List;

import id.base.app.exception.SystemException;
import id.base.app.service.MaintenanceService;
import id.base.app.valueobject.aboutUs.ProgramPost;

public interface IProgramPostService extends MaintenanceService<ProgramPost> {

	public List<ProgramPost> findLatest(int number) throws SystemException; 
}
