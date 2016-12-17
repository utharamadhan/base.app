package id.base.app.service.publication;

import id.base.app.dao.publication.IResearchReportDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.publication.ResearchReport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResearchReportService implements IResearchReportService {

	@Autowired
	private IResearchReportDAO researchReportDAO;
    
	public PagingWrapper<ResearchReport> findAll(int startNo, int offset) throws SystemException {
		return null;
	}

	public ResearchReport findById(Long id) throws SystemException {
		return researchReportDAO.findById(id);
	}

	public void saveOrUpdate(ResearchReport anObject) throws SystemException {
		researchReportDAO.saveOrUpdate(anObject);
	}

	public void delete(Long[] objectPKs) throws SystemException {
		researchReportDAO.delete(objectPKs);
	}

	public PagingWrapper<ResearchReport> findAllByFilter(int startNo, int offset, List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return researchReportDAO.findAllByFilter(startNo, offset, filter, order);
	}
	
	@Override
	public List<ResearchReport> findObjects(Long[] objectPKs) throws SystemException {
		List<ResearchReport> objects = new ArrayList<>();
		ResearchReport object = null;
		for(Long l:objectPKs){
			object = researchReportDAO.findById(l);
			objects.add(object);
		}
		return objects;
	}

	@Override
	public List<ResearchReport> findAll(List<SearchFilter> filter, List<SearchOrder> order) throws SystemException {
		return researchReportDAO.findAll(filter, order);
	}

}
