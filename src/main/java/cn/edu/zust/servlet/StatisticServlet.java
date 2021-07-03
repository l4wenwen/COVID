package cn.edu.zust.servlet;

import cn.edu.zust.vo.Statistic;
import cn.edu.zust.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "statisticServlet", urlPatterns = "statistic/*")
public class StatisticServlet extends BaseServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void all(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Statistic statistic = statisticService.getAllStatistic(getDate(), getCurrentUser(request).getCollegeNum());
        request.setAttribute("statistic", statistic);
        request.getRequestDispatcher("/WEB-INF/userHome.jsp").forward(request, response);
    }

    public void filled(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        response.setCharacterEncoding("UTF-8");
        List<User> users = statisticService.getFilledUsers(getDate(), getCurrentUser(request).getCollegeNum());
        String json = convertUserToJSON(users);
        PrintWriter out = response.getWriter();
        out.print(json);
    }

    public void highrisk(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        response.setCharacterEncoding("UTF-8");
        List<User> users = statisticService.getHighRiskUsers(getDate(), getCurrentUser(request).getCollegeNum());
        String json = convertUserToJSON(users);
        PrintWriter out = response.getWriter();
        out.print(json);
    }

    public void riskarea(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        response.setCharacterEncoding("UTF-8");
        List<User> users = statisticService.getHighRiskAreaUsers(getDate(), getCurrentUser(request).getCollegeNum());
        String json = convertUserToJSON(users);
        PrintWriter out = response.getWriter();
        out.print(json);
    }

    private User getCurrentUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }
}
