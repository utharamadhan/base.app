package id.base.app.valueobject.publication;

import id.base.app.ILookupConstant;
import id.base.app.valueobject.BaseEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "HOUSING_INDEX")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="housingIndexJid", scope=HousingIndex.class)
public class HousingIndex extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -1595382027549917721L;
	
	public static final String PK_HOUSING_INDEX = "pkHousingIndex";
	public static final String TITLE = "title";
	public static final String VALUE = "value";
	public static final String ORDER_NO = "orderNo";
	public static final String STATUS = "status";
	
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
	
	@Column(name="VALUE")
	private String value;
	
	@Column(name="ORDER_NO")
	private Integer orderNo;
	
	@Column(name="STATUS")
    private Integer status;
	
	@Transient
	private String statusStr;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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
		
}