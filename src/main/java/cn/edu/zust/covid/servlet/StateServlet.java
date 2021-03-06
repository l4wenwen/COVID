package cn.edu.zust.covid.servlet;

import cn.edu.zust.covid.vo.State;
import cn.edu.zust.covid.vo.User;

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
//        System.out.println("methodName1:" + methodName);
        if (!methodName.equals("request") && !methodName.equals("list") ) {
            doPost(request, response);
        } else {
            try {
                Method method = getClass().getDeclaredMethod(methodName,
                        HttpServletRequest.class, HttpServletResponse.class);
                methodName = "state" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
            } catch (NoSuchMethodException e) {
//                System.out.println("doGet: error");
                methodName = "error";
            }
//            System.out.println("methodName2:" + methodName);
            request.getRequestDispatcher("/WEB-INF/" + methodName + ".jsp").forward(request, response);
        }
    }

    public void request(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        System.out.println("request: ");
        User user = (User) request.getSession().getAttribute("user");
        String userNum = user.getUserNum();
        String stateTime = getDate();
        boolean isTemperature = Boolean.parseBoolean(request.getParameter("isTemperature"));
//        System.out.println("isTemperature = " + isTemperature);
        boolean isCovid = Boolean.parseBoolean(request.getParameter("isCovid"));
//        System.out.println("isCovid = " + isCovid);
        boolean isLikeCovid = Boolean.parseBoolean(request.getParameter("isLikeCovid"));
//        System.out.println("isLikeCovid = " + isLikeCovid);
        Integer quarantine = Integer.parseInt(request.getParameter("quarantine"));
//        System.out.println("quarantine = " + quarantine);
        boolean isRecentArea = Boolean.parseBoolean(request.getParameter("isRecentArea"));
//        System.out.println("isRecentArea = " + isRecentArea);
        boolean isRecentCountry = Boolean.parseBoolean(request.getParameter("isRecentCountry"));
//        System.out.println("isRecentCountry = " + isRecentCountry);
        boolean isRecentPeople = Boolean.parseBoolean(request.getParameter("isRecentPeople"));
//        System.out.println("isRecentPeople = " + isRecentPeople);
        boolean isSymptom = Boolean.parseBoolean(request.getParameter("isSymptom"));
//        System.out.println("isSymptom = " + isSymptom);
        boolean isAbnormal = Boolean.parseBoolean(request.getParameter("isAbnormal"));
//        System.out.println("isAbnormal = " + isAbnormal);
        Integer healthCodeType = Integer.parseInt(request.getParameter("healthCodeType"));
//        System.out.println("healthCodeType = " + healthCodeType);
        boolean isOutSchool = Boolean.parseBoolean(request.getParameter("isOutSchool"));
//        System.out.println("isOutSchool = " + isOutSchool);
        boolean isOutCity = Boolean.parseBoolean(request.getParameter("isOutCity"));
//        System.out.println("isOutCity = " + isOutCity);
        if (!isGoodString(userNum) || !isGoodString(stateTime)) {
            request.setAttribute("message", "?????????????????????");
//            System.out.println("?????????????????????");
            request.getRequestDispatcher("/WEB-INF/stateRequest.jsp").forward(request, response);
        } else {
            stateService.delState(userNum);
            State state = new State(userNum, stateTime, isTemperature, isCovid, isLikeCovid, quarantine,
                    isRecentArea, isRecentCountry, isRecentPeople, isSymptom, isAbnormal, healthCodeType, isOutSchool, isOutCity);
            Integer cnt = stateService.addState(state);
            if (cnt == 1) {
//                System.out.println("?????????????????????");
                request.getRequestDispatcher("/WEB-INF/stateList.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "?????????????????????");
//                System.out.println("?????????????????????");
                request.getRequestDispatcher("/WEB-INF/stateRequest.jsp").forward(request, response);
            }
        }
    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        User user = (User) request.getSession().getAttribute("user");
        String userNum = user.getUserNum();
        Integer collegeNum = user.getCollegeNum();
        if (!isGoodString(startTime) || !isGoodString(endTime)) {
            request.setAttribute("message", "?????????????????????");
            request.getRequestDispatcher("/WEB-INF/stateList.jsp").forward(request, response);
            return;
        }
        List<State> states = stateService.selectState(startTime, endTime, collegeNum, user.getUserType(), user.getUserNum());
        request.setAttribute("states", states);
        request.getRequestDispatcher("/WEB-INF/stateList.jsp").forward(request, response);
    }

    public void getstate(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String userNum = ((User)request.getSession().getAttribute("user")).getUserNum();
        request.setAttribute("state", stateService.getUserState(userNum, getDate()));
        request.getRequestDispatcher("/WEB-INF/userHome.jsp").forward(request, response);
    }
}