
public class Employer {
private String employername;
private int esalary;
public int cratedtablecount;
public Employer(String employername,int esalary){
	this.employername=employername;
	this.esalary=esalary;
}
public String getEmployername(){
	return this.employername;
}
public void setEmployername(String employername){
	this.employername=employername;
}
public int getEsalary(){
	return this.esalary;
}
public void setEsalary(int esalary){
	this.esalary=esalary;
}
public int getCratedtablecount(){
	return this.cratedtablecount;
}
public void setCratedtablecount(int cratedtablecount){
	this.cratedtablecount=cratedtablecount;
}
}