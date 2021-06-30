package cn.edu.zust.vo;

/**
 * type:
 *  0 管理员
 *  1 老师
 *  2 学生
 *
 */

public class User {
    private String name;
    private String userNum;
    private boolean sex;
    private int departmentNum;
    private String password;
    private Integer classNum;
    private int userType;

    public User() {
    }

    public User(String name, String userNum, boolean sex, int departmentNum, String password, Integer classNum, int userType) {
        this.name = name;
        this.userNum = userNum;
        this.sex = sex;
        this.departmentNum = departmentNum;
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

    public int getDepartmentNum() {
        return departmentNum;
    }

    public void setDepartmentNum(int departmentNum) {
        this.departmentNum = departmentNum;
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