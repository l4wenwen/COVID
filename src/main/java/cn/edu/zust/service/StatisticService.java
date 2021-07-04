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
            boolean sex = rs.getInt("sex") == 1;
            String telephone = rs.getString("telephone");
            Integer state = rs.getInt("state");
            Integer collegeNum = rs.getInt("collegeNum");
            User user = new User();
            user.setUserName(userName);
            user.setUserNum(userNum);
            user.setSex(sex);
            user.setTelephone(telephone);
            user.setState(state);
            user.setCollegeNum(collegeNum);
            users.add(user);
        }
        return users;
    }

    public Integer getTotalNumber(Integer collegeNumber) throws SQLException {
        String sql = "SELECT COUNT(userNum) num FROM user WHERE userType=2";
        if (collegeNumber != 0) {
            sql += " AND collegeNum='" + collegeNumber + "'";
        }
        ResultSet rs = DBUtil.select(sql);
        Integer totNum = -1;
        if (rs != null && rs.next()) {
            totNum = rs.getInt("num");
        }
        return totNum;
    }

    public Statistic getAllStatistic(String currentDate, Integer collegeNum) throws SQLException {
        String sql = "SELECT * FROM statistic WHERE stateTime = '" + currentDate + "'";
        if (collegeNum != 0) {
            sql = "SELECT * FROM statistic_divide WHERE stateTime = '" + currentDate + "' AND collegeNum='" + collegeNum + "'";
        }
        ResultSet rs = DBUtil.select(sql);
        System.out.println(sql);
        Statistic statistic = null;
        if (rs != null && rs.next()) {
            statistic = new Statistic();
            statistic.setTotalNum(rs.getInt("totalNum"));
            statistic.setFilledNum(rs.getInt("filledNum"));
            statistic.setHighRiskNum(rs.getInt("highRiskNum"));
            statistic.setPassRiskAreaNum(rs.getInt("passRiskAreaNum"));
        } else {
            statistic = new Statistic(getTotalNumber(collegeNum), 0, 0, 0);
        }
        return statistic;
    }

    public List<User> getFilledUsers(String currentDate, Integer collegeNum) throws SQLException {
        String sql = "SELECT userName, userNum, collegeNum, sex, isCovid state, telephone FROM (SELECT user.*, state.isCovid, state.isRecentArea, " +
                "state.stateTime FROM user LEFT JOIN state ON user.userNum = state.userNum) a WHERE a.userType = 2 AND a.stateTime='" + currentDate + "'";
        if (collegeNum != 0)
            sql += " AND collegeNum='" + collegeNum + "'";
        List<User> users = new ArrayList<>();
        return executeStatisticQuery(sql);
    }

    public List<User> getHighRiskUsers(String currentDate, Integer collegeNum) throws SQLException {
        String sql = "SELECT userName, userNum, collegeNum, sex, isCovid state, telephone FROM (SELECT user.*, state.isCovid, state.isRecentArea, " +
                "state.stateTime FROM user LEFT JOIN state ON user.userNum = state.userNum) a WHERE a.userType = 2 AND isCovid=1 AND a.stateTime='" + currentDate + "'";
        if (collegeNum != 0)
            sql += " AND collegeNum='" + collegeNum + "'";
        List<User> users = new ArrayList<>();
        return executeStatisticQuery(sql);
    }

    public List<User> getHighRiskAreaUsers(String currentDate, Integer collegeNum) throws SQLException {
        String sql = "SELECT userName, userNum, collegeNum, sex, isCovid state, telephone FROM (SELECT user.*, state.isCovid, state.isRecentArea, " +
                "state.stateTime FROM user LEFT JOIN state ON user.userNum = state.userNum) a WHERE a.userType = 2 AND isRecentArea=1 AND a.stateTime='" + currentDate + "'";
        if (collegeNum != 0)
            sql += " AND collegeNum='" + collegeNum + "'";
        List<User> users = new ArrayList<>();
        return executeStatisticQuery(sql);
    }

    public String getCollegeOption() throws SQLException {
        String sql = "SELECT * FROM college WHERE collegeNum <> 0";
        ResultSet rs = DBUtil.select(sql);
        String collegeOption = "";
        while(rs.next()) {
            Integer collegeNum = rs.getInt("collegeNum");
            String collegeName = rs.getString("collegeName");
            collegeOption += "<option value='" + collegeNum + "'>" + collegeName + "</option>";
        }
        return collegeOption;
    }

    public String getMajorOption(Integer collegeNum) throws SQLException {
        String sql = "SELECT * FROM major WHERE collegeNum='" + collegeNum + "'";
        ResultSet rs = DBUtil.select(sql);
        String majorOption = "";
        while(rs.next()) {
            Integer majorNum = rs.getInt("majorNum");
            String majorName = rs.getString("majorName");
            majorOption += "<option value='" + majorNum + "'>" + majorName + "</option>";
        }
        return majorOption;
    }
}
