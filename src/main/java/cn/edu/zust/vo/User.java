package cn.edu.zust.vo;

public class User {
    private String userNum;
    private Integer collegeNum;
    private Integer majorNum;
    private Integer classNum;
    private Integer userType;
    private String userName;
    private String password;
    private boolean sex;
    private String telephone;
    private String state;

    public User() {
    }

    /**
     *
     * @param userName      用户姓名
     * @param userNum       用户编号
     * @param sex           用户性别
     * @param collegeNum    学院编号
     * @param majorNum      专业编号
     * @param password      密码
     * @param classNum      班级编号
     * @param userType      用户类别(0管理员, 1老师, 2学生)
     */
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    /**
     * @type
     *  0 管理员
     *  1 老师
     *  2 学生
     */
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}