package cn.edu.zust.vo;

/**
 * @type
 *  0 管理员
 *  1 老师
 *  2 学生
 *
 */
public class User {
    private String userNum;
    private Integer collegeNum;
    private Integer majorNum;
    private Integer classNum;
    private Integer userType;
    private String userName;
    private String password;
    private boolean sex;

    public User() {
    }

    public User(String userName, String userNum, boolean sex, Integer collegeNum, Integer majorNum, String password, Integer classNum, Integer userType) {
        this.userName = userName;
        this.userNum = userNum;
        this.sex = sex;
        this.collegeNum = collegeNum;
        this.majorNum = majorNum;
        this.password = password;
        this.classNum = classNum;
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Integer getCollegeNum() {
        return collegeNum;
    }

    public void setCollegeNum(Integer collegeNum) {
        this.collegeNum = collegeNum;
    }

    public Integer getMajorNum() {
        return majorNum;
    }

    public void setMajorNum(Integer majorNum) {
        this.majorNum = majorNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}