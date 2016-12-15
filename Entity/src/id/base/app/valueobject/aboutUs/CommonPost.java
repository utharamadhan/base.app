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
	public static final String CODE 	= "code";
	public static final String NAME 	= "name";
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
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkCommonPost() {
		return pkCommonPost;
	}
	public void setPkCommonPost(Long pkCommonPost) {
		this.pkCommonPost = pkCommonPost;
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

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
