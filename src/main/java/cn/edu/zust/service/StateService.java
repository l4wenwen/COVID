package cn.edu.zust.service;

import cn.edu.zust.util.DBUtil;
import cn.edu.zust.vo.State;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class StateService {
    public Integer addState(State state) {
        String sql = "INSERT INTO `State` (stateNum, userNum, stateTime, isTemperature, isCovid, isLikeCovid, quarantine, " +
                "isRecentArea, isRecentCountry, isRecentPeople, symptom, isAbnormal, healthCodeType, isOutSchool, isOutCity) VALUES (" +
                state.getStateNum() + ", " + state.getUserNum() + ", " + state.getStateTime() + ", " + state.isTemperature() + ", " +
                state.isCovid() + ", " + state.isLikeCovid() + ", " + state.getQuarantine() + ", " + state.isRecentArea() + ", " +
                state.isRecentCountry() + ", " + state.isRecentPeople() + ", " + state.isSymptom() + ", " + state.isAbnormal() + ", " +
                state.getHealthCodeType() + ", " + state.isOutSchool() + ", " + state.isOutCity() + ");";
        Date date = new Date();
        System.out.println(date + " addState: sql = " + sql);
        return DBUtil.update(sql);
    }

    public Integer selectState(String startTime, String endTime, String collegeName1, Integer userType, String userNum) throws SQLException {
        String sql = "SELECT * FROM `State` WHERE stateTime BETWEEN " + startTime + " AND " + endTime;
        if (userType == 0) {
            sql += ";";
        } else if (userType == 1) {
            String sqlCollegeName = "SELECT `collegeName` FROM `User` WHERE `userNum` = " + userNum;
            ResultSet resultSet = DBUtil.select(sqlCollegeName);
            String collegeName2 = null;
            if (resultSet != null && !resultSet.next()) {
                collegeName2 = resultSet.getString("collegeName");
            }
            if (collegeName1.equals(collegeName2)) {
                sql += " AND collgeName = " + collegeName1 + ";";
            } else {
                Date date = new Date();
                System.out.println(date + " selectState: can't find any");
                return -1;
            }
        } else {
            sql += " AND userNum = " + userNum + ";";
        }
        Date date = new Date();
        System.out.println(date + " selectState: sql = " + sql);
        return DBUtil.update(sql);
    }
}
