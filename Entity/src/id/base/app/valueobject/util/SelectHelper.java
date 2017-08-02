package id.base.app.valueobject.util;


public class SelectHelper {

	public static SelectHelper getInstanceValueInteger(Integer valueInteger, String label) {
		SelectHelper obj = new SelectHelper();
		obj.setValueInteger(valueInteger);
		obj.setLabel(label);
		return obj;
	}
	
	public static SelectHelper getInstanceValueLong(Long valueLong, String label) {
		SelectHelper obj = new SelectHelper();
		obj.setValueLong(valueLong);
		obj.setLabel(label);
		return obj;
	}
	
	public static SelectHelper getInstanceValueString(Long valueString, String label) {
		SelectHelper obj = new SelectHelper();
		obj.setValueLong(valueString);
		obj.setLabel(label);
		return obj;
	}
	
	private Integer valueInteger;
	private Long valueLong;
	private String valueString;
	private String label;
	
	public Integer getValueInteger() {
		return valueInteger;
	}

	public void setValueInteger(Integer valueInteger) {
		this.valueInteger = valueInteger;
	}

	public Long getValueLong() {
		return valueLong;
	}
	public void setValueLong(Long valueLong) {
		this.valueLong = valueLong;
	}
	public String getValueString() {
		return valueString;
	}
	public void setValueString(String valueString) {
		this.valueString = valueString;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}