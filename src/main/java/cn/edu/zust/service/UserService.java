package cn.edu.zust.service;

import cn.edu.zust.util.DBUtil;
import cn.edu.zust.vo.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    public User userLogin(String userNum, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE userNum = '" + userNum + "' and password = '" + password + "'";
        ResultSet rs = DBUtil.select(sql);
        User user = null;
        if (rs != null && rs.next()) {
            String userName = rs.getString("userName");
            int userType  = rs.getInt("userType");
            int collegeNum = rs.getInt("collegeNum");
            int majorNum = rs.getInt("majorNum");
            int classNum = rs.getInt("classNum");
            user = new User(userName, userNum, true, collegeNum, majorNum, password, classNum, userType);
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
        List<User> users = new ArrayList<>();
        ResultSet rs = DBUtil.select(sql);
        while(rs.next()) {
            String userNum = rs.getString("userNum");
            String userName = rs.getString("userName");
            int userType = rs.getInt("userType");
            boolean sex = rs.getInt("sex") == 1;
            User user = new User(userName, userNum, sex, 0, 0, "", 0, userType);
            users.add(user);
        }
        return users;
    }
}
