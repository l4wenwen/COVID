package cn.edu.zust.servlet;

import cn.edu.zust.vo.State;
import cn.edu.zust.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

@WebServlet(name = "stateServlet", urlPatterns = "state/*", loadOnStartup = 1)
public class StateServlet extends BaseServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName = getMethod(request.getRequestURI());
        if ("addState".equals(methodName) || "selectState".equals(methodName)
                || "updateState".equals(methodName) || "delState".equals(methodName)) {
            doPost(request, response);
        } else {
            try {
                Method method = getClass().getDeclaredMethod(methodName,
                        HttpServletRequest.class, HttpServletResponse.class);
            } catch (NoSuchMethodException e) {
                methodName = "error";
            }
            request.getRequestDispatcher("/WEB-INF/" + methodName + ".jsp").forward(request, response);
        }
    }

    public void selectState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String collegeName = request.getParameter("collegeName");
        if (!isGoodString(startTime) || !isGoodString(endTime) || !isGoodString(collegeName)) {
            request.setAttribute("message", "输入不能为空。");
            request.getRequestDispatcher("/WEB-INF/stateSelect.jsp").forward(request, response);
            return;
        }
        User user = (User) request.getSession().getAttribute("user");
        stateService.selectState(startTime, endTime, collegeName, user.getUserType(), user.getUserNum());
        response.sendRedirect("/state/stateList");
    }

    public void delState(HttpServletRequest request, HttpServletResponse response) {

    }

    public void updateState(HttpServletRequest request, HttpServletResponse response) {

    }

    public void addState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer stateNum = Integer.parseInt(request.getParameter("stateNum"));
        String userNum = request.getParameter("userNum");
        String stateTime = getTime();
        boolean isTemperature = Boolean.parseBoolean(request.getParameter("isTemperature"));
        boolean isCovid = Boolean.parseBoolean(request.getParameter("isCovid"));
        boolean isLikeCovid = Boolean.parseBoolean(request.getParameter("isLikeCovid"));
        Integer quarantine = Integer.parseInt(request.getParameter("quarantine"));
        boolean isRecentArea = Boolean.parseBoolean(request.getParameter("isRecentArea"));
        boolean isRecentCountry = Boolean.parseBoolean(request.getParameter("isRecentCountry"));
        boolean isRecentPeople = Boolean.parseBoolean(request.getParameter("isRecentPeople"));
        boolean symptom = Boolean.parseBoolean(request.getParameter("symptom"));
        boolean isAbnormal = Boolean.parseBoolean(request.getParameter("isAbnormal"));
        Integer healthCodeType = Integer.parseInt(request.getParameter("healthCodeType"));
        boolean isOutSchool = Boolean.parseBoolean(request.getParameter("isOutSchool"));
        boolean isOutCity = Boolean.parseBoolean(request.getParameter("isOutCity"));
        if (!isGoodString(userNum) || !isGoodString(stateTime)) {
            request.setAttribute("message", "输入不能为空。");
            request.getRequestDispatcher("/WEB-INF/stateRequest.jsp").forward(request, response);
            return;
        }
        State state = new State(stateNum, userNum, stateTime, isTemperature, isCovid, isLikeCovid, quarantine,
                isRecentArea, isRecentCountry, isRecentPeople, symptom, isAbnormal, healthCodeType, isOutSchool, isOutCity);
        Integer cnt = stateService.addState(state);
        if (cnt == 1) {
            request.setAttribute("message", "今日打卡成功。");
            response.sendRedirect("/state/success");
        } else {
            request.setAttribute("message", "今日打卡失败。");
            request.getRequestDispatcher("/state/addState").forward(request, response);
        }
    }
}