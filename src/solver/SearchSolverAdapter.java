package solver;

import searchable.Searchable;
import searcher.Searcher;

public class SearchSolverAdapter<Problem,Solution,SType> implements Solver<Problem,Solution>{
	//Data
	private Searcher<SType> searcher;
	
	//Constructor
	public SearchSolverAdapter(Searcher<SType> searcher) {
		this.searcher = searcher;
	}
		
	@Override
	public Solution solve(Problem p) {
		return (Solution) this.searcher.search((Searchable<SType>) p);
	}
	
}
