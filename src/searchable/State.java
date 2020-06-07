package searchable;

public class State<T>{
	//Data
	private T sValue;
	private int sCost;
	private State<T> father;
	
	//Constructor
	public State(T sValue) {
		this.sValue = sValue;
		this.sCost = Integer.MAX_VALUE;
		this.father = null;
	}
	
	//Getters
	public State<T> getFather() { return father; }
	public int getsCost() { return sCost; }
	public T getsValue() { return sValue; }
	
	//Setters
	public void setFather(State<T> father) { this.father = father; }
	public void setsCost(int sCost) { this.sCost = sCost; }
	public void setsValue(T sValue) { this.sValue = sValue;	}
	
	//Equals method
	public boolean equals(State<T> state) {
		return sValue.equals(state.sValue);
	}
	
	//Override
	@Override
	public int hashCode() {
		return (this.sValue + "").hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.equals((State<T>) obj);
	}
	
	@Override
	public String toString() {
		return this.sValue + " ";
	}
}
