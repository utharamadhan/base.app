package id.padiku.app.webMember;

import id.padiku.app.LoginSession;
import id.padiku.app.SystemParameter;
import id.padiku.app.config.BeanUtilsConfigurer;
import id.padiku.app.rest.RestCaller;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.rest.RestServiceConstant;
import id.padiku.app.valueobject.AppFunction;
import id.padiku.app.valueobject.AppParameter;
import id.padiku.app.valueobject.AppRole;
import id.padiku.app.valueobject.AppUser;
import id.padiku.app.valueobject.LogAuditTrail;
import id.padiku.app.valueobject.Lookup;
import id.padiku.app.valueobject.LookupAddress;
import id.padiku.app.valueobject.LookupAddressGroup;
import id.padiku.app.valueobject.LookupGroup;
import id.padiku.app.valueobject.MasterAddress;
import id.padiku.app.valueobject.RuntimeUserLogin;
import id.padiku.app.valueobject.business.report.ViewCashFlow;
import id.padiku.app.valueobject.business.report.ViewCostExpensesReport;
import id.padiku.app.valueobject.business.report.ViewStock;
import id.padiku.app.valueobject.business.report.ViewTransInReport;
import id.padiku.app.valueobject.forecast.ForecastCallHourly;
import id.padiku.app.valueobject.inventory.DispatchOrderNote;
import id.padiku.app.valueobject.inventory.GoodsReceiptNote;
import id.padiku.app.valueobject.master.Company;
import id.padiku.app.valueobject.master.CompanyLookup;
import id.padiku.app.valueobject.master.CompanyMachinery;
import id.padiku.app.valueobject.master.CompanyMasterFee;
import id.padiku.app.valueobject.master.CompanyProduct;
import id.padiku.app.valueobject.master.CompanyWarehouse;
import id.padiku.app.valueobject.master.Stock;
import id.padiku.app.valueobject.master.VWCompanyThirdParty;
import id.padiku.app.valueobject.party.Party;
import id.padiku.app.valueobject.procurement.PurchaseOrder;
import id.padiku.app.valueobject.procurement.TransIn;
import id.padiku.app.valueobject.procurement.TransInItem;
import id.padiku.app.valueobject.production.TransProd;
import id.padiku.app.valueobject.sales.TransOut;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jasypt.digest.config.SimpleDigesterConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class ParameterLoader extends ContextLoader implements ServletContextListener  {
	
	private Logger logger=LoggerFactory.getLogger(ParameterLoader.class);

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
	
	public ParameterLoader() {
		super();
	}
	
	public ParameterLoader(WebApplicationContext context) {
		super(context);
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext=servletContextEvent.getServletContext();

		WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);
		
		/*initializeAccessibility(ctx);
		
		initializeDigester(ctx);
		
		initializeLdap(ctx);*/
		
		initializeBeanUtils();
		
		/*initializeMailSender(ctx);*/
		
		initializeRest();
	}

	private void initializeMailSender(WebApplicationContext ctx) {
		logger.info("Start initializing mail sender... ");
		JavaMailSenderImpl mailSender = (JavaMailSenderImpl) ctx.getBean("mailSender");
		mailSender.setHost(SystemParameter.MAIL_HOST);
		mailSender.setPort(SystemParameter.MAIL_PORT);
		mailSender.setUsername(SystemParameter.MAIL_USERNAME);
		mailSender.setPassword(SystemParameter.MAIL_PASSWORD);
		logger.info("Finish initializing mail sender... ");
	}

	private void initializeBeanUtils() {
		logger.info("Start initializing beanutils... ");
		BeanUtilsConfigurer.configure();
		logger.info("Finish initializing beanutils... ");
	}

	private void initializeLdap(WebApplicationContext ctx) {
		/*logger.info("Start initializing ldap context... ");
		LdapTemplate ldapTemplate = (LdapTemplate) ctx.getBean(RestServiceConstant.LDAP_TEMPLATE);
		ContextSource contextSource = ldapTemplate.getContextSource();
		LdapContextSource ldapContext = (LdapContextSource) ctx.getBean(RestServiceConstant.LDAP_CONTEXT);
		ldapContext.setUrl(SystemParameter.LDAP_URL);
		ldapContext.setUserDn(SystemParameter.LDAP_USER_DN);
		ldapContext.setBase(SystemParameter.LDAP_BASE);
		
		StringEncryptor encryptor = (StringEncryptor) ctx.getBean(RestServiceConstant.ENCRYPTOR);
		ldapContext.setPassword(encryptor.decrypt(SystemParameter.LDAP_PASSWORD));
		
		logger.info("Finish initializing ldap context... ");*/
	}

	private void initializeDigester(WebApplicationContext ctx) {
		logger.info("Start initializing digester... ");
//		StandardStringDigester digester = (StandardStringDigester) ctx.getBean(RestServiceConstant.DIGESTER);
		SimpleDigesterConfig config = (SimpleDigesterConfig) ctx.getBean("digesterConfig");
		config.setAlgorithm(SystemParameter.DIGESTER_ALGORITHM);
//		digester.setConfig(config);
		//digester.initialize();
		logger.info("Finish initializing digester... ");
	}

	private void initializeAccessibility(WebApplicationContext ctx) {
		logger.info("Start loading Accesibility Constants .. ");
		//TODO find a way to initializeAccessibility
		/*IAppFunctionService appFunctionService = (IAppFunctionService) ctx
				.getBean(RestServiceConstant.APP_FUNCTION_SERVICE);

		List<AppFunction> appFunctions = appFunctionService.findAllAppFunction();
		
		for (AppFunction appFunction : appFunctions) {
			if(appFunction!=null){
			logger.debug("Setting appfunction [{}] with functionNumber [{}] ",
						appFunction.getDescr(), appFunction.getPkAppFunction());
				IAccessibilityConstant.synchronizeFunction(appFunction.getDescr(),
						appFunction.getPkAppFunction());
			}
		}*/
		
		logger.info("Finish loading Accesibility Constants .. ");
	}

	private void initializeRest() {
		RestCaller.BASE_URL.put(RestServiceConstant.APP_FUNCTION_SERVICE, RestConstant.RM_APP_FUNCTION);
		RestCaller.BASE_CLASS.put(RestServiceConstant.APP_FUNCTION_SERVICE, AppFunction.class);
		RestCaller.BASE_URL.put(RestServiceConstant.SYSTEM_PARAMETER_SERVICE, RestConstant.RM_SYSTEM_PARAMETER);
		RestCaller.BASE_CLASS.put(RestServiceConstant.SYSTEM_PARAMETER_SERVICE, AppParameter.class);
		RestCaller.BASE_URL.put(RestServiceConstant.SECURITY_PARAMETER_SERVICE, RestConstant.RM_SECURITYPARAM);
		RestCaller.BASE_CLASS.put(RestServiceConstant.SECURITY_PARAMETER_SERVICE, AppParameter.class);
		RestCaller.BASE_URL.put(RestServiceConstant.USER_SERVICE, RestConstant.RM_USER);
		RestCaller.BASE_CLASS.put(RestServiceConstant.USER_SERVICE, AppUser.class);
		RestCaller.BASE_URL.put(RestServiceConstant.AUTHENTICATION, RestConstant.RM_AUTHENTICATION);
		RestCaller.BASE_CLASS.put(RestServiceConstant.AUTHENTICATION, LoginSession.class);
		RestCaller.BASE_URL.put(RestServiceConstant.ROLE_SERVICE, RestConstant.RM_ROLE);
		RestCaller.BASE_CLASS.put(RestServiceConstant.ROLE_SERVICE, AppRole.class);
		RestCaller.BASE_URL.put(RestServiceConstant.ROLE_SERVICE, RestConstant.RM_ROLE);
		RestCaller.BASE_CLASS.put(RestServiceConstant.ROLE_SERVICE, AppRole.class);
		RestCaller.BASE_URL.put(RestServiceConstant.AUDIT_TRAIL_SERVICE, RestConstant.RM_AUDIT);
		RestCaller.BASE_CLASS.put(RestServiceConstant.AUDIT_TRAIL_SERVICE, LogAuditTrail.class);
		RestCaller.BASE_URL.put(RestServiceConstant.LOGIN_SERVICE, RestConstant.RM_USER_LOGIN);
		RestCaller.BASE_CLASS.put(RestServiceConstant.LOGIN_SERVICE, RuntimeUserLogin.class);
		
		RestCaller.BASE_URL.put(RestServiceConstant.LOOKUP_SERVICE, RestConstant.RM_LOOKUP);
		RestCaller.BASE_CLASS.put(RestServiceConstant.LOOKUP_SERVICE, Lookup.class);
		RestCaller.BASE_URL.put(RestServiceConstant.LOOKUP_GROUP_SERVICE, RestConstant.RM_LOOKUP_GROUP);
		RestCaller.BASE_CLASS.put(RestServiceConstant.LOOKUP_GROUP_SERVICE, LookupGroup.class);
		RestCaller.BASE_URL.put(RestServiceConstant.LOOKUP_ADDRESS_SERVICE, RestConstant.RM_LOOKUP_ADDRESS);
		RestCaller.BASE_CLASS.put(RestServiceConstant.LOOKUP_ADDRESS_SERVICE, LookupAddress.class);
		RestCaller.BASE_URL.put(RestServiceConstant.LOOKUP_ADDRESS_GROUP_SERVICE, RestConstant.RM_LOOKUP_ADDRESS_GROUP);
		RestCaller.BASE_CLASS.put(RestServiceConstant.LOOKUP_ADDRESS_GROUP_SERVICE, LookupAddressGroup.class);
		
		RestCaller.BASE_URL.put(RestServiceConstant.MASTER_ADDRESS_SERVICE, RestConstant.RM_MASTER_ADDRESS);
		RestCaller.BASE_CLASS.put(RestServiceConstant.MASTER_ADDRESS_SERVICE, MasterAddress.class);
		
		RestCaller.BASE_URL.put(RestServiceConstant.PARTY, RestConstant.RM_PARTY);
		RestCaller.BASE_CLASS.put(RestServiceConstant.PARTY, Party.class);
		
		RestCaller.BASE_URL.put(RestServiceConstant.MAIL_SERVICE, RestConstant.RM_MAIL);
		RestCaller.BASE_CLASS.put(RestServiceConstant.MAIL_SERVICE, Object.class);
		
		// master
		RestCaller.BASE_URL.put(RestServiceConstant.COMPANY_SERVICE, RestConstant.RM_COMPANY);
		RestCaller.BASE_CLASS.put(RestServiceConstant.COMPANY_SERVICE, Company.class);
		RestCaller.BASE_URL.put(RestServiceConstant.COMPANY_LOOKUP_SERVICE, RestConstant.RM_COMPANY_LOOKUP);
		RestCaller.BASE_CLASS.put(RestServiceConstant.COMPANY_LOOKUP_SERVICE, CompanyLookup.class);
		RestCaller.BASE_URL.put(RestServiceConstant.COMPANY_PARTY_SERVICE, RestConstant.RM_COMPANY_PARTY);
		RestCaller.BASE_CLASS.put(RestServiceConstant.COMPANY_PARTY_SERVICE, Party.class);
		RestCaller.BASE_URL.put(RestServiceConstant.COMPANY_PRODUCT_SERVICE, RestConstant.RM_COMPANY_PRODUCT);
		RestCaller.BASE_CLASS.put(RestServiceConstant.COMPANY_PRODUCT_SERVICE, CompanyProduct.class);
		RestCaller.BASE_URL.put(RestServiceConstant.COMPANY_WAREHOUSE_SERVICE, RestConstant.RM_COMPANY_WAREHOUSE);
		RestCaller.BASE_CLASS.put(RestServiceConstant.COMPANY_WAREHOUSE_SERVICE, CompanyWarehouse.class);
		RestCaller.BASE_URL.put(RestServiceConstant.COMPANY_MACHINERY_SERVICE, RestConstant.RM_COMPANY_MACHINERY);
		RestCaller.BASE_CLASS.put(RestServiceConstant.COMPANY_MACHINERY_SERVICE, CompanyMachinery.class);
		RestCaller.BASE_URL.put(RestServiceConstant.COMPANY_TRANSPORTER_SERVICE, RestConstant.RM_COMPANY_TRANSPORTER);
		RestCaller.BASE_CLASS.put(RestServiceConstant.COMPANY_TRANSPORTER_SERVICE, Party.class);
		RestCaller.BASE_URL.put(RestServiceConstant.COMPANY_THIRD_PARTY_SERVICE, RestConstant.RM_COMPANY_THIRD_PARTY);
		RestCaller.BASE_CLASS.put(RestServiceConstant.COMPANY_THIRD_PARTY_SERVICE, VWCompanyThirdParty.class);
		RestCaller.BASE_URL.put(RestServiceConstant.COMPANY_MASTER_FEE_SERVICE, RestConstant.RM_COMPANY_MASTER_FEE);
		RestCaller.BASE_CLASS.put(RestServiceConstant.COMPANY_MASTER_FEE_SERVICE, CompanyMasterFee.class);
		RestCaller.BASE_URL.put(RestServiceConstant.STOCK_SERVICE, RestConstant.RM_STOCK);
		RestCaller.BASE_CLASS.put(RestServiceConstant.STOCK_SERVICE, Stock.class);
		
		// procurement
		RestCaller.BASE_URL.put(RestServiceConstant.PURCHASE_ORDER_SERVICE, RestConstant.RM_PURCHASE_ORDER);
		RestCaller.BASE_CLASS.put(RestServiceConstant.PURCHASE_ORDER_SERVICE, PurchaseOrder.class);
		RestCaller.BASE_URL.put(RestServiceConstant.TRANS_IN_SERVICE, RestConstant.RM_TRANS_IN);
		RestCaller.BASE_CLASS.put(RestServiceConstant.TRANS_IN_SERVICE, TransIn.class);
		RestCaller.BASE_URL.put(RestServiceConstant.TRANS_IN_ITEM_SERVICE, RestConstant.RM_TRANS_IN_ITEM);
		RestCaller.BASE_CLASS.put(RestServiceConstant.TRANS_IN_ITEM_SERVICE, TransInItem.class);
		
		//production
		RestCaller.BASE_URL.put(RestServiceConstant.TRANS_PROD_SERVICE, RestConstant.RM_TRANS_PROD);
		RestCaller.BASE_CLASS.put(RestServiceConstant.TRANS_PROD_SERVICE, TransProd.class);
		
		// inventory
		RestCaller.BASE_URL.put(RestServiceConstant.DISPATCH_ORDER_NOTE_SERVICE, RestConstant.RM_DISPATCH_ORDER_NOTE);
		RestCaller.BASE_CLASS.put(RestServiceConstant.DISPATCH_ORDER_NOTE_SERVICE, DispatchOrderNote.class);
		RestCaller.BASE_URL.put(RestServiceConstant.GOODS_RECEIPT_NOTE_SERVICE, RestConstant.RM_GOODS_RECEIPT_NOTE);
		RestCaller.BASE_CLASS.put(RestServiceConstant.GOODS_RECEIPT_NOTE_SERVICE, GoodsReceiptNote.class);
		
		//sales
		RestCaller.BASE_URL.put(RestServiceConstant.TRANS_OUT_SERVICE, RestConstant.RM_TRANS_OUT);
		RestCaller.BASE_CLASS.put(RestServiceConstant.TRANS_OUT_SERVICE, TransOut.class);
		
		//report
		RestCaller.BASE_URL.put(RestServiceConstant.REPORT_COST_EXPENSES_SERVICE, RestConstant.RM_REPORT_COST_EXPENSES);
		RestCaller.BASE_CLASS.put(RestServiceConstant.REPORT_COST_EXPENSES_SERVICE, ViewCostExpensesReport.class);
		RestCaller.BASE_URL.put(RestServiceConstant.REPORT_STOCK_SERVICE, RestConstant.RM_REPORT_STOCK);
		RestCaller.BASE_CLASS.put(RestServiceConstant.REPORT_STOCK_SERVICE, ViewStock.class);
		RestCaller.BASE_URL.put(RestServiceConstant.REPORT_TRANS_IN_SERVICE, RestConstant.RM_REPORT_TRANS_IN);
		RestCaller.BASE_CLASS.put(RestServiceConstant.REPORT_TRANS_IN_SERVICE, ViewTransInReport.class);
		RestCaller.BASE_URL.put(RestServiceConstant.REPORT_CASH_FLOW_SERVICE, RestConstant.RM_REPORT_CASH_FLOW);
		RestCaller.BASE_CLASS.put(RestServiceConstant.REPORT_CASH_FLOW_SERVICE, ViewCashFlow.class);
		
		//business report
		RestCaller.BASE_URL.put(RestServiceConstant.CASH_FLOW_SERVICE, RestConstant.RM_CASH_FLOW);
		RestCaller.BASE_CLASS.put(RestServiceConstant.CASH_FLOW_SERVICE, ViewCashFlow.class);
		
		//forecast weather
		RestCaller.BASE_URL.put(RestServiceConstant.FORECAST_CALL_SERVICE, RestConstant.RM_FORECAST_CALL);
		RestCaller.BASE_CLASS.put(RestServiceConstant.FORECAST_CALL_SERVICE, ForecastCallHourly.class);
	}
}
