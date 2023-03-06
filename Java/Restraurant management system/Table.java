
public class Table {
	private String tename;
	private int capacity;
	private int id;
	
	private float price;
	private int maxorder=0;
	private boolean inservice = false;
	private String checkoutl=null;
	private String order=null;
	private boolean haswaiter=false;
    private String tablewaiter;
	
	public Table(String tename,int capacity){
		this.tename=tename;
		this.capacity=capacity;
	}
	
	public String getTename(){
		return this.tename;
	}
	public void setTename(String tename){
		this.tename=tename;
	}
	public int getCapacity(){
		return this.capacity;
	}
	public void setCapacity(int capacity){
		this.capacity=capacity;
	}
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id=id;
	}
	public float getPrice(){
		return this.price;
	}
	public void setPrice(float f){
		this.price=f;
	}
	public int getMaxorder(){
		return this.maxorder;
	}
	public void setMaxorder(int maxorder){
		this.maxorder=maxorder;
	}
	public boolean getInservice(){
		return this.inservice;
	}
	public void setInservice(boolean inservice){
		this.inservice=inservice;
	}
	public String getCheckoutl(){
		return this.checkoutl;
	}
	public void setCheckoutl(String checkoutl){
		this.checkoutl=checkoutl;
	}
	public boolean getHaswaiter(){
		return this.haswaiter;
	}
	public void setHaswaiter(boolean haswaiter){
		this.haswaiter=haswaiter;
	}
	public String getTablewaiter(){
		return this.tablewaiter;
	}
	public void setTablewaiter(String tablewaiter){
		this.tablewaiter=tablewaiter;
	}
	public String getOrder(){
		return this.order;
	}
	public void setOrder(String order){
		this.order=order;
	}
}
