package ru.aryukov.webservice;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.springframework.beans.factory.annotation.Configurable;
import ru.aryukov.cache.CacheEngine;
import ru.aryukov.domain.UserEntity;

import java.io.IOException;

/**
 * Created by dev on 08.08.17.
 */
@Configurable
public class CacheInfoServlet {
    private final CacheEngine<Integer, UserEntity> userCache;

    public CacheInfoServlet(CacheEngine<Integer, UserEntity> userCache) {
        this.userCache = userCache;
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        System.out.println("gettig message:" + data);
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        sendData(session);
        System.out.println("onOpen");
    }

    private void sendData(Session session) {
        while(true) {
            try {
                final CacheInfo cacheInfo = new CacheInfo(userCache.getHitCount(), userCache.getMissCount());
                final String data = new Gson().toJson(cacheInfo);
                session.getRemote().sendString(data);
                System.out.println("Sending message:" + data);
                Thread.sleep(1000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        System.out.println("onClose");
    }

    class CacheInfo {
        private int hitCount;
        private int missCount;

        public CacheInfo() {
        }

        public CacheInfo(int hitCount, int missCount) {
            this.hitCount = hitCount;
            this.missCount = missCount;
        }

        public int getHitCount() {
            return hitCount;
        }

        public void setHitCount(int hitCount) {
            this.hitCount = hitCount;
        }

        public int getMissCount() {
            return missCount;
        }

        public void setMissCount(int missCount) {
            this.missCount = missCount;
        }
    }
}
