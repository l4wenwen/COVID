package cn.edu.zust.service;

import cn.edu.zust.util.DBUtil;
import cn.edu.zust.vo.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public User userLogin(String userNum, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE uNum = '" + userNum + "' and uPassword = '" + password + "'";
        ResultSet rs = DBUtil.select(sql);
        User user = null;
        if (rs != null && rs.next()) {
            String name = rs.getString("uName");
            boolean sex = rs.getBoolean("uSex");
            String college = rs.getString("uCollege");
            String major  = rs.getString("uMajor");
            Integer classNum  = rs.getInt("uClassNum");
            int userType  = rs.getInt("uType");
            user = new User(name, userNum, sex, college, major, password, classNum, userType);
        }
        return user;
    }

    public boolean userRegister(String userNum, String password) throws SQLException {
        String sqlCheck = "SELECT * FROM user WHERE uNum = '" + userNum + "' and uPassword = '" + password + "'";
        String sqlUpdate = "INSERT INTO user(uNum, uPassword, uName, uSex, uType) VALUES('" + userNum + "','" + password + "','a','0','0')";
        ResultSet rs = DBUtil.select(sqlCheck);
        boolean isRegistered = false;
        if (rs == null || !rs.next()) {
            isRegistered = DBUtil.update(sqlUpdate);
        }
        return isRegistered;
    }
}
