package ru.aryukov.webservice;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.aryukov.dao.UserEntityDao;

/**
 * Created by dev on 07.08.17.
 */
public class Web {
    private static final int PORT = 8095;
    private static final String DEF_RESOURCES = "/HomeWork12/public_html";

    public static void main(String[] args) throws Exception {

        Server server = new Server(PORT);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setWelcomeFiles(new String[] {"index.html"});
        resourceHandler.setResourceBase("." + DEF_RESOURCES);
        UserEntityDao userEntityDao = new UserEntityDao();
        userEntityDao.startUp();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(LoginServlet.class, "/login");
        context.addServlet(new ServletHolder(new AdminServlet(userEntityDao.getCache())), "/admin");
        context.addServlet(new ServletHolder(new CacheInfoWebSocketServlet(userEntityDao.getCache())), "/getCacheInfo");

        //server.setHandler(resourceHandler);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }
}
