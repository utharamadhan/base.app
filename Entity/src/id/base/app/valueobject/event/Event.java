package id.base.app.valueobject.event;

import id.base.app.valueobject.BaseEntity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EVENT")
public class Event extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2512890745160322636L;
	
	public static final String PK_EVENT = "pkEvent";
	public static final String CODE 	= "code";
	public static final String NAME 	= "name";
	public static final String DESCRIPTION 	= "description";
	public static final String STATUS 	= "status";
	public static final String EVENT_DATE = "eventDate";
	
	public static Event getInstance() {
		return new Event();
	}
	
	@Id
	@SequenceGenerator(name="EVENT_PK_EVENT_SEQ", sequenceName="EVENT_PK_EVENT_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EVENT_PK_EVENT_SEQ")
	@Column(name = "PK_EVENT", unique = true ,nullable = false)
	private Long pkEvent;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="EVENT_DATE")
	private Date eventDate;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkEvent() {
		return pkEvent;
	}
	public void setPkEvent(Long pkEvent) {
		this.pkEvent = pkEvent;
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
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
}
