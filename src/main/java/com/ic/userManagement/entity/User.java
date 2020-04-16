package com.ic.userManagement.entity;

import java.util.Date;

/*本应用群的用户对象*/
public class User {
    /*数据库id*/
    private Integer id;
    /*用户id号，用作外键*/
    private Integer userId;
    /*用户名*/
    private String userName;
    /*密码*/
    private String password;
    /*对象创建时间*/
    private Date creatTime;
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


}
