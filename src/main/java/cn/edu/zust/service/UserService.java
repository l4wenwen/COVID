package cn.edu.zust.service;

import cn.edu.zust.util.DBUtil;
import cn.edu.zust.vo.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public User userLogin(String userNum, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE user_id = '" + userNum + "' and password = '" + password + "'";
        ResultSet rs = DBUtil.select(sql);
        User user = null;
        if (rs != null && rs.next()) {
            String userName = rs.getString("user_name");
            int userType  = rs.getInt("user_type");
            int depNo = rs.getInt("dept_no");
            user = new User(userName, userNum, true, depNo, "major", password, 1, userType);
        }
        return user;
    }

    public boolean userRegister(String userNum, String password) throws SQLException {
        String sqlCheck = "SELECT * FROM user WHERE id = '" + userNum + "' and password = '" + password + "'";
        String sqlUpdate = "INSERT INTO user(id, password, user_type) VALUES('" + userNum + "','" + password + "', 2)";
        ResultSet rs = DBUtil.select(sqlCheck);
        boolean isRegistered = false;
        if (rs == null || !rs.next()) {
            isRegistered = DBUtil.update(sqlUpdate);
        }
        return isRegistered;
    }
}
