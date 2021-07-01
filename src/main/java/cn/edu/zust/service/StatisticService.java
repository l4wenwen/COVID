package cn.edu.zust.service;

import cn.edu.zust.util.DBUtil;
import cn.edu.zust.vo.Statistic;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatisticService {
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
        String sql = "SELECT * FROM statistic WHERE submitDate = '" + currentDate + "'";
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
}
