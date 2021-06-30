package cn.edu.zust.util;

import java.sql.*;

public class DBUtil {
    private static Connection con = null;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean openConnection(String url, String userName, String pwd) {
        try {
            if (con != null && con.isClosed())
                con.close();
            con = DriverManager.getConnection(url, userName, pwd);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            con = null;
            return false;
        }
    }

    public static boolean closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                con = null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            con = null;
        }
        return true;
    }

    public static ResultSet select(String sql) {
        if (con == null) return null;
        try {
            Statement st = con.createStatement();
            return st.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static boolean update(String sql) {
        if (con == null) return false;
        try {
            Statement st = con.createStatement();
            return st.executeUpdate(sql) == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
