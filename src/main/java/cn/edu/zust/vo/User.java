package cn.edu.zust.vo;

/**
 * @author sunyin
 *
 *  type
 *  0 管理员
 *  1 学生
 *  2 老师
 *
 */
public class User {
    private String name;
    private Integer userNum;
    private boolean sex;
    private String college;
    private String major;
    private Integer classNum;
    private int userType;

    public User() {
    }

    public User(String name, Integer userNum, boolean sex, String college, String major, Integer classNum, int userType) {
        this.name = name;
        this.userNum = userNum;
        this.sex = sex;
        this.college = college;
        this.major = major;
        this.classNum = classNum;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}