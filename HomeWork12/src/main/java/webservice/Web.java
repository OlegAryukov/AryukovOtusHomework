package webservice;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * Created by dev on 07.08.17.
 */
public class Web {
    private static final int PORT = 8989;
    private static final String DEF_RESOURCES = "public_html";

    public static void main(String[] args) throws Exception {

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(DEF_RESOURCES);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }
}
