package cn.edu.zust.vo;

public class State {
    private boolean isTemperature;
    private boolean isCovid;
    private boolean isLikeCovid;
    private String quarantine;
    private boolean isRecentArea;
    private boolean isRecentCountry;
    private boolean isRecentPeople;
    private boolean symptom;
    private boolean isAbnormal;
    private String healthCodeColor;
    private boolean isOutSchool;
    private boolean isOutCity;

    public State() {
    }

    public State(boolean isTemperature, boolean isCovid, boolean isLikeCovid, String quarantine, boolean isRecentArea, boolean isRecentCountry, boolean isRecentPeople, boolean symptom, boolean isAbnormal, String healthCodeColor, boolean isOutSchool, boolean isOutCity) {
        this.isTemperature = isTemperature;
        this.isCovid = isCovid;
        this.isLikeCovid = isLikeCovid;
        this.quarantine = quarantine;
        this.isRecentArea = isRecentArea;
        this.isRecentCountry = isRecentCountry;
        this.isRecentPeople = isRecentPeople;
        this.symptom = symptom;
        this.isAbnormal = isAbnormal;
        this.healthCodeColor = healthCodeColor;
        this.isOutSchool = isOutSchool;
        this.isOutCity = isOutCity;
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

    public String getQuarantine() {
        return quarantine;
    }

    public void setQuarantine(String quarantine) {
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
        return symptom;
    }

    public void setSymptom(boolean symptom) {
        this.symptom = symptom;
    }

    public boolean isAbnormal() {
        return isAbnormal;
    }

    public void setAbnormal(boolean abnormal) {
        isAbnormal = abnormal;
    }

    public String getHealthCodeColor() {
        return healthCodeColor;
    }

    public void setHealthCodeColor(String healthCodeColor) {
        this.healthCodeColor = healthCodeColor;
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
