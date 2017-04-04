package id.base.app.valueobject.publication;

import id.base.app.ILookupConstant;
import id.base.app.valueobject.BaseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "HOUSING_INDEX")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="housingIndexJid", scope=HousingIndex.class)
public class HousingIndex extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -2410352630957149832L;
	
	public static final String PK_HOUSING_INDEX = "pkHousingIndex";
	public static final String TITLE = "title";
	public static final String PROVINCE = "province";
	public static final String VALUE = "value";
	
	public static HousingIndex getInstance() {
		return new HousingIndex();
	}
	
	@Id
	@SequenceGenerator(name="HOUSING_INDEX_PK_HOUSING_INDEX_SEQ", sequenceName="HOUSING_INDEX_PK_HOUSING_INDEX_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="HOUSING_INDEX_PK_HOUSING_INDEX_SEQ")
	@Column(name = "PK_HOUSING_INDEX", unique = true ,nullable = false)
	private Long pkHousingIndex;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="FILE_URL")
	private String fileURL;
	
	@Column(name="STATUS")
    private Integer status;
	
	@Transient
	private String statusStr;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="housingIndex", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<HousingIndexProvince> housingIndexProvincesList = new ArrayList<>(); 

	public Long getPkHousingIndex() {
		return pkHousingIndex;
	}

	public void setPkHousingIndex(Long pkHousingIndex) {
		this.pkHousingIndex = pkHousingIndex;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getStatusStr() {
		return ILookupConstant.ArticleStatus.ARTICLE_STATUS_MAP.get(status);
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public List<HousingIndexProvince> getHousingIndexProvincesList() {
		return housingIndexProvincesList;
	}

	public void setHousingIndexProvincesList(
			List<HousingIndexProvince> housingIndexProvincesList) {
		this.housingIndexProvincesList.clear();
		if(null != housingIndexProvincesList){
			this.housingIndexProvincesList.addAll(housingIndexProvincesList);
		}
	}

}