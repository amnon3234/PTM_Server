package searcher;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import searchable.Searchable;
import searchable.State;

public class BestFirstSearch<T> extends CommonSearcher<T> {

	//Override
	@Override
	public List<State<T>> search(Searchable<T> searchable) {
		LinkedHashSet<State<T>> visitedSet = new LinkedHashSet<>(); // A set of states already evaluated
		super.minHeap.add(searchable.getInitialState()); // A priority queue of states to be evaluated
		
		while(!super.minHeap.isEmpty()) {
			State<T> curr = super.pollFromHeap();
			visitedSet.add(curr);
			if(searchable.isGoalState(curr))
				return super.backTrace(curr);
			List<State<T>> successors = searchable.getAllPossibleStates(curr);
			for(State<T> successor : successors) {
				if (!super.minHeap.contains(successor) && !visitedSet.contains(successor)) {
					successor.setFather(curr);
					super.minHeap.add(successor);
				} else if (!visitedSet.contains(successor) && successor.getsCost() < super.getCost(super.minHeap.iterator(), successor)) {
					super.minHeap.remove(successor);
					super.minHeap.add(successor);
				}
				if(successor.getFather() == null)
					successor.setFather(curr);
			}
		}
		return null;
	}
}
