package ru.aryukov.webservice;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.aryukov.cache.MessageCacheBuilderInfo;
import ru.aryukov.cache.cacheImpl.CacheInfo;
import ru.aryukov.dao.UserEntityDao;
import ru.aryukov.messageSystem.Address;
import ru.aryukov.messageSystem.MessageResponse;
import ru.aryukov.messageSystem.MessageSystem;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by dev on 08.08.17.
 */
@Configurable
public class CacheInfoServlet extends HttpServlet {

    @Autowired
    private MessageCacheBuilderInfo messageCacheBuilderInfo;

    @Autowired
    private MessageSystem messageSystem;

    private Address address;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        address = new Address("CacheInfoServlet");
        System.out.println("Address: " + address + " create at " + new Date().toString());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        System.out.println("get request");
        ResponseHelper rh = new ResponseHelper();

        if (request.getSession().getAttribute("login") != null) {
            System.out.println("Invoke message system");

            messageSystem.sendMessage(messageCacheBuilderInfo.makeMessage(address));
            MessageResponse<CacheInfo> messageResponse = messageSystem.getResponse(address);
             while (messageResponse == null){
                 System.out.println(" wait a response ");
                 rh.sleep(1);
                 messageResponse = messageSystem.getResponse(address);
             }

            final String data = new Gson().toJson(messageResponse.getValue());
            System.out.println("Sending message:" + data);
            response.getWriter().println(data);
            new ResponseHelper().responseOK(response);
        } else {
            response.sendRedirect("/HW15/accessDenied.html");
            new ResponseHelper().responseFORBIDDEN(response);
        }
    }
}
