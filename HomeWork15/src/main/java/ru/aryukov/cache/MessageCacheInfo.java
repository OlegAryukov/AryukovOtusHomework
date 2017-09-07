package ru.aryukov.cache;

import ru.aryukov.cache.daoCacheImpl.UserEntityCacheImpl;
import ru.aryukov.messageSystem.Address;
import ru.aryukov.messageSystem.Message;
import ru.aryukov.messageSystem.MessageResponse;

/**
 * Created by dev on 07.09.17.
 */
public class MessageCacheInfo extends Message {

    private UserEntityCacheImpl entityCache;

    public MessageCacheInfo(UserEntityCacheImpl entityCache, Address from) {
        this.entityCache = entityCache;
        super.setTo(entityCache.getAddress());
        super.setFrom(from);
    }

    @Override
    public MessageResponse exec() {
        return new MessageResponse<>(entityCache.getCacheInfo());
    }
}
