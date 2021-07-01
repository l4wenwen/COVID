package cn.edu.zust.vo;


public class Statistic {
    private Integer totalNum;
    private Integer filledNum;
    private Integer highRiskNum;
    private Integer passRiskAreaNum;

    /**
     *
     * @param totalNum          总检测人数
     * @param filledNum         已填写人数
     * @param highRiskNum       高风险人数
     * @param passRiskAreaNum   经过风险地区人数
     */
    public Statistic(Integer totalNum, Integer filledNum, Integer highRiskNum, Integer passRiskAreaNum) {
        this.totalNum = totalNum;
        this.filledNum = filledNum;
        this.highRiskNum = highRiskNum;
        this.passRiskAreaNum = passRiskAreaNum;
    }

    public Statistic() {
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getFilledNum() {
        return filledNum;
    }

    public void setFilledNum(Integer filledNum) {
        this.filledNum = filledNum;
    }

    public Integer getHighRiskNum() {
        return highRiskNum;
    }

    public void setHighRiskNum(Integer highRiskNum) {
        this.highRiskNum = highRiskNum;
    }

    public Integer getPassRiskAreaNum() {
        return passRiskAreaNum;
    }

    public void setPassRiskAreaNum(Integer passRiskAreaNum) {
        this.passRiskAreaNum = passRiskAreaNum;
    }
}
