package cn.edu.zust.service;

import cn.edu.zust.util.DBUtil;
import cn.edu.zust.vo.User;
import cn.edu.zust.vo.Vacation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacationService {
    public List<Vacation> executeVacationQuery(String sql) throws SQLException {
        ResultSet rs = DBUtil.select(sql);
        List<Vacation> vacations = new ArrayList<>();
        while(rs.next()) {
            int id = rs.getInt("vacationNum");
            String reason = rs.getString("reason");
            String startTime = rs.getString("startTime");
            String endTime = rs.getString("endTime");
            String requestTime = rs.getString("requestTime");
            String way = rs.getString("way");
            String userId = rs.getString("userNum");
            int state = rs.getInt("state");
            Vacation vacation = new Vacation(id, reason, startTime, endTime, requestTime, way, userId, state);
            vacations.add(vacation);
        }
        return vacations;
    }

    public List<Vacation> getVacationListById(String userNum) throws SQLException {
        String sql = "SELECT vacation.*, user.userName FROM vacation, user WHERE vacation.userNum = user.userNum and user.userNum = " + userNum;
        return executeVacationQuery(sql);
    }

    public List<Vacation> getVacationListByDepId(int collegeNum) throws SQLException {
        String sql = "SELECT vacation.*, userName FROM vacation, (SELECT userNum, userName FROM user WHERE collegeNum = " +
                collegeNum + ") t WHERE t.userNum = vacation.userNum";
        return executeVacationQuery(sql);
    }

    public boolean submitVacationRequest(User user, Vacation vacation) {
        String sql = "INSERT INTO vacation(reason, startTime, endTime, state, userNum, requestTime, way) VALUES('" + vacation.getReason()
                + "','" + vacation.getStartTime() + "','" + vacation.getEndTime() + "','" +
                vacation.getState() + "','" + user.getUserNum() + "','" + vacation.getRequestTime() + "','" +
                vacation.getWay() + "')";
        return DBUtil.update(sql) == 1;
    }

    public boolean revokeRequest(String userNum, String vacationNum) throws SQLException {
        String sqlUpdate = "DELETE FROM vacation WHERE userNum=" + userNum + " and vid=" + vacationNum;
        String sqlCheck = "SELECT state FROM vacation WHERE userNum=" + userNum + " and vid=" + vacationNum;
        ResultSet rs = DBUtil.select(sqlCheck);
        boolean isRevoked = false;
        if (rs != null && rs.next()) {
            int state = rs.getInt("state");
            if (state == Vacation.STATE_PENDING)
                isRevoked = DBUtil.update(sqlUpdate) == 1;
        }
        return isRevoked;
    }

    public boolean performDecision(int vacationNum, int operation) {
        String sql = "UPDATE vacation SET state = " + operation + " WHERE vacationNum = " + vacationNum;
        return DBUtil.update(sql) == 1;
    }
}
