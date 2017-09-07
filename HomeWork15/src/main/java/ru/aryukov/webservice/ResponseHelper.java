package ru.aryukov.webservice;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * Created by dev on 08.08.17.
 */
public class ResponseHelper {

    public void responseOK(HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void responseFORBIDDEN(HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    public void sleep(long timeInSec){
        try{
            Thread.sleep(TimeUnit.SECONDS.toMicros(timeInSec));
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
