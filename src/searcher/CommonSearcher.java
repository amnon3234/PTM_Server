package searcher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import searchable.Searchable;
import searchable.State;

public abstract class CommonSearcher<T> implements Searcher<T> {
	//Data
	protected PriorityQueue<State<T>> minHeap;
	private int numberOfNodes;
	
	//Constructor
	public CommonSearcher() {
		this.minHeap = new PriorityQueue<>((s1, s2) -> { return Integer.compare(s1.getsCost(), s2.getsCost());});
		this.numberOfNodes = 0;
	}
	
	//Protected methods
	protected final State<T> pollFromHeap(){
		this.numberOfNodes++;
		return this.minHeap.poll();
	}
	
	protected List<State<T>> backTrace(State<T> goal) {
		Stack<State<T>> stack = new Stack<>();
		List<State<T>> sList = new ArrayList<>();
		
		while(goal.getFather() != null) { //Check if we reached start position
			stack.push(goal);
			goal = goal.getFather();
		}
		stack.push(goal);//Adding the start position
		while(!stack.isEmpty())//while stack is not empty
			sList.add(stack.pop());
		
		this.minHeap.clear();
		return sList;
	}
	
	protected int getCost(Iterator<State<T>> it, State<T> state ) {
		while(it.hasNext()) {
			State<T> temp = it.next();
			if(temp.equals(state))
				return temp.getsCost();
		}
		return 0;
	}
	
	//Override
	@Override
	public abstract List<State<T>> search(Searchable<T> searchable);

	@Override
	public final int getNumberOfNodes() { return this.numberOfNodes; }

}
