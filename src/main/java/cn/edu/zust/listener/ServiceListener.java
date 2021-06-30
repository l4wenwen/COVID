package cn.edu.zust.listener;

import cn.edu.zust.util.DBUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServiceListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String url = "jdbc:mysql://127.0.0.1:3306/covid";
        String userName = "root";
        String pwd = "root";
        DBUtil.openConnection(url, userName, pwd);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DBUtil.closeConnection();
    }
}
