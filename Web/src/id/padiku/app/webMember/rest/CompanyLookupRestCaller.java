package id.padiku.app.webMember.rest;

import id.padiku.app.SystemConstant;
import id.padiku.app.exception.SystemException;
import id.padiku.app.rest.LoginSessionUtil;
import id.padiku.app.rest.RestBase;
import id.padiku.app.rest.RestConstant;
import id.padiku.app.valueobject.master.CompanyLookup;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class CompanyLookupRestCaller extends RestBase<CompanyLookup> {
	protected static Logger LOGGER = LoggerFactory.getLogger(CompanyLookupRestCaller.class);
	
	private HttpHeaders headers = new HttpHeaders();
	
	private Long pkCompany;
	
	public CompanyLookupRestCaller(Long pkCompany) {
		super();
		baseClass = CompanyLookup.class;
		baseUrl = RestConstant.RM_LOOKUP;
		this.pkCompany = pkCompany;
		try{
	        String userName = LoginSessionUtil.getLogin().getUserName();
	        if(StringUtils.isNotEmpty(userName)){
				headers.add(RestConstant.USER_CALLER, userName);
			}
        }catch(Exception e){
        	headers.add(RestConstant.USER_CALLER, SystemConstant.SYSTEM_USER);
        }
	}
	
	public List<CompanyLookup> findByLookupGroup(String lookupGroup) throws SystemException {
		ResponseEntity<List> responseEntity = null;		
		String url = RestConstant.REST_SERVICE+RestConstant.RM_COMPANY_LOOKUP+"/findCompanyLookupByLookupGroup/{pkCompany}/{lookupGroup}";
		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		RestTemplate rt = new RestTemplate();
		responseEntity = rt.exchange(url, HttpMethod.GET, entity, List.class, pkCompany, lookupGroup);
		
		return resolveList(responseEntity, LinkedList.class);
	}
	
	public List<CompanyLookup> findByLookupGroupOrderBy(String lookupGroup, String orderBy, boolean desc) throws SystemException {
		ResponseEntity<List> responseEntity = null;		
		String url = RestConstant.REST_SERVICE+RestConstant.RM_COMPANY_LOOKUP+"/findCompanyLookupByLookupGroup/{pkCompany}/{lookupGroup}/{orderBy}/{desc}";
		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		RestTemplate rt = new RestTemplate();
		responseEntity = rt.exchange(url, HttpMethod.GET, entity, List.class, new Object[]{pkCompany, lookupGroup,orderBy,desc});
		
		return resolveList(responseEntity, LinkedList.class);
	}
	
	public CompanyLookup findCompanyLookupById(Long pkCompanyLookup) throws SystemException {
		CompanyLookup lookup = null;
		String url = RestConstant.REST_SERVICE+RestConstant.RM_COMPANY_LOOKUP+"/"+pkCompanyLookup;
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		RestTemplate rt = new RestTemplate();
		ResponseEntity<CompanyLookup> responseEntity = rt.exchange(url, HttpMethod.GET, entity, CompanyLookup.class);
		lookup = responseEntity.getBody();
		return lookup;
	}
	
	public CompanyLookup findLookupByCodeAndLookupGroup(String code, String lookupGroup) throws SystemException {
		CompanyLookup lookup = null;
		String url = RestConstant.REST_SERVICE+RestConstant.RM_COMPANY_LOOKUP+"/findCompanyLookupByCodeAndLookupGroup/"+pkCompany+"/"+code+"/"+lookupGroup;
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		RestTemplate rt = new RestTemplate();
		ResponseEntity<CompanyLookup> responseEntity= rt.exchange(url, HttpMethod.GET, entity, CompanyLookup.class);
		lookup = responseEntity.getBody();
		return lookup;
	}
	
	public Map<String,CompanyLookup> findMapByLookupGroup(String lookupGroup) throws SystemException {
		ResponseEntity<List> responseEntity = null;		
		String url = RestConstant.REST_SERVICE+RestConstant.RM_COMPANY_LOOKUP+"/findCompanyLookupByLookupGroup/{pkCompany}/{lookupGroup}";
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		RestTemplate rt = new RestTemplate();
		responseEntity = rt.exchange(url, HttpMethod.GET, entity, List.class, pkCompany, lookupGroup);
		List<CompanyLookup> list = resolveList(responseEntity, LinkedList.class);
		Map<String,CompanyLookup> mapLookup = new HashMap<>();
		for (CompanyLookup lookup : list) {
			mapLookup.put(lookup.getCode(), lookup);
		}
		return mapLookup;
	}
	
	public Map<String,List<CompanyLookup>> findByLookupGroups(List<String> lookupGroups) throws SystemException {
		ResponseEntity<List> responseEntity = null;		
		String url = RestConstant.REST_SERVICE+RestConstant.RM_COMPANY_LOOKUP+"/findByLookupGroups";
		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		RestTemplate rt = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("lg", lookupGroups.toArray(new String[lookupGroups.size()]));
		responseEntity = rt.exchange(builder.build().toUri(), HttpMethod.GET, entity, List.class);
		
		List<CompanyLookup> lookups = resolveList(responseEntity, LinkedList.class);
		Map<String,List<CompanyLookup>> lookupMaps = new LinkedHashMap<String,List<CompanyLookup>>();
		for (CompanyLookup lookup : lookups) {
			String lookupGroupString = lookup.getLookupGroupString();
			if(lookupMaps.get(lookupGroupString)==null){
				lookupMaps.put(lookupGroupString, new LinkedList<CompanyLookup>());
			}
			lookupMaps.get(lookupGroupString).add(lookup);
		}
		return lookupMaps;
	}
}
