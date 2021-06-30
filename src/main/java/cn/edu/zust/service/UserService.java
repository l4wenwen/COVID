package cn.edu.zust.service;

import cn.edu.zust.util.DBUtil;
import cn.edu.zust.vo.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public User userLogin(String loginName, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE login_name = '" + loginName + "' and password = '" + password + "'";
        ResultSet rs = DBUtil.select(sql);
        User user = null;
        if (rs != null && rs.next()) {
            String name = rs.getString("name");
            String userNum = rs.getString("name");
            boolean sex = rs.getBoolean("name");
            String college = rs.getString("name");
            String major  = rs.getString("name");
            Integer classNum  = rs.getInt("name");
            Integer userType  = rs.getInt("name");
            user = new User(name, userNum, sex, college, major, password, classNum, userType);
        }
        return user;
    }
}
