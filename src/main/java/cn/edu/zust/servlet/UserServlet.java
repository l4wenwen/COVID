package cn.edu.zust.servlet;

import cn.edu.zust.vo.User;
import cn.edu.zust.vo.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "userServlet", urlPatterns = "user/*", loadOnStartup = 1)
public class UserServlet extends BaseServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //doGet跳转至相应界面
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String methodName = getMethod(request.getRequestURI());
        if ("logout".equals(methodName) || "update".equals(methodName) || "profile".equals(methodName) || "changePassword".equals(methodName)
            || "manager".equals(methodName) || "userInfo".equals(methodName)) {
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

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String userNum = request.getParameter("account");
        String password = request.getParameter("password");
        String message = "", directURI = "/WEB-INF/";
        User user = null;
        if (userNum == null || password == null || "".equals(userNum.trim()) || "".equals(password.trim()))
            message = "账号密码不能为空。";
        else {
            user = userService.userLogin(userNum, password);
            if (user == null) message = "账号密码错误。";
        }
        if (user == null) {
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/userHome.jsp").forward(request, response);
        }
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String userNum = request.getParameter("account");
        String password = request.getParameter("password");
        String message = "", directURI = "/WEB-INF/";
        boolean isRegistered = false;
        if (userNum == null || password == null || "".equals(userNum.trim()) || "".equals(password.trim()))
            message = "账号密码不能为空。";
        else {
            isRegistered = userService.userRegister(userNum, password);
            if (!isRegistered) message = "账号已存在。";
        }

        if (isRegistered) directURI += "login.jsp";
        else {
            request.setAttribute("message", message);
            directURI += "register.jsp";
        }
        request.getRequestDispatcher(directURI).forward(request, response);
    }

    public void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pwd = request.getParameter("pwd");
        String repwd = request.getParameter("repwd");
        String json = "{\"state\": ";
        if (!isGoodString(pwd) || !isGoodString(repwd) || !pwd.equals(repwd) || !userService.updatePassword(getCurrentUser(request).getUserNum(), pwd)) json += "1";
        else json += "0";
        json += "}";
        PrintWriter out = response.getWriter();
        out.print(json);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String telephone = request.getParameter("telephone");
        System.out.println(telephone);
        boolean result = false;
        if (isGoodString(telephone)) {
            Pattern pattern = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
            Matcher matcher = pattern.matcher(telephone);
            result = matcher.matches();
        }
        if (result) {
            userService.updateTelephone(getCurrentUser(request).getUserNum(), telephone);
        } else {
            request.setAttribute("message", "号码格式错误！");
        }
        request.getRequestDispatcher("/user/profile").forward(request, response);
    }

    public void manager(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
    }

    public void profile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        UserProfile userProfile = userService.getUserProfile(getCurrentUser(request).getUserNum());
        request.setAttribute("userProfile", userProfile);
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }

    public void userinfo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        List<User> users = userService.getAllUsers(getCurrentUser(request).getCollegeNum());
        String json = convertUserToJSON(users);
        PrintWriter out = response.getWriter();
        out.print(json);
    }

    public void teacherlist(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        List<User> users = userService.getAllTeachers();
        String json = convertUserToJSON(users);
        PrintWriter out = response.getWriter();
        out.print(json);
    }

    public void userInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Integer studentNum = userService.getStudentNumber();
        Integer teacherNum = userService.getTeacherNumber();
        request.setAttribute("studentNum", studentNum);
        request.setAttribute("teacherNum", teacherNum);
        request.getRequestDispatcher("/WEB-INF/userManager.jsp").forward(request, response);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("/user/login");
    }

    public void userHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/userHome.jsp").forward(request, response);
    }

    public User getCurrentUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }
}
