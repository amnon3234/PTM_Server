package cacheMananger;

import java.util.*;

public class FileCacheManager<PType,SType> implements CacheManager<PType,SType> {
	//Data
	Map<PType,SType> cache;
	
	//Constructor
	public FileCacheManager() {
		this.cache = new HashMap<>();
	}
	
	//Override
	@Override
	public SType allreadySolved(PType problem) {
		return this.cache.get(problem); // Return the solution or NULL if not found
	}

	@Override
	public void addToCache(PType problem, SType solution) {
		this.cache.put(problem, solution);// Add the solution to the cache
	}

	

}
