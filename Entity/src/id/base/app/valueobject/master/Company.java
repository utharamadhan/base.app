package id.base.app.valueobject.master;

import id.base.app.SystemConstant;
import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.CreateEntity;
import id.base.app.valueobject.UpdateEntity;
import id.base.app.valueobject.party.PartyCompany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "COMPANY")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="companyJid", scope=Company.class)
public class Company extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = -5435629896888204364L;

	public Company () {}
	
	public static Company getInstance() {
		Company obj = new Company();
			obj.setStatus(SystemConstant.ValidFlag.VALID);
		return obj;
	}
	
	public static Company getInstance (Long pkCompany) {
		Company obj = new Company();
			obj.setPkCompany(pkCompany);
		return obj;
	}
	
	public static Company getInstance (Long pkCompany, String name) {
		Company obj = new Company();
			obj.setPkCompany(pkCompany);
			obj.setName(name);
		return obj;
	}
	
	public static final String ID = "pkCompany";
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String STATUS = "status";
	public static final String PARTY_COMPANIES = "partyCompanies";
	public static final String PARTY_COMPANIES_COMPANY = PARTY_COMPANIES + ".company";
	public static final String PARTY_COMPANIES_COMPANY_PK = PARTY_COMPANIES_COMPANY + ".pkCompany";
	public static final String PARTY_COMPANIES_PARTY = PARTY_COMPANIES + ".party";
	public static final String PARTY_COMPANIES_PARTY_PK = PARTY_COMPANIES_PARTY + ".pkParty";
	public static final String COMPANY_ADDRESS = "companyAddresses";
	public static final String COMPANY_ADDRESS_KODEPOS = COMPANY_ADDRESS + ".kodepos";
	public static final String COMPANY_ADDRESS_ALAMAT_VALFIELD = COMPANY_ADDRESS + "[%d].alamat";
	public static final String COMPANY_ADDRESS_PROVINSI = COMPANY_ADDRESS + ".provinsi";
	public static final String COMPANY_ADDRESS_PROVINSI_NAME = COMPANY_ADDRESS_PROVINSI + ".name";
	public static final String COMPANY_ADDRESS_PROVINSI_VALFIELD = COMPANY_ADDRESS + "[%d].provinsi.pkLookupAddress";
	public static final String COMPANY_ADDRESS_KABUPATEN_KOTA = COMPANY_ADDRESS + ".kabupatenKota";
	public static final String COMPANY_ADDRESS_KABUPATEN_KOTA_NAME = COMPANY_ADDRESS_KABUPATEN_KOTA + ".name";
	public static final String COMPANY_ADDRESS_KABUPATEN_KOTA_VALFIELD = COMPANY_ADDRESS + "[%d].kabupatenKota.pkLookupAddress";
	public static final String COMPANY_ADDRESS_KELURAHAN = COMPANY_ADDRESS + ".kelurahan";
	public static final String COMPANY_ADDRESS_KELURAHAN_NAME = COMPANY_ADDRESS_KELURAHAN + ".name";
	public static final String COMPANY_ADDRESS_KELURAHAN_VALFIELD = COMPANY_ADDRESS + "[%d].kelurahan.pkLookupAddress";
	public static final String COMPANY_ADDRESS_KECAMATAN = COMPANY_ADDRESS + ".kecamatan";
	public static final String COMPANY_ADDRESS_KECAMATAN_NAME = COMPANY_ADDRESS_KECAMATAN + ".name";
	public static final String COMPANY_ADDRESS_KECAMATAN_VALFIELD = COMPANY_ADDRESS + "[%d].kecamatan.pkLookupAddress";
	
	
	public static String[] MAINTENANCE_LIST_FIELDS =
			new String[] { ID, CODE, NAME } ;

	@Id
	@Column(name = "PK_COMPANY", unique = true, nullable = false)
	@SequenceGenerator(name="COMPANY_PK_COMPANY_SEQ", sequenceName="COMPANY_PK_COMPANY_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COMPANY_PK_COMPANY_SEQ")
	@NotNull(groups=UpdateEntity.class, message="{error.message.update.not.allowed}")
	@Null(groups=CreateEntity.class, message="{error.message.create.not.allowed}")
	private Long pkCompany;
	
	@Column(name = "COMPANY_CODE")
	private String code;
	
	@Column(name = "COMPANY_NAME", length = 200)
	private String name;
	
	@Column(name = "NPWP")
	private String npwp;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@OneToMany(mappedBy="company", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<CompanyAddress> companyAddresses = new ArrayList<>();
	
	@OneToMany(mappedBy="company", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<CompanyContact> companyContacts = new ArrayList<>();
	
	@OneToMany(mappedBy="company", cascade=CascadeType.ALL)
	private Set<CompanyLookup> companyLookups = new HashSet<CompanyLookup>();
	
	@OneToMany(mappedBy="company", cascade=CascadeType.ALL)
	private Set<PartyCompany> partyCompanies = new HashSet<PartyCompany>();
	
	@OneToMany(mappedBy="company", cascade=CascadeType.ALL)
	private Set<CompanyWarehouse> companyWarehouses = new HashSet<CompanyWarehouse>();

	public Long getPkCompany() {
		return pkCompany;
	}

	public void setPkCompany(Long pkCompany) {
		this.pkCompany = pkCompany;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNpwp() {
		return npwp;
	}
	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}

	public List<CompanyAddress> getCompanyAddresses() {
		return companyAddresses;
	}
	public void setCompanyAddresses(List<CompanyAddress> companyAddresses) {
		if(this.companyAddresses == null) {
			this.companyAddresses = new ArrayList<CompanyAddress>();
		}
		if(companyAddresses != null) {
			this.companyAddresses.clear();
			this.companyAddresses.addAll(companyAddresses);
		}
	}

	public List<CompanyContact> getCompanyContacts() {
		return companyContacts;
	}
	public void setCompanyContacts(List<CompanyContact> companyContacts) {
		if(this.companyContacts == null) {
			this.companyContacts = new ArrayList<CompanyContact>();
		}
		if(companyContacts != null) {
			this.companyContacts.clear();
			this.companyContacts.addAll(companyContacts);
		}
	}
	
	public Set<CompanyLookup> getCompanyLookups() {
		return companyLookups;
	}
	public void setCompanyLookups(Set<CompanyLookup> companyLookups) {
		this.companyLookups = companyLookups;
	}

	public Set<PartyCompany> getPartyCompanies() {
		return partyCompanies;
	}
	public void setPartyCompanies(Set<PartyCompany> partyCompanies) {
		this.partyCompanies = partyCompanies;
	}

	public Set<CompanyWarehouse> getCompanyWarehouses() {
		return companyWarehouses;
	}
	public void setCompanyWarehouses(Set<CompanyWarehouse> companyWarehouses) {
		this.companyWarehouses = companyWarehouses;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}