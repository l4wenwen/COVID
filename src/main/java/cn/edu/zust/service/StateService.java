package cn.edu.zust.service;

import cn.edu.zust.util.DBUtil;
import cn.edu.zust.vo.State;
import cn.edu.zust.vo.User;
import cn.edu.zust.vo.Vacation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StateService {
    public Integer addState(State state) {
        String sql = "INSERT INTO `State` (userNum, stateTime, isTemperature, isCovid, isLikeCovid, quarantine, " +
                "isRecentArea, isRecentCountry, isRecentPeople, isSymptom, isAbnormal, healthCodeType, isOutSchool, isOutCity) VALUES (" +
                state.getUserNum() + ", \"" + state.getStateTime() + "\", " + state.isTemperature() + ", " +
                state.isCovid() + ", " + state.isLikeCovid() + ", " + state.getQuarantine() + ", " + state.isRecentArea() + ", " +
                state.isRecentCountry() + ", " + state.isRecentPeople() + ", " + state.isSymptom() + ", " + state.isAbnormal() + ", " +
                state.getHealthCodeType() + ", " + state.isOutSchool() + ", " + state.isOutCity() + ");";
        Date date = new Date();
        return DBUtil.update(sql);
    }

    public Integer delState(String userNum) {
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strDateFormat);
        String stateTime = simpleDateFormat.format(date);
        String sql = "DELETE FROM `state` WHERE `stateTime` = \"" + stateTime + "\" AND `userNum` = \"" + userNum + "\";";
        return DBUtil.update(sql);
    }

    public List<State> executeStateQuery(String sql) throws SQLException {
        ResultSet rs = DBUtil.select(sql);
        List<State> states = new ArrayList<>();
        while(rs.next()) {
            Integer stateNum = rs.getInt("stateNum");
            String userNum = rs.getString("userNum");
            String strDateFormat = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strDateFormat);
            String stateTime = simpleDateFormat.format(rs.getDate("stateTime"));
            Boolean isTemperature = rs.getBoolean("isTemperature");
            Boolean isCovid = rs.getBoolean("isCovid");
            Boolean isLikeCovid = rs.getBoolean("isLikeCovid");
            Integer quarantine = rs.getInt("quarantine");
            Boolean isRecentArea = rs.getBoolean("isRecentArea");
            Boolean isRecentCountry = rs.getBoolean("isRecentCountry");
            Boolean isRecentPeople = rs.getBoolean("isRecentPeople");
            Boolean isSymptom = rs.getBoolean("isSymptom");
            Boolean isAbnormal = rs.getBoolean("isAbnormal");
            Integer healthCodeType = rs.getInt("healthCodeType");
            Boolean isOutSchool = rs.getBoolean("isOutSchool");
            Boolean isOutCity = rs.getBoolean("isOutCity");
            State state = new State(stateNum, userNum, stateTime, isTemperature, isCovid, isLikeCovid, quarantine,
                    isRecentArea, isRecentCountry, isRecentPeople, isSymptom, isAbnormal, healthCodeType, isOutSchool, isOutCity);
            states.add(state);
        }
        rs.close();
        return states;
    }

    public List<State> selectState(String startTime, String endTime, Integer collegeNum, Integer userType, String userNum) throws SQLException {
        String sql = "SELECT * FROM `State` WHERE stateTime BETWEEN \"" + startTime + "\" AND \"" + endTime + "\"";
        if (userType == 0) {
            sql += ";";
        } else if (userType == 2) {
            sql += " AND `userNum` = \"" + userNum + "\";";
        } else {
            sql = "SELECT * FROM state WHERE userNum IN (SELECT userNum FROM user WHERE collegeNum='" + collegeNum + "')";
        }
        Date date = new Date();
        System.out.println(date + " selectState: sql = " + sql);
        return executeStateQuery(sql);
    }

    public Integer getUserState(String userNum, String currentDate) throws SQLException {
        String sql = "SELECT isCovid FROM state WHERE userNum='" + userNum + "' AND stateTime='" + currentDate + "'";
        ResultSet rs = DBUtil.select(sql);
        Integer isCovid = 3;
        if (rs != null && rs.next()) {
            isCovid = rs.getInt("isCovid");
        }
        return isCovid;
    }

/*
    public List<State> getStateListByUserNum(String userNum) throws SQLException {
        String sql = "SELECT * FROM `state` WHERE `userNum` = \"" + userNum + "\";";
        return executeStateQuery(sql);
    }

    public List<State> getStateListByCollegeNum(Integer collegeNum) throws SQLException {
        String sql = "SELECT s.* FROM `state` AS s, `user` AS u, `college` AS c WHERE `s.userNum` = `u.userNum` AND " +
                "c.collegeNum = \"" + collegeNum + "\" AND u.collegeNum = c.collegeNum;";
        return executeStateQuery(sql);
    }
*/
}
