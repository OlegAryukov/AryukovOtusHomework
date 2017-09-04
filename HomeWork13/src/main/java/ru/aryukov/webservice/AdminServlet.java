package ru.aryukov.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.aryukov.cache.UserEntityCache;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dev on 08.08.17.
 */
@Configurable
public class AdminServlet extends HttpServlet {
    private static final String ADMIN_PAGE_TEMPLATE = "adminPage.html";

    @Autowired
    private UserEntityCache userEntityCache;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        System.out.println("get request");

        if (request.getSession().getAttribute("login") != null) {
            response.getWriter().println(getPage(request));
            new ResponseHelper().responseOK(response);
        } else {
            response.sendRedirect("/accessDenied.html");
            new ResponseHelper().responseFORBIDDEN(response);
        }
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("locale", request.getLocale());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());

        pageVariables.put("hitCount", userEntityCache.getHitCount());
        pageVariables.put("missCount", userEntityCache.getMissCount());


        //let's get login from session
        String login = (String) request.getSession().getAttribute(LoginServlet.LOGIN_PARAMETER_NAME);
        pageVariables.put("login", login);

        return pageVariables;
    }

    private String getPage(HttpServletRequest request) throws IOException {
        final Map<String, Object> pageVariables = createPageVariablesMap(request);
        return TemplateProcessor.instance().getPage(ADMIN_PAGE_TEMPLATE, pageVariables);
    }

}
