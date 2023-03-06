
public class Property extends GameBoard{
protected int cost;
protected String name;
protected boolean haveplayer1=false;
protected boolean haveplayer2=false;
public int getCost() {
	return cost;
}
public void setCost(int cost) {
	this.cost = cost;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
}

