package cn.edu.zust.service;

import cn.edu.zust.util.DBUtil;
import cn.edu.zust.vo.State;

public class StateService {
    public boolean addState(State state) {
        String sql = "INSERT INTO `State` (stateNum, userNum, stateTime, isTemperature, isCovid, isLikeCovid, quarantine, " +
                "isRecentArea, isRecentCountry, isRecentPeople, symptom, isAbnormal, healthCodeType, isOutSchool, isOutCity) VALUES (" +
                state.getStateNum() + ", " + state.getUserNum() + ", " + state.getStateTime() + ", " + state.isTemperature() + ", " +
                state.isCovid() + ", " + state.isLikeCovid() + ", " + state.getQuarantine() + ", " + state.isRecentArea() + ", " +
                state.isRecentCountry() + ", " + state.isRecentPeople() + ", " + state.isSymptom() + ", " + state.isAbnormal() + ", " +
                state.getHealthCodeType() + ", " + state.isOutSchool() + ", " + state.isOutCity() + ");";
        return DBUtil.update(sql);
    }
}
