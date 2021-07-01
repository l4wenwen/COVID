package cn.edu.zust.vo;

public class Vacation {
    private Integer vacationNum;
    private String reason;
    private String startTime;
    private String endTime;
    private String requestTime;
    private String way;
    private String userNum;
    private String userName;
    private Integer state;
    public static final int STATE_PENDING = 0;
    public static final int STATE_REJECT = 1;
    public static final int STATE_ACCEPT = 2;

    public Vacation() {
    }

    public Vacation(Integer vacationNum, String reason, String startTime, String endTime, String requestTime, String way, String userNum, String userName, Integer state) {
        this.vacationNum = vacationNum;
        this.reason = reason;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requestTime = requestTime;
        this.way = way;
        this.userNum = userNum;
        this.userName = userName;
        this.state = state;
    }

    public Vacation(Integer vacationNum, String reason, String startTime, String endTime, String requestTime, String way, String userNum, Integer state) {
        this.vacationNum = vacationNum;
        this.reason = reason;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requestTime = requestTime;
        this.way = way;
        this.userNum = userNum;
        this.state = state;
    }

    public Integer getVacationNum() {
        return vacationNum;
    }

    public void setVacationNum(Integer vacationNum) {
        this.vacationNum = vacationNum;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public static int getStatePending() {
        return STATE_PENDING;
    }

    public static int getStateReject() {
        return STATE_REJECT;
    }

    public static int getStateAccept() {
        return STATE_ACCEPT;
    }
}
