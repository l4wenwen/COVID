package cn.edu.zust.vo;

import java.util.Date;

public class Vacation {
    private int id;
    private String reason;
    private String startTime;
    private String endTime;
    private String requestTime;
    private String way;
    private String userId;
    private String userName;
    private int state;
    public static final int STATE_PENDING = 0;
    public static final int STATE_REJECT = 1;
    public static final int STATE_ACCEPT = 2;

    public Vacation() {
    }

    public Vacation(int id, String reason, String startTime, String endTime, String requestTime, String way, String userId, String userName, int state) {
        this.id = id;
        this.reason = reason;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requestTime = requestTime;
        this.way = way;
        this.userId = userId;
        this.userName = userName;
        this.state = state;
    }

    public Vacation(int id, String reason, String startTime, String endTime, String requestTime, String way, String userId, int state) {
        this.id = id;
        this.reason = reason;
        this.startTime = startTime;
        this.endTime = endTime;
        this.requestTime = requestTime;
        this.way = way;
        this.userId = userId;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
