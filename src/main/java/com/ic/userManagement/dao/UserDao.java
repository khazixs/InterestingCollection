package com.ic.userManagement.dao;


import com.ic.userManagement.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public interface UserDao {
    User selectByUserId(@Param("userId") int userId);

    User selectByPhone(@Param("phone") String phone);

    Integer insertUser(@Param("user") User user);

    Integer deleteById(@Param("id") int id);

    Integer updateById(@Param("user") User user);

    List<User> selectAll();
}
