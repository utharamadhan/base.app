package id.base.app.valueobject.aboutUs;

import id.base.app.valueobject.BaseEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COMMON_POST")
public class CommonPost extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8529693428251387150L;
	
	public static final String PK_COMMON_POST = "pkCommonPost";
	public static final String TITLE 	= "title";
	public static final String CONTENT 	= "content";
	public static final String STATUS 	= "status";
	
	public static CommonPost getInstance() {
		return new CommonPost();
	}
	
	@Id
	@SequenceGenerator(name="COMMON_POST_PK_COMMON_POST_SEQ", sequenceName="COMMON_POST_PK_COMMON_POST_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COMMON_POST_PK_COMMON_POST_SEQ")
	@Column(name = "PK_COMMON_POST", unique = true ,nullable = false)
	private Long pkCommonPost;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="ORDER_NO")
	private Integer orderNo;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkCommonPost() {
		return pkCommonPost;
	}
	public void setPkCommonPost(Long pkCommonPost) {
		this.pkCommonPost = pkCommonPost;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	
}
