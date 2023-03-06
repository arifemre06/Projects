
public class Items {
	private String name;
	private float cost;
	private int amount;
	private int alinanmiktar;
	
	public Items(String name,float cost,int amount){
		this.name=name;
		this.amount=amount;
		this.cost=cost;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public float getCost(){
		return this.cost;
	}
	public void setcost(float cost){
		this.cost=cost;
	}
	public int getAmount(){
		return this.amount;
	}
	public void setAmount(int amount){
		this.amount=amount;
	}
	public int getAlinanmiktar(){
		return this.alinanmiktar;
	}
	public void setAlinanmiktar(int alinanmiktar){
		this.alinanmiktar=alinanmiktar;
	}

}
