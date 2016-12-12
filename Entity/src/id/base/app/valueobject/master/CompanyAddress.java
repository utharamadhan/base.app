package id.base.app.valueobject.master;

import id.base.app.valueobject.BaseEntity;
import id.base.app.valueobject.CreateEntity;
import id.base.app.valueobject.LookupAddress;
import id.base.app.valueobject.UpdateEntity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "COMPANY_ADDRESS")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="companyAddressJid", scope=CompanyAddress.class)
public class CompanyAddress extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -1854948922754401793L;

	public CompanyAddress () {}
	
	public static CompanyAddress getInstance() {
		return new CompanyAddress();
	}
	
	public static CompanyAddress getInstance (Long pkCompany) {
		CompanyAddress obj = new CompanyAddress();
			obj.setCompany(Company.getInstance(pkCompany));
		return obj;
	}
	
	@Id
	@Column(name = "PK_COMPANY_ADDRESS", unique = true, nullable = false)
	@SequenceGenerator(name="COMPANY_ADDRESS_PK_COMPANY_ADDRESS_SEQ", sequenceName="COMPANY_ADDRESS_PK_COMPANY_ADDRESS_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COMPANY_ADDRESS_PK_COMPANY_ADDRESS_SEQ")
	@NotNull(groups=UpdateEntity.class, message="{error.message.update.not.allowed}")
	@Null(groups=CreateEntity.class, message="{error.message.create.not.allowed}")
	private Long pkCompanyAddress;
	
	@ManyToOne(cascade={CascadeType.DETACH})
	@JoinColumn(name="FK_COMPANY")
	private Company company;
	
	@Column(name="ALAMAT")
	private String alamat = "";

	@Column(name="KODEPOS")
	private Integer kodepos;
	
	@OneToOne
	@JoinColumn(name = "FK_LOOKUP_ADDR_KELURAHAN")
	private LookupAddress kelurahan;
	
	@OneToOne
	@JoinColumn(name = "FK_LOOKUP_ADDR_KECAMATAN")
	private LookupAddress kecamatan;
	
	@OneToOne
	@JoinColumn(name = "FK_LOOKUP_ADDR_KABUPATEN_KOTA")
	private LookupAddress kabupatenKota;
	
	@OneToOne
	@JoinColumn(name = "FK_LOOKUP_ADDR_PROVINSI")
	private LookupAddress provinsi;
	
	@Column(name="LONGITUDE")
	private String longitude;
	
	@Column(name="LATITUDE")
	private String latitude;

	public Long getPkCompanyAddress() {
		return pkCompanyAddress;
	}
	public void setPkCompanyAddress(Long pkCompanyAddress) {
		this.pkCompanyAddress = pkCompanyAddress;
	}

	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}

	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	
	public Integer getKodepos() {
		return kodepos;
	}
	public void setKodepos(Integer kodepos) {
		this.kodepos = kodepos;
	}

	public LookupAddress getKelurahan() {
		return kelurahan;
	}
	public void setKelurahan(LookupAddress kelurahan) {
		this.kelurahan = kelurahan;
	}
	
	public LookupAddress getKecamatan() {
		return kecamatan;
	}

	public void setKecamatan(LookupAddress kecamatan) {
		this.kecamatan = kecamatan;
	}

	public LookupAddress getKabupatenKota() {
		return kabupatenKota;
	}
	public void setKabupatenKota(LookupAddress kabupatenKota) {
		this.kabupatenKota = kabupatenKota;
	}
	
	public LookupAddress getProvinsi() {
		return provinsi;
	}
	public void setProvinsi(LookupAddress provinsi) {
		this.provinsi = provinsi;
	}
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}