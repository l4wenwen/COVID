package cn.edu.zust.service;

import cn.edu.zust.util.DBUtil;
import cn.edu.zust.vo.User;
import cn.edu.zust.vo.Vacation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacationService {
    public List<Vacation> excuteVacationQuery(String sql) throws SQLException {
        ResultSet rs = DBUtil.select(sql);
        List<Vacation> vacations = new ArrayList<>();
        while(rs.next()) {
            int id = rs.getInt("vid");
            String reason = rs.getString("vreason");
            String startTime = rs.getString("vstart_time");
            String endTime = rs.getString("vend_time");
            String requestTime = rs.getString("vrequest_time");
            String way = rs.getString("vtransport");
            String userId = rs.getString("user_id");
            String userName = rs.getString("user_name");
            int state = rs.getInt("vstate");
            Vacation vacation = new Vacation(id, reason, startTime, endTime, requestTime, way, userId, userName, state);
            vacations.add(vacation);
        }
        return vacations;
    }

    public List<Vacation> getVacationListById(String user_id) throws SQLException {
        String sql = "SELECT vacation.*, user.user_name FROM vacation, user WHERE vacation.user_id = user.user_id and user.user_id = " + user_id;
        return excuteVacationQuery(sql);
    }

    public List<Vacation> getVacationListByDepId(int depNo) throws SQLException {
        String sql = "SELECT vacation.*, user_name FROM vacation, (SELECT user_id, user_name FROM user WHERE dept_no = " +
                depNo + ") t WHERE t.user_id = vacation.user_id";
        return excuteVacationQuery(sql);
    }

    public boolean submitVacationRequest(User user, Vacation vacation) {
        String sql = "INSERT INTO vacation(vreason, vstart_time, vend_time, vstate, user_id, vrequest_time, vtransport) VALUES('" + vacation.getReason()
                + "','" + vacation.getStartTime() + "','" + vacation.getEndTime() + "','" +
                vacation.getState() + "','" + user.getUserNum() + "','" + vacation.getRequestTime() + "','" +
                vacation.getWay() + "')";
        return DBUtil.update(sql);
    }

    public boolean revokeRequest(String userId, String vid) throws SQLException {
        String sqlUpdate = "DELETE FROM vacation WHERE user_id=" + userId + " and vid=" + vid;
        String sqlCheck = "SELECT vstate FROM vacation WHERE user_id=" + userId + " and vid=" + vid;
        ResultSet rs = DBUtil.select(sqlCheck);
        boolean isRevoked = false;
        if (rs != null && rs.next()) {
            int state = rs.getInt("vstate");
            if (state == Vacation.STATE_PENDING)
                isRevoked = DBUtil.update(sqlUpdate);
        }
        return isRevoked;
    }

    public boolean performDecision(int vid, int operation) {
        String sql = "UPDATE vacation SET vstate = " + operation + " WHERE vid = " + vid;
        return DBUtil.update(sql);
    }
}
