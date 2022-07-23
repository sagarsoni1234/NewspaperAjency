package billhistory;

public class billbean {
 String cmobile;
 String datefrom;
 String dateto;
 float amount;
 int status;
public billbean(String cmobile, String datefrom, String dateto, float amount, int status) {
	super();
	this.cmobile = cmobile;
	this.datefrom = datefrom;
	this.dateto = dateto;
	this.amount = amount;
	this.status = status;
}
public String getCmobile() {
	return cmobile;
}
public void setCmobile(String cmobile) {
	this.cmobile = cmobile;
}
public String getDatefrom() {
	return datefrom;
}
public void setDatefrom(String datefrom) {
	this.datefrom = datefrom;
}
public String getDateto() {
	return dateto;
}
public void setDateto(String dateto) {
	this.dateto = dateto;
}
public float getAmount() {
	return amount;
}
public void setAmount(float amount) {
	this.amount = amount;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
 
}
