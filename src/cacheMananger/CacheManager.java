package cacheMananger;

public interface CacheManager<PType,SType> {
	void addToCache(PType problem, SType solution);
	SType allreadySolved(PType problem);
}
