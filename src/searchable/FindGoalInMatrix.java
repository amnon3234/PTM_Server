package searchable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindGoalInMatrix implements Searchable<SPosition> {
	//Data
	private int[][] matrix;
    private SPosition start;
    private SPosition goal;
    private int rowAmount, colAmount;
   
    //Constructor
    public FindGoalInMatrix(List<int[]> matrix, SPosition start, SPosition goal) {
        this.matrix = this.ListToMat(matrix);
        this.start = start;
        this.goal = goal;
    }
    
    //Private Methods
    private int[][] ListToMat(List<int[]> matrix){
        this.rowAmount = matrix.size() ;
        this.colAmount = matrix.get(0).length;
        int[][] result = new int[this.rowAmount][this.colAmount];
        for(int i = 0; i < this.rowAmount; i++) {
            for(int j = 0; j< this.colAmount ; j++)
            	result[i][j] = matrix.get(i)[j];        
        }
        return result;    
    }
    
    //Override
    @Override
    public State<SPosition> getInitialState() {
    	State<SPosition> start = new State<SPosition>(this.start);
    	start.setsCost(0);
    	start.setFather(null);
    	return start;
    }
 
    @Override
    public boolean isGoalState(State<SPosition> state) {
        return state.getsValue().equals(this.goal);
    }
 
    @Override
    public List<State<SPosition>> getAllPossibleStates(State<SPosition> cState) {
        SPosition currentPosition = cState.getsValue();
        int currentRow = currentPosition.getRow();
        int currentCol =  currentPosition.getCol();
       
        List<State<SPosition>> result = new ArrayList<State<SPosition>>();
        State<SPosition> temp;
        if(currentRow != 0) { // Check up overflow
        	temp = new State<SPosition>(new SPosition(currentRow - 1,currentCol));
        	temp.setsCost(cState.getsCost() + this.matrix[currentRow - 1][currentCol]);
        	temp.setFather(null);
        	result.add(temp);
        }
        if(currentRow != rowAmount-1) {// Check down overflow
            temp = new State<SPosition>(new SPosition(currentRow + 1,currentCol));
        	temp.setsCost(cState.getsCost() + this.matrix[currentRow + 1][currentCol]);
        	temp.setFather(null);
        	result.add(temp);
        }
        if(currentCol != 0) {// Check left overflow
            temp = new State<SPosition>(new SPosition(currentRow,currentCol - 1));
        	temp.setsCost(cState.getsCost() + this.matrix[currentRow][currentCol - 1]);
        	temp.setFather(null);
        	result.add(temp);
        }
        if(currentCol != colAmount-1) {// Check right overflow
        	temp = new State<SPosition>(new SPosition(currentRow,currentCol + 1));
        	temp.setsCost(cState.getsCost() + this.matrix[currentRow][currentCol + 1]);
        	temp.setFather(null);
        	result.add(temp);
        }
       
        return result;
    }
  
    //Override
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + colAmount;
		result = prime * result + ((goal == null) ? 0 : goal.hashCode());
		result = prime * result + Arrays.deepHashCode(matrix);
		result = prime * result + rowAmount;
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}  
    
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FindGoalInMatrix other = (FindGoalInMatrix) obj;
		if (colAmount != other.colAmount)
			return false;
		if (goal == null) {
			if (other.goal != null)
				return false;
		} else if (!goal.equals(other.goal))
			return false;
		if (!Arrays.deepEquals(matrix, other.matrix))
			return false;
		if (rowAmount != other.rowAmount)
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}
}
