package id.padiku.app.dao.audittrail;

import id.padiku.app.exception.SystemException;
import id.padiku.app.paging.PagingWrapper;
import id.padiku.app.util.dao.SearchFilter;
import id.padiku.app.util.dao.SearchOrder;
import id.padiku.app.valueobject.LogAuditTrail;

import java.util.List;

public interface IAuditTrailDAO {

	public abstract PagingWrapper<LogAuditTrail> findAuditTrailByCode(int startNo, int offset,
			List<SearchFilter> searchFilter, List<SearchOrder> searchOrder)
	throws SystemException;
	
	public List<LogAuditTrail> findAuditTrailByCode(Integer code)throws SystemException;
	
	public abstract void create(LogAuditTrail audit) throws SystemException;
	
	public List<LogAuditTrail> findAuditTrailByCodeSortDateDesc(Integer code)throws SystemException;
	
}
