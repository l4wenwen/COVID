package cn.edu.zust.servlet;

import cn.edu.zust.service.StateService;
import cn.edu.zust.service.StatisticService;
import cn.edu.zust.service.UserService;
import cn.edu.zust.service.VacationService;
import cn.edu.zust.vo.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class BaseServlet extends HttpServlet {
    protected UserService userService;
    protected VacationService vacationService;
    protected StateService stateService;
    protected StatisticService statisticService;
    @Override
    public void init() {
        ServletContext application = getServletContext();
        userService = (UserService) application.getAttribute("userService");
        vacationService = (VacationService) application.getAttribute("vacationService");
        stateService = (StateService) application.getAttribute("stateService");
        statisticService = (StatisticService) application.getAttribute("statisticService");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doPost调用方法
        request.setCharacterEncoding("UTF-8");
        String methodName = getMethod(request.getRequestURI());
        try {
            Method method = getClass().getDeclaredMethod(methodName,
                    HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

    /**
     * @return 当前时间
     */
    protected String getTime() {
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strDateFormat);
        return simpleDateFormat.format(date);
    }

    protected String getDate() {
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strDateFormat);
        return simpleDateFormat.format(date);
    }

    protected boolean isGoodString(String str) {
        return str != null && !"".equals(str.trim());
    }

    protected String convertUserToJSON(List<User> users) {
        StringBuilder json = new StringBuilder("[");
        for(User user : users) {
            json.append("{\"userName\": \"").append(user.getUserName()).append("\", \"userNum\": \"").append(user.getUserNum()).append("\", \"sex\": \"").append(user.isSex() ? "男" : "女").append("\"},");
        }
        json.delete(json.lastIndexOf(","), json.lastIndexOf(",")+1);
        json.append("]");
        return json.toString();
    }

    //获取URI中使用的方法
    protected String getMethod(String uri) {
        int pos = uri.lastIndexOf('/');
        String method = "error";
        if (pos != -1) {
            method = uri.substring(pos+1);
        }
        return method;
    }
}
