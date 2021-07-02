package cn.edu.zust.service;

import cn.edu.zust.util.DBUtil;
import cn.edu.zust.vo.Statistic;
import cn.edu.zust.vo.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticService {
    public List<User> executeStatisticQuery(String sql) throws SQLException {
        ResultSet rs = DBUtil.select(sql);
        List<User> users = new ArrayList<>();
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

    public Integer getTotalNumber() throws SQLException {
        String sql = "SELECT COUNT(userNum) num FROM user";
        ResultSet rs = DBUtil.select(sql);
        Integer totNum = -1;
        if (rs != null && rs.next()) {
            totNum = rs.getInt("num");
        }
        return totNum;
    }

    public Statistic getAllStatistic(String currentDate) throws SQLException {
        String sql = "SELECT * FROM statistic WHERE stateTime = '" + currentDate + "'";
        ResultSet rs = DBUtil.select(sql);
        Statistic statistic = null;
        if (rs != null && rs.next()) {
            statistic = new Statistic();
            statistic.setTotalNum(rs.getInt("totalNum"));
            statistic.setFilledNum(rs.getInt("filledNum"));
            statistic.setHighRiskNum(rs.getInt("highRiskNum"));
            statistic.setPassRiskAreaNum(rs.getInt("passRiskAreaNum"));
        } else {
            statistic = new Statistic(getTotalNumber(), 0, 0, 0);
        }
        return statistic;
    }

    public List<User> getFilledUsers(String currentDate) throws SQLException {
        String sql = "SELECT * FROM user WHERE userNum in (SELECT userNum FROM state WHERE stateTime='" + currentDate + "') AND userType=2";
        List<User> users = new ArrayList<>();
        return executeStatisticQuery(sql);
    }

    public List<User> getHighRiskUsers(String currentDate) throws SQLException {
        String sql = "SELECT * FROM user WHERE userNum in (SELECT userNum FROM state WHERE stateTime='" + currentDate + "' AND isCovid=1) AND userType=2";
        List<User> users = new ArrayList<>();
        return executeStatisticQuery(sql);
    }

    public List<User> getHighRiskAreaUsers(String currentDate) throws SQLException {
        String sql = "SELECT * FROM user WHERE userNum in (SELECT userNum FROM state WHERE stateTime='" + currentDate + "' AND isRecentArea=1) AND userType=2";
        List<User> users = new ArrayList<>();
        return executeStatisticQuery(sql);
    }
}
