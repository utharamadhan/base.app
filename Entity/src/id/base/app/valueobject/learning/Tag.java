package id.base.app.valueobject.learning;

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
@Table(name = "TAG")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="tagJid", scope=Tag.class)
public class Tag extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8793258862185720735L;
	
	public static final String PK_TAG 		= "pkTag";
	public static final String CODE 		= "code";
	public static final String NAME 		= "name";
	public static final String VALID 		= "valid";
	public static final String COURSE 		= "course";
	public static final String PK_COURSE 	= "course.pkCourse";
	
	public static Tag getInstance() {
		return new Tag();
	}
	
	public static Tag getInstance(Long pkCourse) {
		Tag c = getInstance();
		return c;
	}
	
	@Id
	@SequenceGenerator(name="TAG_PK_TAG_SEQ", sequenceName="TAG_PK_TAG_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TAG_PK_TAG_SEQ")
	@Column(name = "PK_TAG", unique = true ,nullable = false)
	private Long pkTag;
		
	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="VALID")
	private Integer valid;
	
	public Long getPkTag() {
		return pkTag;
	}

	public void setPkTag(Long pkTag) {
		this.pkTag = pkTag;
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
	
	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}
		
	
}
