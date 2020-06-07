package searchable;

import java.util.ArrayList;
import java.util.List;

public class FindGoalInHeightMatrix extends FindGoalInMatrix{
    // Constructor
    public FindGoalInHeightMatrix(int[][] matrix, SPosition start, SPosition goal) {
        super(matrix, start, goal);
    }
    public FindGoalInHeightMatrix(List<int[]> matrix, SPosition start, SPosition goal) {
        super(matrix, start, goal);
    }

    /**
     * Generate list of neighbors by using the height matrix values
     * Difference from FindGoalInMatrix: state cost will calculate by the absolute values differences
     * @param cState current state
     * @return list of neighbors
     */
    @Override
    public List<State<SPosition>> getAllPossibleStates(State<SPosition> cState) {
        SPosition currentPosition = cState.getsValue();
        int currentRow = currentPosition.getRow();
        int currentCol =  currentPosition.getCol();
        int currMatrixValue = super.matrix[currentRow][currentCol];

        List<State<SPosition>> result = new ArrayList<State<SPosition>>();
        State<SPosition> temp;
        if(currentRow != 0) { // Check up overflow
            int valueToAdd = cState.getsCost() + Math.abs(currMatrixValue - super.matrix[currentRow - 1][currentCol]);
            temp = new State<SPosition>(new SPosition(currentRow - 1,currentCol));
            temp.setsCost(valueToAdd + 10);
            temp.setFather(null);
            result.add(temp);
        }
        if(currentRow != rowAmount-1) {// Check down overflow
            int valueToAdd = cState.getsCost() + Math.abs(currMatrixValue - super.matrix[currentRow + 1][currentCol]);
            temp = new State<SPosition>(new SPosition(currentRow + 1,currentCol));
            temp.setsCost(valueToAdd + 10);
            temp.setFather(null);
            result.add(temp);
        }
        if(currentCol != 0) {// Check left overflow
            int valueToAdd = cState.getsCost() + Math.abs(currMatrixValue - super.matrix[currentRow][currentCol - 1]);
            temp = new State<SPosition>(new SPosition(currentRow,currentCol - 1));
            temp.setsCost(valueToAdd + 10);
            temp.setFather(null);
            result.add(temp);
        }
        if(currentCol != colAmount-1) {// Check right overflow
            int valueToAdd = cState.getsCost() + Math.abs(currMatrixValue - super.matrix[currentRow][currentCol + 1]);
            temp = new State<SPosition>(new SPosition(currentRow,currentCol + 1));
            temp.setsCost(valueToAdd + 10);
            temp.setFather(null);
            result.add(temp);
        }

        return result;
    }
}
