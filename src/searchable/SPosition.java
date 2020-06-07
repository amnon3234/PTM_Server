package searchable;

public class SPosition {
	//Data
	private int row , col;
	
	//Constructor
	public SPosition(int row , int col) {
		this.row = row;
		this.col = col;
	}
	
	//Getters
	public int getCol() { return col; }
	public int getRow() { return row; }
	
	//Setters
	public void setCol(int col) { this.col = col; }
	public void setRow(int row) { this.row = row; }
	
	//Equals method
	public boolean equals(SPosition sp) {
		return (sp.col == this.col && sp.row == this.row) ? true : false ;
	}
	
	//Override
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.equals((SPosition) obj);
	}
	
	@Override
	public String toString() {
		return "( " + this.row + ", "+ this.col + ")";
	}
}
