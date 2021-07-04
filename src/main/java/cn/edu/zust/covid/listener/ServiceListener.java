package cn.edu.zust.covid.listener;

import cn.edu.zust.covid.service.StateService;
import cn.edu.zust.covid.service.StatisticService;
import cn.edu.zust.covid.service.UserService;
import cn.edu.zust.covid.service.VacationService;
import cn.edu.zust.covid.util.DBUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServiceListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String url = "jdbc:mysql://localhost:3306/COVID?allowPublicKeyRetrieval=true";
        String userName = "root";
        String pwd = "root";
        DBUtil.openConnection(url, userName, pwd);
        UserService userService = new UserService();
        VacationService vacationService = new VacationService();
        StateService stateService = new StateService();
        StatisticService statisticService = new StatisticService();
        servletContextEvent.getServletContext().setAttribute("userService", userService);
        servletContextEvent.getServletContext().setAttribute("vacationService", vacationService);
        servletContextEvent.getServletContext().setAttribute("stateService", stateService);
        servletContextEvent.getServletContext().setAttribute("statisticService", statisticService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DBUtil.closeConnection();
    }
}
