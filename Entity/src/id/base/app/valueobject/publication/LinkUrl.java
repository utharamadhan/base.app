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
@Table(name = "LINK_URL")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="linkUrlJid", scope=LinkUrl.class)
public class LinkUrl extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -5286993108604013813L;
	
	public static final String PK_LINK_URL = "pkLinkUrl";
	public static final String TYPE = "type";
	public static final String ORDER_NO = "orderNo";
	public static final String TITLE = "title";
	public static final String URL = "url";
	public static final String STATUS = "status";
	
	public static LinkUrl getInstance() {
		return new LinkUrl();
	}
	
	@Id
	@SequenceGenerator(name="LINK_URL_PK_LINK_URL_SEQ", sequenceName="LINK_URL_PK_LINK_URL_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="LINK_URL_PK_LINK_URL_SEQ")
	@Column(name = "PK_LINK_URL", unique = true ,nullable = false)
	private Long pkLinkUrl;
	
	@Column(name="TYPE")
	private String type;
	
	@Column(name="ORDER_NO")
	private Integer orderNo;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="URL")
	private String url;
	
	@Column(name="STATUS")
    private Integer status;
	
	@Transient
	private String statusStr;
	
	public Long getPkLinkUrl() {
		return pkLinkUrl;
	}

	public void setPkLinkUrl(Long pkLinkUrl) {
		this.pkLinkUrl = pkLinkUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusStr() {
		return ILookupConstant.Status.STATUS_MAP.get(status);
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
}