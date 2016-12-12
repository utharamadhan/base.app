package id.padiku.app.dao.report;

import id.padiku.app.AbstractHibernateDAO;
import id.padiku.app.exception.SystemException;
import id.padiku.app.util.DateTimeFunction;
import id.padiku.app.valueobject.business.report.ViewCashFlow;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

@Repository
public class ReportCashFlowDAO extends AbstractHibernateDAO<ViewCashFlow, Long> implements IReportCashFlowDAO {
	
	@Override
	public List<ViewCashFlow> getCashFlowFee(Long pkCompany) throws SystemException {
		Criteria crit = getSession().createCriteria(domainClass);
			crit.add(Restrictions.eq(ViewCashFlow.FK_COMPANY, pkCompany));
			crit.add(Restrictions.eq(ViewCashFlow.TRANS_DATE, DateTimeFunction.getCurrentDateWithoutTime()));
			crit.setProjection(Projections.projectionList().
					add(Projections.groupProperty(ViewCashFlow.SOURCE)).
					add(Projections.sum(ViewCashFlow.DEBIT)).
					add(Projections.sum(ViewCashFlow.KREDIT)));
			crit.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					ViewCashFlow vcf = new ViewCashFlow();
					try {
						BeanUtils.copyProperty(vcf, ViewCashFlow.SOURCE, tuple[0]);
						BeanUtils.copyProperty(vcf, ViewCashFlow.DEBIT, tuple[1]);
						BeanUtils.copyProperty(vcf, ViewCashFlow.KREDIT, tuple[2]);
					}catch(Exception e){
						LOGGER.error(e.getMessage(), e);
					}
					return vcf;
				}
				
				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
		return crit.list();
	}
}
