package ru.aryukov.webservice;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.aryukov.dao.UserEntityDao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dev on 08.08.17.
 */
@Configurable
public class CacheInfoServlet extends HttpServlet {

    @Autowired
    private UserEntityDao userEntityDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        System.out.println("get request");
        if (request.getSession().getAttribute("login") != null) {

            final CacheInfo cachInfo = new CacheInfo(userEntityDao.getCache().getHitCount(),
                    userEntityDao.getCache().getMissCount());
            final String data = new Gson().toJson(cachInfo);
            System.out.println("Sending message:" + data);
            response.getWriter().println(data);
            new ResponseHelper().responseOK(response);
        } else {
            response.sendRedirect("/HW13/accessDenied.html");
            new ResponseHelper().responseFORBIDDEN(response);
        }
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
