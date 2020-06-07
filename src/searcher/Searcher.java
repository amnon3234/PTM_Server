package searcher;

import java.util.List;

import searchable.Searchable;
import searchable.State;

public interface Searcher<T> {
	List<State<T>> search(Searchable<T> searchable);
	int getNumberOfNodes();
}
