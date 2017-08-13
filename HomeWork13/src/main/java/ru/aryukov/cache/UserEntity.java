package ru.aryukov.cache;

/**
 * Created by dev on 11.08.17.
 */
public interface UserEntity {
    int getHitCount();
    int getMissCount();

    void put(long key, CacheElement<UserEntity> cacheElement);

    CacheElement<UserEntity> get(long key);
}
