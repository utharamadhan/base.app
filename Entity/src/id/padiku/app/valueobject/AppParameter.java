package id.padiku.app.valueobject;

import id.padiku.app.SystemConstant;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "APP_PARAMETER")
public class AppParameter extends BaseEntity {

	private static final long serialVersionUID = 5933206321367656004L;

	private static final String INTEGER = "INTEGER";
	private static final String LONG    = "LONG";
	private static final String DOUBLE  = "DOUBLE";
	private static final String STRING  = "STRING";
	private static final String DATE    = "DATE";
	private static final String BOOLEAN = "BOOLEAN";
	// Fields

	@Id
	@GeneratedValue
	@Column(name = "PK_APP_PARAMETER", unique = true, nullable = false)
	@NotNull(groups=UpdateEntity.class, message="{error.message.update.not.allowed}")
	@Null(groups=CreateEntity.class, message="{error.message.create.not.allowed}")
	private Long pkAppParameter;
	
	@Column(name = "NAME", length = 200)
	private String name;
	@Column(name = "VALUE", length = 200)
	private String value;
	@Column(name = "DESCR", length = 500)
	private String descr;
	@Column(name = "IS_VIEWABLE",nullable=false)
	private Boolean isViewable;
	@Column(name = "DATATYPE")
	private Integer datatype;
	@Transient
	private String dataTypeDescr;
	
	public static final String IS_VIEWABLE="isViewable";
	public static final String NAME = "name";
	public static final String VALUE = "value";
	
	// Constructors

	/** default constructor */
	public AppParameter() {
	}

	/** full constructor */
	public AppParameter(String name, String value, String descr,
			Boolean isForSecurity, Boolean isViewable, Integer datatype,
			Date lastModificationDate) {
		this.name = name;
		this.value = value;
		this.descr = descr;
		this.isViewable = isViewable;
		this.datatype = datatype;
	}

	public Long getPkAppParameter() {
		return pkAppParameter;
	}

	public void setPkAppParameter(Long pkAppParameter) {
		this.pkAppParameter = pkAppParameter;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Boolean getIsViewable() {
		return this.isViewable;
	}

	public void setIsViewable(Boolean isViewable) {
		this.isViewable = isViewable;
	}

	public Integer getDatatype() {
		return datatype;
	}

	public void setDatatype(Integer datatype) {
		this.datatype = datatype;
	}

	public String getDataTypeDesc() {
		if(datatype!=null){
	        switch (datatype.intValue()) {
	            case SystemConstant.FIELD_TYPE_INT :
	                return INTEGER;
	            case SystemConstant.FIELD_TYPE_LONG :
	                return LONG;
	            case SystemConstant.FIELD_TYPE_DOUBLE :
	                return DOUBLE;
	            case SystemConstant.FIELD_TYPE_STRING :
	                return STRING;
	            case SystemConstant.FIELD_TYPE_DATE :
	                return DATE;
	            case SystemConstant.FIELD_TYPE_BOOLEAN :
	                return BOOLEAN;
	        }
		}
        return null;
    }
}