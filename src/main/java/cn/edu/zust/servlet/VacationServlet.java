package cn.edu.zust.servlet;

import cn.edu.zust.vo.User;
import cn.edu.zust.vo.Vacation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "vacationServlet", urlPatterns = "vacation/*")
public class VacationServlet extends BaseServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName = getMethod(request.getRequestURI());
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (!"request".equals(methodName)) {
            doPost(request, response);
        } else {
//            try {
//                Method method = getClass().getDeclaredMethod(methodName,
//                        HttpServletRequest.class, HttpServletResponse.class);
//                methodName = "vacation" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);   //将请求方法加上vacation前缀以访问jsp文件
//            } catch (NoSuchMethodException e) {
//                methodName = "error";
//            }
            request.getRequestDispatcher("/WEB-INF/vacationList.jsp").forward(request, response);
        }
    }

    //提交离校请求
    public void request(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (getCurrentUser(request).getUserType() != 2) {
            response.sendRedirect("/user/userHome");
            return;
        }
        String reason = request.getParameter("reason");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String transport = request.getParameter("transport");

        if (!isGoodString(reason) || !isGoodString(startTime) ||!isGoodString(endTime) || !isGoodString(transport)) {
            request.setAttribute("message", "输入不能为空。");
            request.getRequestDispatcher("/vacation/list").forward(request, response);
        } else {
            Vacation vacation = new Vacation(0, reason, startTime, endTime, getTime(), transport, getCurrentUser(request).getUserNum(), Vacation.STATE_PENDING);
            if (vacationService.submitVacationRequest(getCurrentUser(request), vacation)) {
                response.sendRedirect("/vacation/list");
            } else {
                request.setAttribute("message", "提交请求失败。");
                request.getRequestDispatcher("/vacation/list").forward(request, response);
            }
        }
    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Vacation> vacations;
        User currentUser = getCurrentUser(request);
        if (currentUser.getUserType() == 2) {
            vacations = vacationService.getVacationListById(currentUser.getUserNum());  //如果是学生，则获取自己的请假请求
        } else {
            vacations = vacationService.getVacationListByDepId(currentUser.getCollegeNum());   //如果是老师，则通过学院ID获取请求
        }
        request.setAttribute("vacations", vacations);
        request.getRequestDispatcher("/WEB-INF/vacationList.jsp").forward(request, response);
    }

    //用户撤销请求
    public void revoke(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String userId = request.getParameter("user_id");
        String vId = request.getParameter("vid");
        if (!vacationService.revokeRequest(userId, vId))
            request.setAttribute("message", "撤回失败，请求已经完成。");
        request.getRequestDispatcher("/vacation/list").forward(request, response);
    }

    public void operate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        if (getCurrentUser(request).getUserType() == 2) {
            request.getRequestDispatcher("/vacation/list").forward(request, response);
            return;
        }
        String vid = request.getParameter("vid");
        String operation = request.getParameter("operation");
        if (getCurrentUser(request).getUserType() == 0)
            response.sendRedirect("/user/userHome");
        else {
            vacationService.performDecision(Integer.parseInt(vid), Integer.parseInt(operation));
            request.getRequestDispatcher("/vacation/list").forward(request, response);
        }
    }

    private User getCurrentUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }
}
