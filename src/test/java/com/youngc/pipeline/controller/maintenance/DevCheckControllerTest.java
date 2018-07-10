package com.youngc.pipeline.controller.maintenance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngc.pipeline.model.DevCheckModel;
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
public class DevCheckControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getList() throws Exception{
        mockMvc.perform(get("/check/getList")
                .param("keyWord", "")
                .param("devName","")
                .param("pageNum", "1")
                .param("pageSize","10")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    @Transactional
    public void postInfo() throws Exception{
        DevCheckModel devCheckModel = new DevCheckModel();

        devCheckModel.setDevId(new Long(1));
        devCheckModel.setPlanName("计划名称");
        devCheckModel.setExeTime("2018-12-12");
        devCheckModel.setCheckOrganize("检验机构");
        devCheckModel.setCheckUser("李四");
        devCheckModel.setExeCycle(new Long(7));
        devCheckModel.setExeUser("王五");
        devCheckModel.setExeDesc("描述");
        devCheckModel.setRemark("");
        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(post("/check")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(devCheckModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.devId").value(equalTo(1)))
                .andExpect(jsonPath("$.data.planName").value(equalTo("计划名称")))
                .andExpect(jsonPath("$.data.exeTime").value(equalTo("2018-12-12")))
                .andExpect(jsonPath("$.data.exeCycle").value(equalTo(7)))
                .andExpect(jsonPath("$.data.checkOrganize").value(equalTo("检验机构")))
                .andExpect(jsonPath("$.data.checkUser").value(equalTo("李四")))
                .andExpect(jsonPath("$.data.exeUser").value(equalTo("王五")))
                .andExpect(jsonPath("$.data.exeDesc").value(equalTo("描述")))
        ;
    }

    @Test
    @Transactional
    public void deleteInfo() throws Exception{
        mockMvc.perform(delete("/check/del")
                .param("idList", "3,4")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getDetails() throws Exception{
        mockMvc.perform(get("/check/3")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    @Transactional
    public void putInfo() throws Exception{
        DevCheckModel devCheckModel = new DevCheckModel();
        devCheckModel.setId(new Long(3));
        devCheckModel.setDevId(new Long(1));
        devCheckModel.setPlanName("计划名称");
        devCheckModel.setExeTime("2018-12-12");
        devCheckModel.setCheckOrganize("检验机构");
        devCheckModel.setCheckUser("李四");
        devCheckModel.setExeCycle(new Long(7));
        devCheckModel.setExeUser("王五");
        devCheckModel.setExeDesc("描述");
        devCheckModel.setRemark("");
        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(put("/check")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(devCheckModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.devId").value(equalTo(1)))
                .andExpect(jsonPath("$.data.planName").value(equalTo("计划名称")))
                .andExpect(jsonPath("$.data.exeTime").value(equalTo("2018-12-12")))
                .andExpect(jsonPath("$.data.exeCycle").value(equalTo(7)))
                .andExpect(jsonPath("$.data.checkOrganize").value(equalTo("检验机构")))
                .andExpect(jsonPath("$.data.checkUser").value(equalTo("李四")))
                .andExpect(jsonPath("$.data.exeUser").value(equalTo("王五")))
                .andExpect(jsonPath("$.data.exeDesc").value(equalTo("描述")))
        ;
    }
}