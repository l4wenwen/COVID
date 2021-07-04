package cn.edu.zust.covid.servlet;

import cn.edu.zust.covid.util.MD5Util;
import cn.edu.zust.covid.vo.User;
import cn.edu.zust.covid.vo.UserProfile;

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
        if (!"login".equals(methodName)) {
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
            user = userService.userLogin(userNum, MD5Util.encrypt(password));
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

    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        String userNum = request.getParameter("num");
        String userName = request.getParameter("name");
        String sex = request.getParameter("sex");
        String userType = request.getParameter("type");
        String collegeNum = request.getParameter("college");
        String majorNum = request.getParameter("major");
        String message = "", directURI = "/WEB-INF/";
        boolean isRegistered = false;
        do {
            if (!isGoodString(userName) || !isGoodString(userNum) || !isGoodString(sex) || !isGoodString(userType)) {
                message = "信息不能为空。";
                break;
            }
            if (userNum.length() != 8) {
                message = "学号长度为8位";
                break;
            }
            Integer iUserType = Integer.parseInt(userType);
            if (iUserType == 0) {
                collegeNum = majorNum = null;
            } else if (iUserType == 1) {
                if (!isGoodString(collegeNum)) {
                    message = "学院未选择";
                    break;
                }
                majorNum = null;
            } else {
                if (!isGoodString(collegeNum) || !isGoodString(majorNum)) {
                    message = "学院或专业未选择";
                    break;
                }
            }
            isRegistered = userService.addUser(userNum, userName, sex, userType, collegeNum, majorNum);
            if (!isRegistered) {
                message = "账号已存在。";
                break;
            }
            message = "添加成功";
        } while (false);
        PrintWriter out = response.getWriter();
        out.print(message);
    }

    public void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pwd = request.getParameter("pwd");
        String repwd = request.getParameter("repwd");
        String json = "{\"state\": ";
        if (!isGoodString(pwd) || !isGoodString(repwd) || !pwd.equals(repwd) || !userService.updatePassword(getCurrentUser(request).getUserNum(), MD5Util.encrypt(pwd))) json += "1";
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
