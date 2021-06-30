package cn.edu.zust.vo;

import java.util.Date;

public class Vacation {
    private String reason;
    private Date startTime;
    private Date endTime;
    private String way;

    public Vacation() {
    }

    public Vacation(String reason, Date startTime, Date endTime, String way) {
        this.reason = reason;
        this.startTime = startTime;
        this.endTime = endTime;
        this.way = way;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }
}
