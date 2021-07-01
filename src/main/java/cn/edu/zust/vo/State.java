package cn.edu.zust.vo;

/**
 * @quarantine
 * 0 未曾隔离
 * 1 曾隔离，已解除
 * 2 正在居家隔离
 * 3 正在集中隔离
 *
 * @healthCodeType
 *  0 绿色
 *  1 黄色
 *  2 红色
 */
public class State {
    private Integer stateNum;
    private String userNum;
    private String stateTime;
    private boolean isTemperature;
    private boolean isCovid;
    private boolean isLikeCovid;
    private Integer quarantine;
    private boolean isRecentArea;
    private boolean isRecentCountry;
    private boolean isRecentPeople;
    private boolean isSymptom;
    private boolean isAbnormal;
    private Integer healthCodeType;
    private boolean isOutSchool;
    private boolean isOutCity;

    public State() {
    }

    /**
     *
     * @param stateNum 打卡编号
     * @param userNum 用户编号
     * @param stateTime 打卡时间
     * @param isTemperature 今日体温是否正常
     * @param isCovid 是否确诊为新冠
     * @param isLikeCovid 是否疑似为新冠
     * @param quarantine 当前的隔离状态
     * @param isRecentArea 本人同住亲友近14天内是否途径或停留疫情中高风险地区
     * @param isRecentCountry 近14天是否曾在国(境)外旅行、居住
     * @param isRecentPeople 近14天是否曾接触过确诊或疑似新冠病例
     * @param isSymptom 今日是否有以下相关症状(乏力、干咳 、鼻塞、流涕、咽痛、腹泻)
     * @param isAbnormal 今日是否存在其他个人健康异常情况
     * @param healthCodeType 今日健康码颜色
     * @param isOutSchool 今日是否出校
     * @param isOutCity 今日是否出城
     */
    public State(Integer stateNum, String userNum, String stateTime, boolean isTemperature, boolean isCovid, boolean isLikeCovid, Integer quarantine, boolean isRecentArea, boolean isRecentCountry, boolean isRecentPeople, boolean isSymptom, boolean isAbnormal, Integer healthCodeType, boolean isOutSchool, boolean isOutCity) {
        this.stateNum = stateNum;
        this.userNum = userNum;
        this.stateTime = stateTime;
        this.isTemperature = isTemperature;
        this.isCovid = isCovid;
        this.isLikeCovid = isLikeCovid;
        this.quarantine = quarantine;
        this.isRecentArea = isRecentArea;
        this.isRecentCountry = isRecentCountry;
        this.isRecentPeople = isRecentPeople;
        this.isSymptom = isSymptom;
        this.isAbnormal = isAbnormal;
        this.healthCodeType = healthCodeType;
        this.isOutSchool = isOutSchool;
        this.isOutCity = isOutCity;
    }

    public Integer getStateNum() {
        return stateNum;
    }

    public void setStateNum(Integer stateNum) {
        this.stateNum = stateNum;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getStateTime() {
        return stateTime;
    }

    public void setStateTime(String stateTime) {
        this.stateTime = stateTime;
    }

    public boolean isTemperature() {
        return isTemperature;
    }

    public void setTemperature(boolean temperature) {
        isTemperature = temperature;
    }

    public boolean isCovid() {
        return isCovid;
    }

    public void setCovid(boolean covid) {
        isCovid = covid;
    }

    public boolean isLikeCovid() {
        return isLikeCovid;
    }

    public void setLikeCovid(boolean likeCovid) {
        isLikeCovid = likeCovid;
    }

    public Integer getQuarantine() {
        return quarantine;
    }

    public void setQuarantine(Integer quarantine) {
        this.quarantine = quarantine;
    }

    public boolean isRecentArea() {
        return isRecentArea;
    }

    public void setRecentArea(boolean recentArea) {
        isRecentArea = recentArea;
    }

    public boolean isRecentCountry() {
        return isRecentCountry;
    }

    public void setRecentCountry(boolean recentCountry) {
        isRecentCountry = recentCountry;
    }

    public boolean isRecentPeople() {
        return isRecentPeople;
    }

    public void setRecentPeople(boolean recentPeople) {
        isRecentPeople = recentPeople;
    }

    public boolean isSymptom() {
        return isSymptom;
    }

    public void setSymptom(boolean symptom) {
        isSymptom = symptom;
    }

    public boolean isAbnormal() {
        return isAbnormal;
    }

    public void setAbnormal(boolean abnormal) {
        isAbnormal = abnormal;
    }

    public Integer getHealthCodeType() {
        return healthCodeType;
    }

    public void setHealthCodeType(Integer healthCodeType) {
        this.healthCodeType = healthCodeType;
    }

    public boolean isOutSchool() {
        return isOutSchool;
    }

    public void setOutSchool(boolean outSchool) {
        isOutSchool = outSchool;
    }

    public boolean isOutCity() {
        return isOutCity;
    }

    public void setOutCity(boolean outCity) {
        isOutCity = outCity;
    }
}