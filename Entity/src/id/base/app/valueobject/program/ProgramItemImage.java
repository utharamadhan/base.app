package id.base.app.valueobject.program;

import id.base.app.encryptor.EncodeDecode;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "PROGRAM_ITEM_IMAGE")
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="programItemImageJid", scope=ProgramItemImage.class)
public class ProgramItemImage implements Serializable{

	private static final long serialVersionUID = -4352824404094265090L;
	
	@Id
	@SequenceGenerator(name="PROGRAM_ITEM_IMAGE_PK_PROGRAM_ITEM_IMAGE_SEQ", sequenceName="PROGRAM_ITEM_IMAGE_PK_PROGRAM_ITEM_IMAGE_SEQ", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PROGRAM_ITEM_IMAGE_PK_PROGRAM_ITEM_IMAGE_SEQ")
	@Column(name = "PK_PROGRAM_ITEM_IMAGE", unique = true ,nullable = false)
	private Long pkProgramItemImage;

	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="FK_PROGRAM_ITEM")
	private ProgramItem programItem;
	
	@Column(name="IMAGE_URL")
	private String imageURL;
	
	@Column(name="IMAGE_THUMB_URL")
	private String imageThumbURL;

	@Transient
	public String encodedImage;
	
	public Long getPkProgramItemImage() {
		return pkProgramItemImage;
	}

	public void setPkProgramItemImage(Long pkProgramItemImage) {
		this.pkProgramItemImage = pkProgramItemImage;
	}

	public ProgramItem getProgramItem() {
		return programItem;
	}

	public void setProgramItem(ProgramItem programItem) {
		this.programItem = programItem;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getImageThumbURL() {
		return imageThumbURL;
	}

	public void setImageThumbURL(String imageThumbURL) {
		this.imageThumbURL = imageThumbURL;
	}
	
	public String getEncodedImage() throws Exception {
		if(getImageThumbURL()!=null && !"".equals(getImageThumbURL())){
			encodedImage = EncodeDecode.getBase64FromLink(getImageThumbURL());
		}else if(getImageURL()!=null && !"".equals(getImageURL())){
			encodedImage = EncodeDecode.getBase64FromLink(getImageURL());
		}
		return encodedImage;
	}

}