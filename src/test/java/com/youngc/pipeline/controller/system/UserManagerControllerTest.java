package com.youngc.pipeline.controller.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngc.pipeline.model.UserManagerModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserManagerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUserList()throws Exception {
        mockMvc.perform(get("/user")
                .param("keyword", "")
                .param("pageNum", "1")
                .param("pageSize","10")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data").isArray())
                ;
    }

    @Test
    public void getUserDetails() throws Exception{
        mockMvc.perform(get("/user/28"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.msg").value(equalTo("操作成功！")))
                .andExpect(jsonPath("$.data.userId").isNumber())
                .andExpect(jsonPath("$.data.userSex").isNumber())
                .andExpect(jsonPath("$.data.userAddress").isString())
                .andExpect(jsonPath("$.data.userEmail").isString())
                .andExpect(jsonPath("$.data.userName").isString())
                .andExpect(jsonPath("$.data.realName").isString())
                .andExpect(jsonPath("$.data.userPhone").isString())
        ;
    }

    @Test
    @Transactional
    public void putUser() throws Exception{
        UserManagerModel usersManagerModel = new UserManagerModel();
        Long id = new Long(28);
        Long sex = new Long(1);
        Long unit = new Long(8);
        usersManagerModel.setUserId(id);
        usersManagerModel.setUserName("admin1");
        usersManagerModel.setRealName("超级管理员");
        usersManagerModel.setUserPhone("15636985642");
        usersManagerModel.setUserAddress("青岛市");
        usersManagerModel.setUserEmail("admin@126.com");
        usersManagerModel.setUserSex(sex);
        usersManagerModel.setUnitId(unit);
        usersManagerModel.setLastPerson(id);
        usersManagerModel.setRoleIds("1");
        usersManagerModel.setDroleIds("2");

        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(put("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(usersManagerModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.unitId").value(equalTo(8)))
                .andExpect(jsonPath("$.data.userName").value(equalTo("admin1")))
                .andExpect(jsonPath("$.data.realName").value(equalTo("超级管理员")))
                .andExpect(jsonPath("$.data.userAddress").value(equalTo("青岛市")))
                .andExpect(jsonPath("$.data.userEmail").value(equalTo("admin@126.com")))
                .andExpect(jsonPath("$.data.userSex").value(equalTo(1)))
                .andExpect(jsonPath("$.data.userPhone").value(equalTo("15636985642")))
        ;
    }

    @Test
    @Transactional
    public void postUser()throws Exception {
        UserManagerModel usersManagerModel = new UserManagerModel();
        Long id = new Long(28);
        Long sex = new Long(1);
        Long unit = new Long(8);
        usersManagerModel.setPassword("123456");
        usersManagerModel.setUserName("admin1");
        usersManagerModel.setRealName("超级管理员");
        usersManagerModel.setUserPhone("15636985642");
        usersManagerModel.setUserAddress("青岛市");
        usersManagerModel.setUserEmail("admin@126.com");
        usersManagerModel.setUserSex(sex);
        usersManagerModel.setUnitId(unit);
        usersManagerModel.setLastPerson(id);
        usersManagerModel.setRoleIds("1");
        usersManagerModel.setDroleIds("2");

        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(usersManagerModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.unitId").value(equalTo(8)))
                .andExpect(jsonPath("$.data.userName").value(equalTo("admin1")))
                .andExpect(jsonPath("$.data.realName").value(equalTo("超级管理员")))
                .andExpect(jsonPath("$.data.userAddress").value(equalTo("青岛市")))
                .andExpect(jsonPath("$.data.userEmail").value(equalTo("admin@126.com")))
                .andExpect(jsonPath("$.data.userSex").value(equalTo(1)))
                .andExpect(jsonPath("$.data.userPhone").value(equalTo("15636985642")))
        ;
    }


    @Test
    @Transactional
    public void deleteUser() throws Exception{
        mockMvc.perform(delete("/user/28")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    @Transactional
    public void deleteUserList()throws Exception {
        mockMvc.perform(delete("/user")
                .param("deleteIds", "28,29")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    @Transactional
    public void updatePassword() throws Exception{
        mockMvc.perform(post("/user/28/password")
                .param("userId","28")
                .param("password", "123")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getUnitList()throws Exception {
        mockMvc.perform(get("/user/getUnitList"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.msg").value(equalTo("操作成功！")))

        ;
    }

    @Test
    public void getRoleList()throws Exception {
        mockMvc.perform(get("/user/getRoleList"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.msg").value(equalTo("操作成功！")))

        ;
    }

    @Test
    public void getDataRoleList() throws Exception{
        mockMvc.perform(get("/user/getDataRoleList"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.msg").value(equalTo("操作成功！")))

        ;
    }
}