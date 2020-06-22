package com.ic.userManagement.controller;

import com.ic.userManagement.entity.ServerResponse;
import com.ic.userManagement.entity.User;
import com.ic.userManagement.service.UserService;
import com.ic.userManagement.utils.CheckUp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        User user = (User) request.getAttribute("user");
        user.setCreatedTime(Long.toString(System.currentTimeMillis() / 1000L));
        user.setAuthority(1);//最低权限
        user.setUserLevel(1);
        user.setVersion(1L);
        // TODO: 2020/6/6 短信验证
        /*电话号码不能为空，且必须满足要求*/
        if (!CheckUp.checkUpParameter("phone", user.getPhone())) {
            return null;
        }
        /*用户密码不能为空，且必须满足要求*/
        if (!CheckUp.checkUpParameter("password", user.getPassword())) {
            return null;
        }

        if (user.getSex() != null) {
            if (!user.getSex().equals("male") && !user.getSex().equals("female")) {
                return null;
            }
        }
        if (user.getEmail() != null) {
            if (!CheckUp.checkUpParameter("email", user.getEmail())) {
                return null;
            }
        }
        if (user.getUserName() != null) {
            if (!CheckUp.checkUpParameter("name", user.getUserName())) {
                return null;
            }
        }
        User res = userService.createUser(user);
        HttpSession session = request.getSession();
        session.setAttribute("user", res);
        /*设置session过期时间为30分钟*/
        session.setMaxInactiveInterval(30 * 60);/*以秒为单位*/
        return res;
    }

    @GetMapping("/login")
    public User login(HttpServletRequest request) {
        // TODO: 2020/6/6 短信验证登录
        User requestUser = (User) request.getAttribute("user");
        if (!CheckUp.checkUpParameter("phone", requestUser.getPhone())) {
            return null;
        }
        if (!CheckUp.checkUpParameter("password", requestUser.getPassword())) {
            return null;
        }
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("user");

        if (sessionUser != null) {
            if (sessionUser.getPhone().equals(requestUser.getPhone()) && sessionUser.getPassword().equals(requestUser.getPassword())) {
                session.setMaxInactiveInterval(30 * 60);
                return sessionUser;
            }
        }
        return userService.login(requestUser);
    }

    @GetMapping("/updateUser")
    public ServerResponse updateUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User target = (User) request.getAttribute("user");
        //防止越权问题，我们将传过来的用户Id设置为Session里面获取当前登录用户的Id
        User current = (User) session.getAttribute("user");
        if (current == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }

        if (!CheckUp.checkUpParameter("name",target.getUserName())){
            target.setUserName(current.getUserName());
        }


        if (target.getPassword()!=null&& !target.getPassword().equals(current.getPassword())){
            /*此时需要修改密码*/
            /*先判断新密码合不合法*/
            if (!CheckUp.checkUpParameter("password",target.getUserName())){
                /*新密码不合法*/
                return ServerResponse.createByErrorMessage("新密码不合法");
            }else{
                // TODO: 2020/6/6 短信验证，或者输入原密码
            }
        }

        target.setCreatedTime(current.getCreatedTime());

        if (!CheckUp.checkUpParameter("type",target.getUserType())){
            target.setUserType(current.getUserType());
        }

        if (!CheckUp.checkUpParameter("phone",target.getPhone())){
            target.setPhone(current.getPhone());
        }

        if (!target.getSex().equals("male")&&!target.getSex().equals("female")){
            target.setSex(current.getSex());
        }

        target.setSweatHeart(current.getSweatHeart());

        target.setAuthority(current.getAuthority());

        if (target.getDescription() == null){
            target.setDescription(current.getDescription());
        }

        if (!CheckUp.checkUpParameter("email",target.getEmail())){
            target.setEmail(current.getEmail());
        }

        target.setUserLevel(current.getUserLevel());

        target.setCoin(current.getCoin());

        target.setVersion(current.getVersion()==0?1:current.getVersion()+1);

        User res = userService.updateUser(target);

        if (res!=null){
            session.setAttribute("user",res);
            return new ServerResponse(true,"更新成功");
        }
        return ServerResponse.createByErrorMessage("更新失败");
    }

}
