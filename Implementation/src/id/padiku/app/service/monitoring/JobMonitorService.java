/*
 * Created on Apr 8, 2004
 */
package id.padiku.app.service.monitoring;


import id.padiku.app.dao.jobmonitor.IJobMonitorDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.service.MaintenanceService;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.monitoring.JobMonitor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JobMonitorService  implements MaintenanceService<JobMonitor>, IJobMonitorService {

	@Autowired
    protected  IJobMonitorDAO jobMonitorDAO;
    
    public JobMonitorService(){};
    
    public JobMonitorService(IJobMonitorDAO jobMonitorDAO){
    	this.jobMonitorDAO = jobMonitorDAO;
    }
    
	public void delete(Long[] objectPKs) throws SystemException {
		// TODO Auto-generated method stub
	}

	public void saveOrUpdate(JobMonitor anObject) throws SystemException {
	    jobMonitorDAO.saveOrUpdate(anObject);
	}

	public PagingWrapper<JobMonitor> findAllByFilter(int startNo, int offset,
			List<SearchFilter> filter, List<SearchOrder> order)
			throws SystemException {
		return jobMonitorDAO.findAllByFilter(startNo, offset, filter, order);
	}

	@Override
	public List<JobMonitor> findObjects(Long[] objectPKs)
			throws SystemException {
		List<JobMonitor> JobMonitors=new ArrayList<JobMonitor>();
		JobMonitor jobMonitor=null;
		for(Long l:objectPKs){
			jobMonitor=jobMonitorDAO.findById(l);
			JobMonitors.add(jobMonitor);
		}
		return JobMonitors;
	}

	@Override
	public List<JobMonitor> findAll(List<SearchFilter> filter,
			List<SearchOrder> order) throws SystemException {
		return jobMonitorDAO.findAll(filter, order);
	}

	@Override
	public JobMonitor findById(Long id) throws SystemException {
		return jobMonitorDAO.findById(id);
	}
	
}
