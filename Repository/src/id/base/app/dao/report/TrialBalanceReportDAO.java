package id.base.app.dao.report;

import id.base.app.AbstractHibernateDAO;
import id.base.app.exception.SystemException;
import id.base.app.paging.PagingUtil;
import id.base.app.paging.PagingWrapper;
import id.base.app.util.StringFunction;
import id.base.app.util.dao.SearchFilter;
import id.base.app.util.dao.SearchOrder;
import id.base.app.valueobject.business.report.ViewTrialBalanceReport;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("trialBalanceReportDAO")
public class TrialBalanceReportDAO extends AbstractHibernateDAO<ViewTrialBalanceReport, Long> {
	private static String QUERY_TRIAL_BALANCE_REPORT = "select C2.PERIOD, C2.PK_COA, C2.FK_PARENT, " +
                								"C2.LEVEL_NAME, C2.ACC_CODE, C2.ACC_DESCRIPTION, " +
                								"C2.MD, C2.MC,C2.YD,C2.YC, C2.ALTERNATE_REFERENCE, C2.lvl "+
												"from ( " +
													"SELECT rownum rn, level lvl, C.PK_COA, C.FK_PARENT " +
													"		, C.ACC_CODE, M.PERIOD, C.LEVEL_NAME " +
													"		, md, mc, YD, YC " +
													"		, C.ACC_DESCRIPTION " +
													"		, c.ALTERNATE_REFERENCE " +
													"	FROM COA C 	 " +
													"		LEFT JOIN ( " +
													"			SELECT M.PERIOD, NVL(m.DEBIT_AMOUNT,0) md, NVL(m.CREDIT_AMOUNT,0) mc " +
													"					, NVL(Y.DEBIT_AMOUNT,0) YD, NVL(Y.CREDIT_AMOUNT,0) YC , M.FK_COA " +
													"				FROM COA_BAL_MTD M  " +
													"					INNER JOIN COA_BAL_YTD Y ON M.FK_COA = Y.FK_COA " +
													"				WHERE M.PERIOD = ? AND Y.PERIOD=? " +
													"					) M ON M.FK_COA = C.PK_COA " +
													"	CONNECT BY PRIOR C.PK_COA = C.FK_PARENT  " +
													"	START WITH C.FK_PARENT IS NULL " +
													"	ORDER SIBLINGS BY C.ACC_DESCRIPTION DESC " +
													") c2 " +
													"order by c2.rn desc";
//	private static String QUERY_TRIAL_BALANCE_REPORT = "select C2.PERIOD, C2.PK_COA, C2.FK_PARENT, " +
//                								"C2.LEVEL_NAME, C2.ACC_CODE, C2.ACC_DESCRIPTION, " +
//                								"C2.MD, C2.MC,C2.YD,C2.YC, C2.ALTERNATE_REFERENCE, C2.lvl "+
//												"from ( "+
//												"SELECT rownum rn, level lvl, C.PK_COA, C.FK_PARENT " +
//												        ", C.ACC_CODE, M.PERIOD, C.LEVEL_NAME " +
//												        ", NVL(m.DEBIT_AMOUNT,0) md, NVL(m.CREDIT_AMOUNT,0) mc " +
//												        ", NVL(Y.DEBIT_AMOUNT,0) YD, NVL(Y.CREDIT_AMOUNT,0) YC " +
//												        ", C.ACC_DESCRIPTION " +
//												        ", c.ALTERNATE_REFERENCE " +
//												    "FROM COA C      " +
//												        "LEFT JOIN COA_BAL_MTD M ON M.FK_COA = C.PK_COA " +
//												        "LEFT JOIN COA_BAL_YTD Y ON M.FK_ACCOUNTING_YEAR = Y.FK_ACCOUNTING_YEAR AND M.FK_COA = Y.FK_COA " +
//												        "WHERE M.PERIOD = ? " +
//												    "CONNECT BY PRIOR C.PK_COA = C.FK_PARENT  " +
//												    "START WITH C.FK_PARENT IS NULL " +
//												    "ORDER SIBLINGS BY C.ACC_DESCRIPTION DESC " +
//												") c2 " +
//												"order by c2.rn desc ";
	
	private String getFilterPeriod(List<SearchFilter> filters) {
		String period = null;
		if (filters != null) {
			for (SearchFilter filter : filters) {
				if (filter.getFieldName().equals(ViewTrialBalanceReport.PERIOD)) {
					period = (String) filter.getValue();
				}
			}
		}
		// in case period filter is null, use current month
		if (period == null) {
			Calendar cal = Calendar.getInstance();
			int intPeriod = cal.get(Calendar.YEAR) * 100 + Integer.parseInt("" + StringUtils.leftPad("" + (cal.get(Calendar.MONTH) + 1), 2, '0'));
			period = Integer.toString(intPeriod);
		}
		return period;
	}
	
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		int intPeriod = cal.get(Calendar.YEAR) * 100 + Integer.parseInt("" + StringUtils.leftPad("" + (cal.get(Calendar.MONTH) + 1), 2, '0'));
		System.out.println(Integer.toString(intPeriod));
	}
	
	private List<ViewTrialBalanceReport> runQuery(String sqlQuery, List<SearchFilter> searchFilters,List<SearchOrder> searchOrders,String cacheRegion)
			throws SystemException{
		String period = getFilterPeriod(searchFilters);
		
		final int yearPeriod = Integer.parseInt(period.substring(0, 4));
		
		SQLQuery query = getSession().createSQLQuery(sqlQuery);
		/*
		 * Assume contract only has 1 life assured
		 */
		query.setResultTransformer(new ResultTransformer() {
			
			@Override
			public Object transformTuple(Object[] tuple, String[] aliases) {
				ViewTrialBalanceReport rep = new ViewTrialBalanceReport();
				rep.setAccountName(String.valueOf(tuple[4]));
				rep.setCreditMtd(Long.valueOf(StringFunction.isNotEmpty(String.valueOf(tuple[7])) && !String.valueOf(tuple[7]).equals("null") ? String.valueOf(tuple[7]) : "0"));
				rep.setCreditYtd(Long.valueOf(StringFunction.isNotEmpty(String.valueOf(tuple[9])) && !String.valueOf(tuple[9]).equals("null") ? String.valueOf(tuple[9]) : "0"));
				rep.setDebitMtd(Long.valueOf(StringFunction.isNotEmpty(String.valueOf(tuple[6])) && !String.valueOf(tuple[6]).equals("null") ? String.valueOf(tuple[6]) : "0"));
				rep.setDebitYtd(Long.valueOf(StringFunction.isNotEmpty(String.valueOf(tuple[8])) && !String.valueOf(tuple[8]).equals("null") ? String.valueOf(tuple[8]) : "0"));
				rep.setDescription(String.valueOf(tuple[5]));
				try{
					rep.setPeriod(Integer.valueOf(String.valueOf(tuple[0]).substring(4, 6)));
				}catch(Exception e){
					rep.setPeriod(0);
				}
				rep.setSunGlCode(String.valueOf(tuple[10]).equals("null") ? "" : String.valueOf(tuple[10]));
				rep.setYear(yearPeriod);
				rep.setLevel(Integer.valueOf(String.valueOf(tuple[11])));
				return rep;
			}
			
			@Override
			public List transformList(List collection) {
				return collection;
			}
		});
		
		query.setString(0, period);
		query.setString(1, period.substring(0,4));

		return query.list();
	}
	/**
	 * 
	 */
	public List<ViewTrialBalanceReport> findAll(List<SearchFilter> searchFilters,List<SearchOrder> searchOrders,String cacheRegion)
			throws SystemException{
		return runQuery(QUERY_TRIAL_BALANCE_REPORT, searchFilters, searchOrders, cacheRegion);

	}
	
	public PagingWrapper<ViewTrialBalanceReport> findAllWithPagingWrapper(int startIndex,
			int maxRow, List<SearchFilter> searchFilters,
			List<SearchOrder> searchOrders, String cacheRegion)
			throws SystemException {
		String queryCount = "select count(1) from ( " + QUERY_TRIAL_BALANCE_REPORT +
		      					")";
		String period = getFilterPeriod(searchFilters);
		
		SQLQuery sqlQuery = getSession().createSQLQuery(queryCount);
		sqlQuery.setString(0, period);
		sqlQuery.setString(1, period.substring(0,4));
		Long _totalRecords = ((BigDecimal)sqlQuery.uniqueResult()).longValue();
		
		if(_totalRecords==null){
			_totalRecords = 0L;
		}
		// normalize startIndex (in case startIndex > totalRecords/maxRow)
		if (startIndex > _totalRecords) {
			startIndex = PagingUtil.getLastPageStartRow(_totalRecords, maxRow);
		}
		
		String query = "select * from ( " +
			      	"select a.*, rownum rn from ( " + QUERY_TRIAL_BALANCE_REPORT +
			      	") a) where rn >= " +
			      	startIndex + " and rn < " + (startIndex + maxRow);
		
		List<ViewTrialBalanceReport> list = runQuery(query, searchFilters, searchOrders, cacheRegion);
		
		return new PagingWrapper<ViewTrialBalanceReport>(list, _totalRecords, startIndex, maxRow);
	}

}
