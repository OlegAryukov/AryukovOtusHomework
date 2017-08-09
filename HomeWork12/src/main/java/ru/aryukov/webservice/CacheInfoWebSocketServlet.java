package ru.aryukov.webservice;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import ru.aryukov.cache.CacheEngine;
import ru.aryukov.domain.UserEntity;

/**
 * Created by dev on 08.08.17.
 */
public class CacheInfoWebSocketServlet extends WebSocketServlet {

    private final static int LOGOUT_TIME = 10 * 60 * 1000;
    private final CacheEngine<Integer, UserEntity> userCache;

    public CacheInfoWebSocketServlet(CacheEngine<Integer, UserEntity> userCache) {
        this.userCache = userCache;
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator(new WebSocketFactory(userCache));
    }
}
