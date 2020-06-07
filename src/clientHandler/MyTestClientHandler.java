package clientHandler;

import java.io.*;

import cacheMananger.CacheManager;
import cacheMananger.FileCacheManager;
import solver.Solver;

public class MyTestClientHandler implements ClientHandler {
	//Data
	private Solver<String,String> solver;
	private CacheManager<String,String> cm;
	
	//Constructor
	public MyTestClientHandler() {
		this.solver = new Solver<String,String>() { //Create new solver which will reverse a given string
			@Override
			public String solve(String p) {
				StringBuilder solution = new StringBuilder();
				return solution.append(p).reverse().toString();
			}};
		this.cm = new FileCacheManager<>();
	}
	
	//Getters
	public CacheManager<String,String> getCm() { return cm; }
	public Solver<String,String> getSolver() {	return solver; }
	//Setters
	public void setCm(CacheManager<String,String> cm) { this.cm = cm; }
	public void setSolver(Solver<String,String> solver) { this.solver = solver; }
	
	//Override
	@Override
	public void handleClient(InputStream in, OutputStream out) throws IOException{
		//throws IOEexception in case the buffer count read from the client
		BufferedReader inFromAClient = new BufferedReader(new InputStreamReader(in));
		PrintWriter outToAClient = new PrintWriter(out);
		
		String problem;	
		while(!(problem = inFromAClient.readLine()).equals("end")) { // Reading until "end" 
			String solution = cm.allreadySolved(problem); // If Already solved return the result else null 
			if(solution == null) { // If the cache does not possess the solution
				solution = solver.solve(problem);//Solve
				cm.addToCache(problem , solution);// Add the new solution to the cache
			}
			outToAClient.println(solution);//send to client
			outToAClient.flush();
		}
		inFromAClient.close();
		outToAClient.close();

	}

}
