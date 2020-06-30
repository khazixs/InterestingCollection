package com.ic.userManagement.service;

import com.ic.userManagement.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)//表示该测试用例是运用junit4进行测试，也可以换成其他测试框架
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
@WebAppConfiguration //1 此注解指定web资源的位置，默认为src/main/webapp
public class TestRedis {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedis() {
        User u = new User();
        u.setUserId(1);
        u.setId(1);
        u.setPhone("13402823693");
        u.setPassword("123456");
        redisTemplate.opsForHash().put("users", u.getId(), u);
        System.out.println(((User)redisTemplate.opsForHash().get("users", u.getId())).toString());
    }

    @Test
    public void testHash() {
        User u = new User();
        u.setId(1);
        redisTemplate.opsForHash().put("users", u.getId(), u);
        System.out.println(redisTemplate.opsForHash().get("users", u.getId()));
    }
}
