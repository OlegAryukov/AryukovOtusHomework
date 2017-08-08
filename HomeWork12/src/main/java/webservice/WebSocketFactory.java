package webservice;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import ru.aryukov.cache.CacheEngine;
import ru.aryukov.dao.UserEntityDao;
import ru.aryukov.domain.UserEntity;

/**
 * Created by dev on 08.08.17.
 */
public class WebSocketFactory implements WebSocketCreator{

    private final CacheEngine<Integer, UserEntity> userCache;
    public WebSocketFactory(CacheEngine<Integer, UserEntity> userCache) {
        this.userCache = userCache;
        System.out.println("WebSocketCreator created");
    }


    @Override
    public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
        CacheInfoSocket socket = new CacheInfoSocket(userCache);
        System.out.println("Socket created");
        return socket;
    }
}
