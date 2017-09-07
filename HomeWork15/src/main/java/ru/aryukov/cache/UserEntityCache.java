package ru.aryukov.cache;

import ru.aryukov.cache.cacheImpl.CacheInfo;
import ru.aryukov.domain.UserEntity;

/**
 * Created by dev on 11.08.17.
 */
public interface UserEntityCache {

    void put(long key, CacheElement<UserEntity> cacheElement);

    CacheElement<UserEntity> get(long key);

    CacheInfo getCacheInfo();
}
