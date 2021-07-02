package cn.edu.zust.service;

import cn.edu.zust.util.DBUtil;
import cn.edu.zust.vo.User;
import cn.edu.zust.vo.UserProfile;
import cn.edu.zust.vo.Vacation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    public List<User> executeUserQuery(String sql) throws SQLException {
        ResultSet rs = DBUtil.select(sql);
        List<User> users = new ArrayList<>();
        while(rs.next()) {
            String userNum = rs.getString("userNum");
            String userName = rs.getString("userName");
            int userType  = rs.getInt("userType");
            boolean sex = rs.getBoolean("sex");
            int collegeNum = rs.getInt("collegeNum");
            int majorNum = rs.getInt("majorNum");
            int classNum = rs.getInt("classNum");
            User user = new User(userName, userNum, sex, collegeNum, majorNum, "", classNum, userType);
            users.add(user);
        }
        return users;
    }

    public User userLogin(String userNum, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE userNum = '" + userNum + "' and password = '" + password + "'";
        ResultSet rs = DBUtil.select(sql);
        User user = null;
        if (rs != null && rs.next()) {
            String userName = rs.getString("userName");
            int userType  = rs.getInt("userType");
            boolean sex = rs.getBoolean("sex");
            int collegeNum = rs.getInt("collegeNum");
            int majorNum = rs.getInt("majorNum");
            int classNum = rs.getInt("classNum");
            user = new User(userName, userNum, sex, collegeNum, majorNum, password, classNum, userType);
        }
        return user;
    }

    public boolean userRegister(String userNum, String password) throws SQLException {
        String sqlCheck = "SELECT * FROM user WHERE userNum = '" + userNum + "' and password = '" + password + "'";
        String sqlUpdate = "INSERT INTO user(userNum, password, userType) VALUES('" + userNum + "','" + password + "', 2)";
        ResultSet rs = DBUtil.select(sqlCheck);
        boolean isRegistered = false;
        if (rs == null || !rs.next()) {
            isRegistered = DBUtil.update(sqlUpdate) == 1;
        }
        return isRegistered;
    }

    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM user WHERE userType=2";
        return executeUserQuery(sql);
    }

    public List<User> getAllTeachers() throws SQLException {
        String sql = "SELECT * FROM user WHERE userType=1";
        return executeUserQuery(sql);
    }

    public String getCollegeNameById(Integer collegeNum) throws SQLException {
        String sql = "SELECT * FROM college WHERE collegeNum='" + collegeNum + "'";
        ResultSet rs = DBUtil.select(sql);
        String collegeName = "NULL";
        if (rs != null && rs.next()) {
            collegeName = rs.getString("collegeName");
        }
        return collegeName;
    }

    public String getMajorNameById(Integer majorNum) throws SQLException {
        String sql = "SELECT * FROM major WHERE majorNum='" + majorNum + "'";
        ResultSet rs = DBUtil.select(sql);
        String majorName = "NULL";
        if (rs != null && rs.next()) {
            majorName = rs.getString("majorName");
        }
        return majorName;
    }

    public UserProfile getUserProfile(User user) throws SQLException {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserName(user.getUserName());
        userProfile.setUserNum(user.getUserNum());
        userProfile.setSex(user.isSex() ? "男" : "女");
        userProfile.setCollegeName(getCollegeNameById(user.getCollegeNum()));
        userProfile.setMajorName(getMajorNameById(user.getMajorNum()));
        String userType = "管理员";
        if (user.getUserType() == 1) userType = "老师";
        else if (user.getUserType() == 2) userType = "学生";
        userProfile.setUserType(userType);
        return userProfile;
    }

    public boolean updatePassword(String userNum, String pwd) {
        String sql = "UPDATE user SET password='" + pwd + "' WHERE userNum='" + userNum + "'";
        return DBUtil.update(sql) == 1;
    }

    public Integer getStudentNumber() throws SQLException {
        String sql = "SELECT COUNT(*) num FROM user WHERE userType = 2";
        ResultSet rs = DBUtil.select(sql);
        Integer studentNum = 0;
        if (rs != null && rs.next())
            studentNum = rs.getInt("num");
        return studentNum;
    }

    public Integer getTeacherNumber() throws SQLException {
        String sql = "SELECT COUNT(*) num FROM user WHERE userType = 1";
        ResultSet rs = DBUtil.select(sql);
        Integer teacherNum = 0;
        if (rs != null && rs.next())
            teacherNum = rs.getInt("num");
        return teacherNum;
    }
}
