package ru.aryukov.cache;

import ru.aryukov.domain.UserEntity;

/**
 * Created by dev on 11.08.17.
 */
public interface UserEntityCache {
    int getHitCount();
    int getMissCount();

    void put(long key, CacheElement<UserEntity> cacheElement);

    CacheElement<UserEntity> get(long key);
}
