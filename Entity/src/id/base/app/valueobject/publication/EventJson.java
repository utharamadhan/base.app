package id.base.app.valueobject.publication;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import id.base.app.valueobject.BaseEntity;

public class EventJson extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2512890745160322636L;
	
	public static EventJson getInstance() {
		return new EventJson();
	}
	
	@JsonProperty(value="title")
	private String title;
	
	@JsonProperty(value="start")
	private String eventDate;
	
	@JsonProperty(value="url")
	private String url;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
