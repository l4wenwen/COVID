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
        try {
            Method method = getClass().getDeclaredMethod(methodName,
                    HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            methodName = "error";
        }
        request.getRequestDispatcher("/WEB-INF/" + methodName + ".jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doPost调用方法
        String methodName = getMethod(request.getRequestURI());
        try {
            Method method = getClass().getDeclaredMethod(methodName,
                    HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String userNum = request.getParameter("account");
        String password = request.getParameter("password");
        String message = "", directURI = "/WEB-INF/";
        User user = null;
        if (userNum == null || password == null || "".equals(userNum.trim()) || "".equals(password.trim()))
            message = "账号密码不能为空。";
        else {
            user = userService.userLogin(userNum, password);
            if (user == null) message = "账号密码错误。";
        }
        if (user == null) {
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/index.jsp");
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String userNum = request.getParameter("account");
        String password = request.getParameter("password");
        String message = "", directURI = "/WEB-INF/";
        boolean isRegistered = false;
        if (userNum == null || password == null || "".equals(userNum.trim()) || "".equals(password.trim()))
            message = "账号密码不能为空。";
        else {
            isRegistered = userService.userRegister(userNum, password);
            if (!isRegistered) message = "账号已存在。";
        }

        if (isRegistered) directURI += "login.jsp";
        else {
            request.setAttribute("message", message);
            directURI += "register.jsp";
        }
        request.getRequestDispatcher(directURI).forward(request, response);
    }
}
