package cn.edu.zust.servlet;

import cn.edu.zust.util.DBUtil;
import cn.edu.zust.vo.State;
import cn.edu.zust.vo.User;
import cn.edu.zust.vo.Vacation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "stateServlet", urlPatterns = "state/*", loadOnStartup = 1)
public class StateServlet extends BaseServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print("StateServlet doGet:");
        String methodName = getMethod(request.getRequestURI());
        System.out.println("methodName1:" + methodName);
        if (!methodName.equals("request") && !methodName.equals("list") ) {
            doPost(request, response);
        } else {
            try {
                Method method = getClass().getDeclaredMethod(methodName,
                        HttpServletRequest.class, HttpServletResponse.class);
                methodName = "state" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
            } catch (NoSuchMethodException e) {
                System.out.println("doGet: error");
                methodName = "error";
            }
            System.out.println("methodName2:" + methodName);
            request.getRequestDispatcher("/WEB-INF/" + methodName + ".jsp").forward(request, response);
        }
    }

    public void request(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("request: ");
        User user = (User) request.getSession().getAttribute("user");
        String userNum = user.getUserNum();
        String stateTime = getTime();
        boolean isTemperature = Boolean.parseBoolean(request.getParameter("isTemperature"));
        boolean isCovid = Boolean.parseBoolean(request.getParameter("isCovid"));
        boolean isLikeCovid = Boolean.parseBoolean(request.getParameter("isLikeCovid"));
        Integer quarantine = Integer.parseInt(request.getParameter("quarantine"));
        boolean isRecentArea = Boolean.parseBoolean(request.getParameter("isRecentArea"));
        boolean isRecentCountry = Boolean.parseBoolean(request.getParameter("isRecentCountry"));
        boolean isRecentPeople = Boolean.parseBoolean(request.getParameter("isRecentPeople"));
        boolean isSymptom = Boolean.parseBoolean(request.getParameter("symptom"));
        boolean isAbnormal = Boolean.parseBoolean(request.getParameter("isAbnormal"));
        Integer healthCodeType = Integer.parseInt(request.getParameter("healthCodeType"));
        boolean isOutSchool = Boolean.parseBoolean(request.getParameter("isOutSchool"));
        boolean isOutCity = Boolean.parseBoolean(request.getParameter("isOutCity"));
        if (!isGoodString(userNum) || !isGoodString(stateTime)) {
            request.setAttribute("message", "输入不能为空。");
            System.out.println("输入不能为空。");
            request.getRequestDispatcher("/WEB-INF/stateRequest.jsp").forward(request, response);
        } else {
            State state = new State(userNum, stateTime, isTemperature, isCovid, isLikeCovid, quarantine,
                    isRecentArea, isRecentCountry, isRecentPeople, isSymptom, isAbnormal, healthCodeType, isOutSchool, isOutCity);
            Integer cnt = stateService.addState(state);
            if (cnt == 1) {
                System.out.println("提交请求成功。");
                request.getRequestDispatcher("/WEB-INF/stateList.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "提交请求失败。");
                System.out.println("提交请求失败。");
                request.getRequestDispatcher("/WEB-INF/stateRequest.jsp").forward(request, response);
            }
        }
    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        System.out.println("list: ");
        String startTime = request.getParameter("startTime");
        System.out.println("startTime = " + startTime);
        String endTime = request.getParameter("endTime");
        System.out.println("endTime = " + endTime);
        User user = (User) request.getSession().getAttribute("user");
        Integer userNum = Integer.parseInt(user.getUserNum());
        String collegeName = userService.getCollegeNameByUserNum(userNum);
        if (!isGoodString(startTime) || !isGoodString(endTime) || !isGoodString(collegeName)) {
            request.setAttribute("message", "输入不能为空。");
            request.getRequestDispatcher("/WEB-INF/stateList.jsp").forward(request, response);
            return;
        }
        List<State> states = stateService.selectState(startTime, endTime, collegeName, user.getUserType(), user.getUserNum());
        request.setAttribute("states", states);
        request.getRequestDispatcher("/WEB-INF/stateList.jsp").forward(request, response);
    }
}