package ru.aryukov.cache;

import ru.aryukov.cache.cacheImpl.CacheInfo;

/**
 * Created by oaryukov on 23.07.2017.
 */
public interface CacheEngine<K, V> {

    void put(K key, CacheElement<V> cacheElement);

    CacheElement<V> get(K key);

    int getHitCount();

    int getMissCount();

    void dispose();

    CacheInfo getCahCacheInfo();
}
