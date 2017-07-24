package ru.aryukov.cache;

/**
 * Created by oaryukov on 23.07.2017.
 */
public interface CacheEngine<K, V> {

    void put(K key, CacheElement<V> cacheElement);

    CacheElement<V> get(K key);

    int getHitCount();

    int getMissCount();

    void dispose();
}
