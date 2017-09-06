package id.base.app.valueobject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="VW_SEARCH")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="VWSearchJid", scope=VWSearch.class)
public class VWSearch implements Serializable {

	private static final long serialVersionUID = -419964780705396362L;
	
	public static final String PK = "pk";
	public static final String PERMALINK = "permalink";
	public static final String LINK_DETAIL = "linkDetail";
	public static final String TITLE = "title";
	public static final String TYPE = "type";
	public static final String ORDER_NO = "orderNo";
	
	@Id
	@Column(name="PK")
	private Long pk;
	
	@Column(name="PERMALINK")
	private String permalink;
	
	@Column(name="LINK_DETAIL")
	private String linkDetail;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="TYPE")
	private String type;
	
	@Column(name="ORDER_NO")
	private Integer orderNo;

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getLinkDetail() {
		return linkDetail;
	}

	public void setLinkDetail(String linkDetail) {
		this.linkDetail = linkDetail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	
}
