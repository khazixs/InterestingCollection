package com.ic.userManagement.controller;

import com.ic.userManagement.entity.User;
import com.ic.userManagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ResponseBody
@Controller("userController")
@RequestMapping("/user")

public class UserController {
    @Resource
    public UserService userService;

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/getUserByUserId")
    public User getUserByUserId(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        return userService.getByUserId(userId);
    }

    @GetMapping("/register")
    public User register(HttpServletRequest request) {
        // TODO: 2020/5/14 参数验证
        User user = (User) request.getAttribute("user");
        return userService.createUser(user);
    }
}
