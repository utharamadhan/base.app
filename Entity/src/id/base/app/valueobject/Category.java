package id.base.app.valueobject;

import id.base.app.ILookupConstant;
import id.base.app.encryptor.EncodeDecode;
import id.base.app.valueobject.learning.LearningItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CATEGORY")
public class Category extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 8342553462195240581L;
	
	public static final String PK_CATEGORY 	= "pkCategory";
	public static final String TYPE 		= "type";
	public static final String TITLE 		= "title";
	public static final String PERMALINK	= "permalink";
	public static final String IMAGE_URL 	= "imageURL";
	public static final String IMAGE_THUMB_URL = "imageThumbURL";
	public static final String DESCRIPTION	= "description";
	public static final String ORDER_NO		= "orderNo";
	public static final String IS_ITEMS_NEW_PAGE = "isItemsNewPage";
	public static final String IS_SHOW_FILTER = "isShowFilter";
	public static final String DETAIL_LINK_IMAGE_URL = "detailLinkImageURL";
	public static final String STATUS		= "status";
	
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
		
	@Column(name="TYPE")
	private String type;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="PERMALINK")
	private String permalink;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="ORDER_NO")
	private Integer orderNo;
	
	@Column(name="IMAGE_URL")
	private String imageURL;
	
	@Column(name="IMAGE_THUMB_URL")
	private String imageThumbURL;
	
	@Transient
	public String encodedPicture;
	
	@Column(name = "IS_ITEMS_NEW_PAGE")
	private Boolean isItemsNewPage;
	
	@Column(name = "IS_SHOW_FILTER")
	private Boolean isShowFilter;
	
	@Column(name = "DETAIL_LINK_IMAGE_URL")
	private String detailLinkImageURL;
	
	@Transient
	public String encodedDetailLinkImageURL;
	
	@Column(name="STATUS")
	private Integer status;
	
	@Transient
	private String statusStr;
	
	@ManyToMany
	@JoinTable(name="LEARNING_ITEM_CATEGORY",
			joinColumns=@JoinColumn(name="FK_CATEGORY"),
			inverseJoinColumns=@JoinColumn(name="FK_LEARNING_ITEM"))
	private List<LearningItem> items;
	
	@Transient
	private boolean checked;
	
	@Transient
	private List<Faq> faqs = new ArrayList<>();
	
	public Long getPkCategory() {
		return pkCategory;
	}

	public void setPkCategory(Long pkCategory) {
		this.pkCategory = pkCategory;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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
	
	public Boolean getIsItemsNewPage() {
		return isItemsNewPage;
	}

	public void setIsItemsNewPage(Boolean isItemsNewPage) {
		this.isItemsNewPage = isItemsNewPage;
	}
	
	public Boolean getIsShowFilter() {
		return isShowFilter;
	}

	public void setIsShowFilter(Boolean isShowFilter) {
		this.isShowFilter = isShowFilter;
	}
	
	public String getDetailLinkImageURL() {
		return detailLinkImageURL;
	}

	public void setDetailLinkImageURL(String detailLinkImageURL) {
		this.detailLinkImageURL = detailLinkImageURL;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getStatusStr() {
		return ILookupConstant.Status.STATUS_MAP.get(status);
	}
	
	public List<LearningItem> getItems() {
		return items;
	}

	public void setItems(List<LearningItem> items) {
		if(this.items == null){
			this.items = new ArrayList<LearningItem>();
		}else{
			this.items.clear();	
		}
		if(null != items){
			this.items.addAll(items);
		}
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public List<Faq> getFaqs() {
		return faqs;
	}

	public void setFaqs(List<Faq> faqs) {
		this.faqs = faqs;
	}

	public String getEncodedPicture() throws Exception {
		if(getImageThumbURL()!=null && !"".equals(getImageThumbURL())){
			encodedPicture = EncodeDecode.getBase64FromLink(getImageThumbURL());
		}else if(getImageURL()!=null && !"".equals(getImageURL())){
			encodedPicture = EncodeDecode.getBase64FromLink(getImageURL());
		}
		return encodedPicture;
	}

	public void setEncodedPicture(String encodedPicture) {
		this.encodedPicture = encodedPicture;
	}

	public String getEncodedDetailLinkImageURL() throws Exception {
		if(getDetailLinkImageURL()!=null && !"".equals(getDetailLinkImageURL())){
			encodedDetailLinkImageURL = EncodeDecode.getBase64FromLink(getDetailLinkImageURL());
		}else if(getDetailLinkImageURL()!=null && !"".equals(getDetailLinkImageURL())){
			encodedDetailLinkImageURL = EncodeDecode.getBase64FromLink(getDetailLinkImageURL());
		}
		return encodedDetailLinkImageURL;
	}

	public void setEncodedDetailLinkImageURL(String encodedDetailLinkImageURL) {
		this.encodedDetailLinkImageURL = encodedDetailLinkImageURL;
	}
			
}
