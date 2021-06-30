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
    private String userNum;
    private boolean sex;
    private String college;
    private String major;
    private String password;
    private Integer classNum;
    private int userType;

    public User() {
    }

    public User(String name, String userNum, boolean sex, String college, String major, String password, Integer classNum, int userType) {
        this.name = name;
        this.userNum = userNum;
        this.sex = sex;
        this.college = college;
        this.major = major;
        this.password = password;
        this.classNum = classNum;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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