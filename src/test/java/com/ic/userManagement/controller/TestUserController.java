package com.ic.userManagement.controller;

import com.ic.userManagement.service.UserService;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringJUnit4ClassRunner.class)//表示该测试用例是运用junit4进行测试，也可以换成其他测试框架
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
@WebAppConfiguration //1 此注解指定web资源的位置，默认为src/main/webapp

public class TestUserController {
    private MockMvc mockMvc; //2 模拟MVC对象

    @Resource
    private UserService userService;//3 在测试用例注入spring的bean

    @Resource
    WebApplicationContext wac;// 4.注入WebApplicationContext

    @Resource
    MockHttpSession session; //5 注入模拟的http session


    @Before //7 测试开始前的初始化工作
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build(); //2
    }

    @Test
    public void testGetUserByUserId() throws Exception {
        String responseString = mockMvc.perform(get("/user/getUserByUserId").param("userId","123"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }

    @Test
    public void testGetAll() throws Exception {
        String responseString = mockMvc.perform(get("/user/getAll"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseString);
    }
}
