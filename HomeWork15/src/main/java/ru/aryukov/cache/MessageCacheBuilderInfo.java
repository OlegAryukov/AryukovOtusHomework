package ru.aryukov.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aryukov.cache.daoCacheImpl.UserEntityCacheImpl;
import ru.aryukov.messageSystem.Address;

/**
 * Created by dev on 07.09.17.
 */

@Component
public class MessageCacheBuilderInfo {

    @Autowired
    private UserEntityCacheImpl userEntityCache;

    public MessageCacheInfo makeMessage(Address from){
        return new MessageCacheInfo(userEntityCache, from);
    }
}
