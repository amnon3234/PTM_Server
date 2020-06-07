package searcher;

import java.util.List;

import searchable.Searchable;
import searchable.State;

public class Dijkstra<T> extends CommonSearcher<T> {

	@Override
	public List<State<T>> search(Searchable<T> searchable) {
		
		// Initialize source
		this.minHeap.add(searchable.getInitialState());
		
		while(!this.minHeap.isEmpty()) {
			
			State<T> curr = super.pollFromHeap();
			
			// If the algorithm found the goal state
            if (searchable.isGoalState(curr))
            	return super.backTrace(curr);
			
            List<State<T>> successors = searchable.getAllPossibleStates(curr);
            for(State<T> successor : successors) {
            	if(!super.minHeap.contains(successor)) {
            		successor.setFather(curr);
            		super.minHeap.add(successor);
            	}else {
            		if(successor.getsCost() >= super.getCost(super.minHeap.iterator(), successor)) continue;
            		successor.setFather(curr);
            		super.minHeap.remove(successor);
            		super.minHeap.add(successor);
            	}
            }
		}
		
		// There is no path 
		return null;
	}
	
}