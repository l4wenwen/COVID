package cn.edu.zust.servlet;

import cn.edu.zust.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

public class BaseServlet extends HttpServlet {
    protected UserService userService;
    @Override
    public void init() {
        ServletContext application = getServletContext();
        userService = (UserService) application.getAttribute("userService");
    }
}
