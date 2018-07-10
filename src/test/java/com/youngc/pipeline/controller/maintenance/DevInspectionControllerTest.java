package com.youngc.pipeline.controller.maintenance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngc.pipeline.model.DevInspectionModel;
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
public class DevInspectionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getList() throws Exception{
        mockMvc.perform(get("/inspection/getList")
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
    public void postInfo()throws Exception {
        DevInspectionModel devInspectionModel = new DevInspectionModel();

        devInspectionModel.setDevId(new Long(2));
        devInspectionModel.setPlanName("111");
        devInspectionModel.setExeTime("2018-7-30");
        devInspectionModel.setExeCycle(new Long(9));
        devInspectionModel.setExeUser("张三");
        devInspectionModel.setExeDesc("描述");
        devInspectionModel.setRemark("");
        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(post("/inspection")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(devInspectionModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.devId").value(equalTo(2)))
                .andExpect(jsonPath("$.data.planName").value(equalTo("111")))
                .andExpect(jsonPath("$.data.exeTime").value(equalTo("2018-7-30")))
                .andExpect(jsonPath("$.data.exeCycle").value(equalTo(9)))
                .andExpect(jsonPath("$.data.exeUser").value(equalTo("张三")))
                .andExpect(jsonPath("$.data.exeDesc").value(equalTo("描述")))
        ;
    }

    @Test
    @Transactional
    public void deleteInfo()throws Exception {
        mockMvc.perform(delete("/inspection/del")
                .param("idList", "4")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getDetails()throws Exception {
        mockMvc.perform(get("/inspection/4")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    @Transactional
    public void putInfo() throws Exception{
        DevInspectionModel devInspectionModel = new DevInspectionModel();
        devInspectionModel.setId(new Long(4));
        devInspectionModel.setDevId(new Long(2));
        devInspectionModel.setPlanName("111");
        devInspectionModel.setExeTime("2018-7-30");
        devInspectionModel.setExeCycle(new Long(9));
        devInspectionModel.setExeUser("张三");
        devInspectionModel.setExeDesc("描述");
        devInspectionModel.setRemark("");
        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(put("/inspection")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(devInspectionModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.devId").value(equalTo(2)))
                .andExpect(jsonPath("$.data.planName").value(equalTo("111")))
                .andExpect(jsonPath("$.data.exeTime").value(equalTo("2018-7-30")))
                .andExpect(jsonPath("$.data.exeCycle").value(equalTo(9)))
                .andExpect(jsonPath("$.data.exeUser").value(equalTo("张三")))
                .andExpect(jsonPath("$.data.exeDesc").value(equalTo("描述")))
        ;
    }

    @Test
    public void getModel() throws Exception{
        mockMvc.perform(get("/inspection/getDev")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }
}