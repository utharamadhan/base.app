package id.base.app.valueobject.advisory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

import id.base.app.valueobject.BaseEntity;

@Entity
@Table(name = "CATEGORY")
public class Category extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8342553462195240581L;
	
	public static final String PK_CATEGORY 	= "pkCategory";
	public static final String CODE 		= "code";
	public static final String NAME 		= "name";
	public static final String VALID 		= "valid";
	
	public static Category getInstance() {
		return new Category();
	}
	
	public static Category getInstance(Long pkCourse) {
		Category c = getInstance();
		return c;
	}
	
	@Id
	@SequenceGenerator(name="CATEGORY_PK_CATEGORY_SEQ", sequenceName="CATEGORY_PK_CATEGORY_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CATEGORY_PK_CATEGORY_SEQ")
	@Column(name = "PK_CATEGORY", unique = true ,nullable = false)
	private Long pkCategory;
		
	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="BASIC_PICTURE_URL")
	private String basicPictureURL;
	
	@Column(name="VALID")
	private Integer valid;
	
	public Long getPkCategory() {
		return pkCategory;
	}

	public void setPkCategory(Long pkCategory) {
		this.pkCategory = pkCategory;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBasicPictureURL() {
		return basicPictureURL;
	}

	public void setBasicPictureURL(String basicPictureURL) {
		this.basicPictureURL = basicPictureURL;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}
		
	
}
