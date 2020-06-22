package com.ic.userManagement.service.impl;

import com.ic.userManagement.dao.UserDao;
import com.ic.userManagement.entity.User;
import com.ic.userManagement.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public User getByUserId(int userId) {
        if (userId > 0) {
            return userDao.selectByUserId(userId);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return userDao.selectAll();
    }

    @Override
    public User createUser(User user) {
        // TODO: 2020/5/16 短信验证码登录？
        /*判断User是否已经创建,主要判据是电话号是否已经存在了*/
        User oldUser = userDao.selectByPhone(user.getPhone());
        if (oldUser != null) {
            return oldUser;
        }
        int res = userDao.insertUser(user);
        /*创建对象成功则将新对象放入缓存中，并且返回给前端控制器*/
        if (res == 1) {
            User u = userDao.selectByPhone(user.getPhone());
            redisTemplate.opsForHash().put("users", u.getId(), u);
            return u;
        }
        return null;
    }

    @Override
    public User login(User user) {
        // TODO: 2020/5/16 再斟酌一下免登录的方式，想清楚缓存是用来做什么的，如何短信验证码登录,添加缓存

        User res;

        res = userDao.selectByPhone(user.getPhone());

        if (res == null) {
            return null;
        } else {
            return res.getPassword().equals(user.getPassword()) ? res : null;
        }
    }

    @Override
    public User updateUser(User user) {
        // TODO: 2020/5/16 更新用户信息 ，限定不同权限修改不同属性
        return null;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-*.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        User u = new User();
        u.setUserId(1);
        u.setId(1);
        u.setPhone("13402823693");
        u.setPassword("123456");
        System.out.println(userService.login(u).toString());
    }
}
