package id.base.app.valueobject.advisory;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import id.base.app.valueobject.BaseEntity;

@Entity
@Table(name = "ADVISORY_POST")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="advisoryPostJid", scope=AdvisoryPost.class)
public class AdvisoryPost extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8793258862185720735L;
	
	public static final String PK_ADVISORY_POST 		= "pkAdvisoryPost";
	public static final String NAME 					= "name";
	public static final String VALID 					= "valid";
	
	public static AdvisoryPost getInstance() {
		return new AdvisoryPost();
	}
	
	@Id
	@SequenceGenerator(name="ADVISORY_PK_ADVISORY_POST_SEQ", sequenceName="ADVISORY_PK_ADVISORY_POST_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ADVISORY_PK_ADVISORY_POST_SEQ")
	@Column(name = "PK_ADVISORY_POST", unique = true ,nullable = false)
	private Long pkAdvisoryPost;
		
	@Column(name="NAME")
	private String name;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="VALID")
	private Integer valid;
	
	public Long getPkAdvisoryPost() {
		return pkAdvisoryPost;
	}

	public void setPkAdvisoryPost(Long pkAdvisoryPost) {
		this.pkAdvisoryPost = pkAdvisoryPost;
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

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}
		
	
}
