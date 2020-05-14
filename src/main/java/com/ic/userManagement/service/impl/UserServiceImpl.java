package com.ic.userManagement.service.impl;

import com.ic.userManagement.dao.UserDao;
import com.ic.userManagement.entity.User;
import com.ic.userManagement.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

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
        /*判断User是否已经创建,主要判据是电话号是否已经存在了*/
        User oldUser = userDao.selectByPhone(user.getPhone());
        if (oldUser!=null){
            return oldUser;
        }
        int res = userDao.insertUser(user);
        if (res == 1){
            return userDao.selectByPhone(user.getPhone());
        }
        return null;
    }
}
