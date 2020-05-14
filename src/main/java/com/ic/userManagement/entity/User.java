package com.ic.userManagement.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/*本应用群的用户对象*/
public class User implements Serializable {
    /*数据库id*/
    private Integer id;
    /*用户id号，用作外键*/
    private Integer userId;
    /*用户名*/
    private String userName;
    /*密码*/
    private String password;
    /*对象创建时间*/
    private String createdTime;
    /*用户类型*/
    private Integer userType;
    /*电话号*/
    private String phone;
    /*性别，male和female*/
    private String sex;
    /*存储用户伴侣的userId*/
    private Integer sweatHeart;
    /*用户权限，用以区分管理员和普通用户*/
    private Integer authority;
    /*用户出生日期*/
    private String birthday;
    /*用户描述，个性签名*/
    private String description;
    /*用户绑定的邮箱*/
    private String email;
    /*用户个人等级*/
    private Integer userLevel;
    /*用户余额，积分*/
    private long coin;
    /*头像*/
    private byte[] headShot;
    /*对象版本号，用于确定该用户是否已经更新，避免多线程情况下的数据不一致问题*/
    private Long version;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", createdTime=" + createdTime +
                ", userType=" + userType +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", sweatHeart=" + sweatHeart +
                ", authority=" + authority +
                ", birthday='" + birthday + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", userLevel=" + userLevel +
                ", coin=" + coin +
                ", headShot=" + Arrays.toString(headShot) +
                ", version=" + version +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String  getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getSweatHeart() {
        return sweatHeart;
    }

    public void setSweatHeart(Integer sweatHeart) {
        this.sweatHeart = sweatHeart;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public long getCoin() {
        return coin;
    }

    public void setCoin(long coin) {
        this.coin = coin;
    }

    public byte[] getHeadShot() {
        return headShot;
    }

    public void setHeadShot(byte[] headShot) {
        this.headShot = headShot;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
