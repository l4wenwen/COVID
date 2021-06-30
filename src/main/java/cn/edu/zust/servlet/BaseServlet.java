package cn.edu.zust.servlet;

import cn.edu.zust.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "userServlet", urlPatterns = "user/*", loadOnStartup = 1)
public class BaseServlet extends HttpServlet {
    UserService userService;
    @Override
    public void init() {
        ServletContext application = getServletContext();
        userService = (UserService) application.getAttribute("userService");
    }
}
