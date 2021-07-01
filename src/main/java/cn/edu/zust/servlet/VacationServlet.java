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
    protected User currentUser;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName = getMethod(request.getRequestURI());
        if ((currentUser = getCurrentUser(request)) == null) {
            response.sendRedirect("/user/login");
        }
        if (!"request".equals(methodName)) {
            doPost(request, response);
        } else {
            try {
                Method method = getClass().getDeclaredMethod(methodName,
                        HttpServletRequest.class, HttpServletResponse.class);
                methodName = "vacation" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);   //将请求方法加上vacation前缀以访问jsp文件
            } catch (NoSuchMethodException e) {
                methodName = "error";
            }
            request.getRequestDispatcher("/WEB-INF/" + methodName + ".jsp").forward(request, response);
        }
    }

    //提交离校请求
    public void request(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (currentUser.getUserType() != 2) {
            response.sendRedirect("/user/userHome");
            return;
        }
        String reason = request.getParameter("reason");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String transport = request.getParameter("transport");
        if (reason == null || startTime == null || endTime == null || transport == null ||  "".equals(reason.trim()) || "".equals(startTime.trim()) ||
            "".equals(endTime.trim()) || "".equals(transport.trim())) {
            request.setAttribute("message", "输入不能为空。");
            request.getRequestDispatcher("/WEB-INF/vacationRequest.jsp").forward(request, response);
        }
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        Vacation vacation = new Vacation(0, reason, startTime, endTime, sdf.format(date), transport, currentUser.getUserNum(), Vacation.STATE_PENDING);
        if (vacationService.submitVacationRequest(currentUser, vacation)) {
            response.sendRedirect("/vacation/list");
        } else {
            request.setAttribute("message", "提交请求失败。");
            request.getRequestDispatcher("/vacation/request").forward(request, response);
        }
    }

    //列出该用户所有的离校请求
    public void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Vacation> vacations;
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

    public void operate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String vid = request.getParameter("vid");
        String operation = request.getParameter("operation");
        if (currentUser.getUserType() == 0)
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