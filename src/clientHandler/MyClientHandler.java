package clientHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cacheMananger.CacheManager;
import cacheMananger.FileCacheManager;
import searchable.FindGoalInMatrix;
import searchable.SPosition;
import searchable.Searchable;
import searchable.State;
import searcher.BestFirstSearch;
import solver.SearchSolverAdapter;

public class MyClientHandler implements ClientHandler {
	//Data
	private SearchSolverAdapter<Searchable<SPosition>,List<State<SPosition>>,SPosition> solver;
	private CacheManager<FindGoalInMatrix,String> cm;
	
	//Constructor
	public MyClientHandler() {
		this.solver = new SearchSolverAdapter<>(new BestFirstSearch<SPosition>());
		this.cm = new FileCacheManager<FindGoalInMatrix, String>();
	}
	
	//Private Methods
	private String createSolution(List<State<SPosition>> solverResult) {
		StringBuilder solution = new StringBuilder();
		State<SPosition> current , next;
		int rowR , colR;
		
		for(int i = 0 ; i< solverResult.size()-1 ;i++) {
			current = solverResult.get(i);
			next = solverResult.get(i+1);
			rowR = current.getsValue().getRow() - next.getsValue().getRow();
			colR = current.getsValue().getCol()	- next.getsValue().getCol();
			if(rowR == 1)//up
				solution.append("Up");
			else if(rowR == -1)//down
				solution.append("Down");
			else if(colR == 1)//left
				solution.append("Left");
			else if(colR == -1)//right
				solution.append("Right");
			if(i+1 != solverResult.size() - 1)
				solution.append(",");
		}
		return solution.toString();
	}
	
	//Override
	@Override
	public void handleClient(InputStream in, OutputStream out) throws IOException {
		BufferedReader inFromAClient = new BufferedReader(new InputStreamReader(in));
		PrintWriter outToAClient = new PrintWriter(out);
		List<int[]> matrix = new ArrayList<>();
		
		String line;
		//Getting the first wave of information from the client (The Matrix)
		while(!(line = inFromAClient.readLine()).equals("end"))
			matrix.add(Arrays.asList(line.split(",")).stream().mapToInt(Integer::parseInt).toArray());
		
		//Getting the second wave of information from the client (start position)
		line = inFromAClient.readLine();
		int[] startArray = Arrays.asList(line.split(",")).stream().mapToInt(Integer::parseInt).toArray();
		SPosition start = new SPosition(startArray[0], startArray[1]);
		
		//Getting the third wave of information from the client (goal position)
		line = inFromAClient.readLine();
		int[] goalArr = Arrays.asList(line.split(",")).stream().mapToInt(Integer::parseInt).toArray();
		SPosition goal = new SPosition(goalArr[0],goalArr[1]);
		
		//Building the problem
		FindGoalInMatrix problem = new FindGoalInMatrix(matrix, start, goal);
		
    	//Solving if needed
		String solution = this.cm.allreadySolved(problem);
		if(solution == null) { 
			List<State<SPosition>> solverResult = this.solver.solve(problem);
		    solution = createSolution(solverResult);
		    cm.addToCache(problem, solution);
		}
				
		//Sending solution to client
		outToAClient.println(solution);
		outToAClient.flush();
		
		//Close
		inFromAClient.close();
		outToAClient.close();
	}

	

}
