package Customersdata;

public class customersbean {

	String name;
	String area;
	String address;
	String mobile;
	String selpapers;
	float totalprice;
	String dostart;
	public customersbean(String name, String area, String address, String mobile, String selpapers, float totalprice,
			String dostart) {
		super();
		this.name = name;
		this.area = area;
		this.address = address;
		this.mobile = mobile;
		this.selpapers = selpapers;
		this.totalprice = totalprice;
		this.dostart = dostart;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSelpapers() {
		return selpapers;
	}
	public void setSelpapers(String selpapers) {
		this.selpapers = selpapers;
	}
	public float getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
	}
	public String getDostart() {
		return dostart;
	}
	public void setDostart(String dostart) {
		this.dostart = dostart;
	}
	
	}
