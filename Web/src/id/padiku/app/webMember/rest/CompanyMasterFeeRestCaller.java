package id.padiku.app.webMember.rest;

import id.padiku.app.SystemConstant;
import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.LoginSessionUtil;
import id.padiku.app.rest.RestBase;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.valueobject.master.CompanyMasterFee;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CompanyMasterFeeRestCaller extends RestBase<CompanyMasterFee>{

	protected static Logger LOGGER = LoggerFactory.getLogger(CompanyMasterFeeRestCaller.class);
	
	private HttpHeaders headers = new HttpHeaders();
	
	public CompanyMasterFeeRestCaller() {
		super();
		baseClass = CompanyMasterFee.class;
		baseUrl = RestConstant.RM_COMPANY_MASTER_FEE;
		try{
	        String userName = LoginSessionUtil.getLogin().getUserName();
	        if(StringUtils.isNotEmpty(userName)){
				headers.add(RestConstant.USER_CALLER, userName);
			}
        }catch(Exception e){
        	headers.add(RestConstant.USER_CALLER, SystemConstant.SYSTEM_USER);
        }
	}
	
	public List<CompanyMasterFee> findAllByFeeType(Long pkCompany, String feeType) throws SystemException {
		ResponseEntity<List> responseEntity = null;		
		String url = RestConstant.REST_SERVICE+RestConstant.RM_COMPANY_MASTER_FEE+"/findAllByFeeType/{pkCompany}/{feeType}";
		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		RestTemplate rt = new RestTemplate();
		responseEntity = rt.exchange(url, HttpMethod.GET, entity, List.class, pkCompany, feeType);
		
		return resolveList(responseEntity, LinkedList.class);
	}
	
}
