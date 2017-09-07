package ru.aryukov.cache.daoCacheImpl;

import org.springframework.stereotype.Component;
import ru.aryukov.cache.CacheElement;
import ru.aryukov.cache.CacheEngine;
import ru.aryukov.cache.UserCacheMessage;
import ru.aryukov.cache.cacheImpl.CacheEngineImpl;
import ru.aryukov.cache.cacheImpl.CacheInfo;
import ru.aryukov.dao.UserEntityDao;
import ru.aryukov.domain.UserEntity;
import ru.aryukov.messageSystem.Address;

import java.util.concurrent.TimeUnit;

/**
 * Created by dev on 11.08.17.
 */

@Component
public class UserEntityCacheImpl implements UserCacheMessage {

    private static final String ADDRESS_NAME="CacheEngine";
    public static final int MAX_ELEMENTS = 300;
    public static final long LIFE_TIMES_M = 10;
    public static final long IDLE_TIME_M = 10;

    /*@Autowired
    private MessageSystem messageSystem;*/

    private CacheEngine<Long, UserEntityDao> userCache;
    private Address address;

    public UserEntityCacheImpl() {
        userCache = new CacheEngineImpl<>(MAX_ELEMENTS,
                TimeUnit.MINUTES.toMillis(LIFE_TIMES_M),
                TimeUnit.MINUTES.toMillis(IDLE_TIME_M),
                false);

        address = new Address(ADDRESS_NAME);
    }

    @Override
    public void put(long key, CacheElement<UserEntity> cacheElement) {

    }

    @Override
    public CacheElement<UserEntity> get(long key) {
        return null;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public CacheInfo getCacheInfo() {
        return userCache.getCahCacheInfo();
    }
}
