package HawkerData;

public class Hawkerbean {
	
	String name;
	String addresss;
	String addhaar;
	String mobile;
	float esal;
	String date;
	String areas;
	public Hawkerbean(String name, String addresss, String addhaar, String mobile, float esal, String date,
			String areas) {
		super();
		this.name = name;
		this.addresss = addresss;
		this.addhaar = addhaar;
		this.mobile = mobile;
		this.esal = esal;
		this.date = date;
		this.areas = areas;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddresss() {
		return addresss;
	}
	public void setAddresss(String addresss) {
		this.addresss = addresss;
	}
	public String getAddhaar() {
		return addhaar;
	}
	public void setAddhaar(String addhaar) {
		this.addhaar = addhaar;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public float getEsal() {
		return esal;
	}
	public void setEsal(float esal) {
		this.esal = esal;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAreas() {
		return areas;
	}
	public void setAreas(String areas) {
		this.areas = areas;
	}
	
	}
