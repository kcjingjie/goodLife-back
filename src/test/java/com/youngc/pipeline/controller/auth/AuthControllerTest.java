package com.youngc.pipeline.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngc.pipeline.Application;
import com.youngc.pipeline.result.PlatformPageResult;
import com.youngc.pipeline.result.ResultCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AuthControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    private String token;

    @Before
    public void init() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    //login接口测试
    @Test
    public void login() throws Exception {
        String result = mvc.perform(post("/auth/login")
                .param("userName", "admin")
                .param("password", "123456")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("admin")))
                .andReturn()
                .getResponse().getContentAsString();


        mvc.perform(post("/auth/login")
                .param("userName", "admin")
                .param("password", "1234567")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("code\":\"10002")));
    }

    @Test
    public void logout() throws Exception {
        MvcResult result = mvc.perform(post("/auth/login")
                .param("userName", "admin")
                .param("password", "123456")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        PlatformPageResult r = objectMapper.readValue(result.getResponse().getContentAsString(), PlatformPageResult.class);
        Map user = (Map) r.getData();

        mvc.perform(get("/auth/logout")
                .param("token", user.get("token").toString())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("code\":\"200")));
    }
}
