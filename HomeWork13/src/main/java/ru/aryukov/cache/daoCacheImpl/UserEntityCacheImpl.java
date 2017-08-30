package ru.aryukov.cache.daoCacheImpl;

import ru.aryukov.cache.CacheElement;
import ru.aryukov.cache.CacheEngine;
import ru.aryukov.cache.UserEntityCache;
import ru.aryukov.cache.cacheImpl.CacheEngineImpl;
import ru.aryukov.dao.UserEntityDao;
import ru.aryukov.domain.UserEntity;

import java.util.concurrent.TimeUnit;

/**
 * Created by dev on 11.08.17.
 */
public class UserEntityCacheImpl implements UserEntityCache {

    public static final int MAX_ELEMENTS = 300;
    public static final long LIFE_TIMES_M = 10;
    public static final long IDLE_TIME_M = 10;

    private CacheEngine<Long, UserEntityDao> userCache;

    public UserEntityCacheImpl() {
        userCache = new CacheEngineImpl<>(MAX_ELEMENTS,
                TimeUnit.MINUTES.toMillis(LIFE_TIMES_M),
                TimeUnit.MINUTES.toMillis(IDLE_TIME_M),
                false);
    }

    @Override
    public int getHitCount() {
        return userCache.getHitCount();
    }

    @Override
    public int getMissCount() {
        return userCache.getMissCount();
    }

    @Override
    public void put(long key, CacheElement<UserEntity> cacheElement) {

    }

    @Override
    public CacheElement<UserEntity> get(long key) {
        return null;
    }
}
