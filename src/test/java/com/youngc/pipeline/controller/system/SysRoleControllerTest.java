package com.youngc.pipeline.controller.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngc.pipeline.model.SysRoleModel;
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
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SysRoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getRoleList()throws Exception {
        mockMvc.perform(get("/role/getList")
                .param("keyWord", "")
                .param("pageNum", "1")
                .param("pageSize","10")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                ;
    }

    @Test
    public void getRoleInfo()throws Exception {
        mockMvc.perform(get("/role/getInfo/2")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    @Transactional
    public void postRoleInfo()throws Exception {
        SysRoleModel sysRoleModel = new SysRoleModel();
        sysRoleModel.setRoleName("访客");
        sysRoleModel.setStatus(1);
        sysRoleModel.setRoleDesc("普通权限");
        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(post("/role/post")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(sysRoleModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.roleName").value(equalTo("访客")))
                .andExpect(jsonPath("$.data.roleDesc").value(equalTo("普通权限")))
                .andExpect(jsonPath("$.data.status").value(equalTo(1)))
                ;
    }

    @Test
    @Transactional
    public void putRoleInfo()throws Exception{
        Long id= new Long(2);
        SysRoleModel sysRoleModel = new SysRoleModel();
        sysRoleModel.setRoleId(id);
        sysRoleModel.setRoleName("访客");
        sysRoleModel.setStatus(1);
        sysRoleModel.setRoleDesc("普通权限");
        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(put("/role/put")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(sysRoleModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.roleName").value(equalTo("访客")))
                .andExpect(jsonPath("$.data.roleDesc").value(equalTo("普通权限")))
                .andExpect(jsonPath("$.data.status").value(equalTo(1)))
        ;
    }

    @Test
    @Transactional
    public void deleteRoleList() throws Exception{
        mockMvc.perform(delete("/role/del")
                .param("idList", "2,5")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getRoleTree() throws Exception{
        mockMvc.perform(get("/role/getTree/2")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    @Transactional
    public void setRoleModule()throws Exception {
        mockMvc.perform(put("/role/putRoleModule/100/1,2,3")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }
}