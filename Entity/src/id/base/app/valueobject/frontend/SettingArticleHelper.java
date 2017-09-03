package id.base.app.valueobject.frontend;


public class SettingArticleHelper {

	public static SettingArticleHelper getInstance(String title, String description, String url, Integer orderNo) {
		SettingArticleHelper obj = new SettingArticleHelper();
		obj.setTitle(title);
		obj.setDescription(description);
		obj.setUrl(url);
		obj.setOrderNo(orderNo);
		return obj;
	}
	private String title;
	private String description;
	private String url;
	private Integer orderNo;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
}
