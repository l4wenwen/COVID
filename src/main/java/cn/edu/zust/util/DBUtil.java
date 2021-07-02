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
        System.out.print("openConnection:");
        try {
            if (con != null && con.isClosed()) {
                con.close();
            }
            con = DriverManager.getConnection(url, userName, pwd);
            System.out.println("success");
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            con = null;
            System.out.println("fail");
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

    public static Integer update(String sql) {
        if (con == null) return -1;
        try {
            Statement st = con.createStatement();
            return st.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
}
