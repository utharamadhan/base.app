package id.padiku.app.valueobject.master;

import id.padiku.app.ILookupConstant;
import id.padiku.app.util.StringFunction;
import id.padiku.app.valueobject.procurement.PurchaseOrder;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "VW_COMPANY_THIRD_PARTY")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="purchaseOrderJid", scope=PurchaseOrder.class)
public class VWCompanyThirdParty implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2262461475497834546L;
	
	@Id
	@Column(name = "PK_PARTY")
	private Long pkParty;
	
	@Column(name = "FK_COMPANY")
	private Long fkCompany;
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ALAMAT")
	private String alamat;
	
	@Column(name = "KELURAHAN")
	private String kelurahan;
	
	@Column(name = "KECAMATAN")
	private String kecamatan;
	
	@Column(name = "KABUPATEN_KOTA")
	private String kabupatenKota;
	
	@Column(name = "PROVINSI")
	private String provinsi;
	
	@Column(name = "KODEPOS")
	private Integer kodepos;
	
	@Column(name = "NPWP")
	private String npwp;
	
	@Column(name = "PK_PARTY_ROLE")
	private Long pkPartyRole;
	
	@Column(name = "ROLE_CODE")
	private String roleCode;
	
	@Transient
	private String roleDescription;

	@Column(name = "STATUS")
	private Integer status;
	

	public Long getPkParty() {
		return pkParty;
	}
	public void setPkParty(Long pkParty) {
		this.pkParty = pkParty;
	}

	public Long getFkCompany() {
		return fkCompany;
	}
	public void setFkCompany(Long fkCompany) {
		this.fkCompany = fkCompany;
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

	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getKelurahan() {
		return kelurahan;
	}
	public void setKelurahan(String kelurahan) {
		this.kelurahan = kelurahan;
	}

	public String getKecamatan() {
		return kecamatan;
	}
	public void setKecamatan(String kecamatan) {
		this.kecamatan = kecamatan;
	}

	public String getKabupatenKota() {
		return kabupatenKota;
	}
	public void setKabupatenKota(String kabupatenKota) {
		this.kabupatenKota = kabupatenKota;
	}

	public String getProvinsi() {
		return provinsi;
	}
	public void setProvinsi(String provinsi) {
		this.provinsi = provinsi;
	}

	public Integer getKodepos() {
		return kodepos;
	}
	public void setKodepos(Integer kodepos) {
		this.kodepos = kodepos;
	}

	public String getNpwp() {
		return npwp;
	}
	public void setNpwp(String npwp) {
		this.npwp = npwp;
	}

	public Long getPkPartyRole() {
		return pkPartyRole;
	}
	public void setPkPartyRole(Long pkPartyRole) {
		this.pkPartyRole = pkPartyRole;
	}

	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Transient
	public String getRoleDescription() {
		if(StringFunction.isNotEmpty(this.roleCode)){
			return ILookupConstant.PartyRole.partyRoleMap.get(this.roleCode);
		}
		return "";
	}
	public void setRoleDescription(String roleDescription){
		this.roleDescription = roleDescription;
	}

}
