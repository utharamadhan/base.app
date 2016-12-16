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
@Table(name = "PROGRAM_POST")
public class ProgramPost extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7058920704118955735L;
	
	public static final String PK_PROGRAM_POST = "pkProgramPost";
	public static final String CODE 	= "code";
	public static final String NAME 	= "name";
	public static final String CONTENT 	= "content";
	public static final String STATUS 	= "status";
	
	public static ProgramPost getInstance() {
		return new ProgramPost();
	}
	
	@Id
	@SequenceGenerator(name="PROGRAM_POST_PK_PROGRAM_POST_SEQ", sequenceName="PROGRAM_POST_PK_PROGRAM_POST_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PROGRAM_POST_PK_PROGRAM_POST_SEQ")
	@Column(name = "PK_PROGRAM_POST", unique = true ,nullable = false)
	private Long pkProgramPost;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkProgramPost() {
		return pkProgramPost;
	}
	public void setPkProgramPost(Long pkProgramPost) {
		this.pkProgramPost = pkProgramPost;
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
