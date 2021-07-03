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
            String telephone = rs.getString("telephone");
            User user = new User(userName, userNum, sex, collegeNum, majorNum, "", classNum, userType, telephone);
            users.add(user);
        }
        return users;
    }

    public User userLogin(String userNum, String password) throws SQLException {
        String sql = "SELECT *, isCovid state FROM (SELECT user.*, state.isCovid, state.isRecentArea FROM user LEFT JOIN state ON user.userNum = state.userNum) a " +
                "WHERE userNum='" + userNum + "' AND password='" + password + "'";
        ResultSet rs = DBUtil.select(sql);
        User user = null;
        if (rs != null && rs.next()) {
            String userName = rs.getString("userName");
            int userType  = rs.getInt("userType");
            boolean sex = rs.getBoolean("sex");
            int collegeNum = rs.getInt("collegeNum");
            int majorNum = rs.getInt("majorNum");
            int classNum = rs.getInt("classNum");
            String telephone = rs.getString("telephone");
            Integer state = rs.getInt("state");
            user = new User(userName, userNum, sex, collegeNum, majorNum, password, classNum, userType, telephone);
            user.setState(state);
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
        String sql = "SELECT userName, userNum, sex, isCovid state, " +
                "telephone FROM (SELECT user.*, state.isCovid, state.isRecentArea FROM user LEFT JOIN state ON user.userNum = state.userNum) a " +
                "WHERE a.userType = 2";
        ResultSet rs = DBUtil.select(sql);
        List<User> users = new ArrayList<>();
        while(rs.next()) {
            String userNum = rs.getString("userNum");
            String userName = rs.getString("userName");
            boolean sex = rs.getBoolean("sex");
            Integer state = rs.getInt("state");
            User user = new User();
            user.setUserNum(userNum);
            user.setUserName(userName);
            user.setSex(sex);
            user.setState(state);
            users.add(user);
        }
        return users;
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

    public UserProfile getUserProfile(String userNum) throws SQLException {
        String sql = "SELECT * FROM user WHERE userNum='" + userNum + "'";
        List<User> users = executeUserQuery(sql);
        UserProfile userProfile = null;
        if (users.size() == 1) {
            userProfile = new UserProfile();
            User user = users.get(0);
            userProfile.setUserName(user.getUserName());
            userProfile.setUserNum(user.getUserNum());
            userProfile.setSex(user.isSex() ? "男" : "女");
            userProfile.setCollegeName(getCollegeNameById(user.getCollegeNum()));
            userProfile.setMajorName(getMajorNameById(user.getMajorNum()));
            String userType = "管理员";
            if (user.getUserType() == 1) userType = "老师";
            else if (user.getUserType() == 2) userType = "学生";
            userProfile.setUserType(userType);
            userProfile.setTelephone(user.getTelephone() == null ? "未填写" : user.getTelephone());
        }
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

    public String getCollegeNameByUserNum(Integer userNum) throws SQLException {
        String sql = "SELECT `collegeNum` FROM `user` WHERE userNum = \"" + userNum + "\";";
        ResultSet rs = DBUtil.select(sql);
        Integer collegeNum = -1;
        if (rs != null && rs.next()) {
            collegeNum = rs.getInt("collegeNum");
        }
        return getCollegeNameById(collegeNum);
    }

    public boolean updateTelephone(String userNum, String telephone) {
        String sql = "UPDATE user SET telephone='" + telephone + "' WHERE userNum='" + userNum + "'";
        return DBUtil.update(sql) == 1;
    }
}
