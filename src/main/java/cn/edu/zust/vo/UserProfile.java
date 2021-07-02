package cn.edu.zust.vo;


public class UserProfile {
    private String userNum;
    private String userName;
    private String sex;
    private String collegeName;
    private String majorName;
    private String userType;

    public UserProfile(String userNum, String userName, String sex, String collegeName, String majorName, String userType) {
        this.userNum = userNum;
        this.userName = userName;
        this.sex = sex;
        this.collegeName = collegeName;
        this.majorName = majorName;
        this.userType = userType;
    }

    public UserProfile() {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
