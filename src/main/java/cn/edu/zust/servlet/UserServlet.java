package cn.edu.zust.servlet;

import cn.edu.zust.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

@WebServlet(name = "userServlet", urlPatterns = "user/*", loadOnStartup = 1)
public class UserServlet extends BaseServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doGet跳转至相应界面
        String methodName = getMethod(request.getRequestURI());
        request.getRequestDispatcher("/WEB-INF/" + methodName + ".jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        //doPost调用方法
        String methodName = getMethod(request.getRequestURI());
        try {
            Method method = getClass().getDeclaredMethod(methodName,
                    HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String loginName = request.getParameter("account");
        String password = request.getParameter("password");
        String message = "";
        if (loginName == null || password == null || "".equals(loginName.trim()) || "".equals(password.trim())) {
            message = "账号密码错误。";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            User user = userService.userLogin(loginName, password);
            request.getSession().setAttribute("user", user);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) {

    }

    //获取URI中使用的方法
    private String getMethod(String uri) {
        int pos = uri.lastIndexOf('/');
        String method = "error";
        if (pos != -1)
            method = uri.substring(pos+1);
        return method;
    }
}
