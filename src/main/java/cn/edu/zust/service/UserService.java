package cn.edu.zust.service;

import cn.edu.zust.util.DBUtil;
import cn.edu.zust.vo.User;

import java.sql.ResultSet;
import java.sql.SQLException;

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
}
