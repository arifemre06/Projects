
public class Employee {
private String ecode;
private String nric;
private int phone;

public Employee(String ecode, String nric, int phone) {
	this.ecode=ecode;
	this.nric=nric;
	this.phone=phone;
}
public String getEcode() {
	return ecode;
}
public void setEcode(String ecode) {
	this.ecode = ecode;
}

public String getNric() {
	return nric;
}
public void setNric(String nric) {
	this.nric = nric;
}

public int getPhone() {
	return phone;
}
public void setPhone(int phone) {
	this.phone = phone;
}
}
