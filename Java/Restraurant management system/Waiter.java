
public class Waiter {
private String waitername;
private int wsalary;
public int tableservicecount;

public Waiter(String waitername,int wsalary){
	this.waitername=waitername;
	this.wsalary=wsalary;
}
public String getWaitername(){
	return this.waitername;
}
public void setWaitername(String waitername){
	this.waitername=waitername;
}
public int getWsalary(){
	return this.wsalary;
}
public void setWsalary(int wsalary){
	this.wsalary=wsalary;
}
public int getTableservicecount(){
	return this.tableservicecount;
}
public void setTableservicecount(int tableservicecount){
	this.tableservicecount=tableservicecount;
}


}
