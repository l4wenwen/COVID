package cn.edu.zust.servlet;

import cn.edu.zust.vo.Statistic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "statisticServlet", urlPatterns = "statistic/*")
public class StatisticServlet extends BaseServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void all(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Statistic statistic = statisticService.getAllStatistic(getDate());
        request.setAttribute("statistic", statistic);
        request.getRequestDispatcher("/WEB-INF/adminHome.jsp").forward(request, response);
    }
}
