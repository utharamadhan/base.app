package id.base.app.valueobject.publication;

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
@Table(name = "DIGITAL_BOOK")
public class DigitalBook extends BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7698703933226904281L;
	
	private DigitalBook(){}
	
	public static final String PK_DIGITAL_BOOK = "pkDigitalBook";
	public static final String TITLE = "title";
	public static final String COVER_IMAGE_URL = "coverImageURL";
	public static final String FILE_URL = "fileURL";
	public static final String DESCRIPTION = "description";
	public static final String STATUS = "status";
	
	public static DigitalBook getInstance() {
		return new DigitalBook();
	}
	
	@Id
	@SequenceGenerator(name="DIGITAL_BOOK_PK_DIGITAL_BOOK_SEQ", sequenceName="DIGITAL_BOOK_PK_DIGITAL_BOOK_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="DIGITAL_BOOK_PK_DIGITAL_BOOK_SEQ")
	@Column(name = "PK_DIGITAL_BOOK", unique = true ,nullable = false)
	private Long pkDigitalBook;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="COVER_IMAGE_URL")
	private String coverImageURL;

	@Column(name="FILE_URL")
	private String fileURL;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="STATUS")
	private Integer status;

	public Long getPkDigitalBook() {
		return pkDigitalBook;
	}
	public void setPkDigitalBook(Long pkDigitalBook) {
		this.pkDigitalBook = pkDigitalBook;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getCoverImageURL() {
		return coverImageURL;
	}
	public void setCoverImageURL(String coverImageURL) {
		this.coverImageURL = coverImageURL;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getFileURL() {
		return fileURL;
	}
	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
