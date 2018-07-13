package com.youngc.pipeline.controller.maintenance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngc.pipeline.model.DevLeakModel;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DevLeakControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getList() throws Exception{
        mockMvc.perform(get("/leakManager/getList")
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
        DevLeakModel devLeakModel = new DevLeakModel();

        devLeakModel.setDevId(new Long(2));
        devLeakModel.setHandleMethod("111");
        devLeakModel.setOccurTime("2018-7-30");
        devLeakModel.setLeakNo("0001");
        devLeakModel.setLeakDegree("1");
        devLeakModel.setPlanExeTime("描述");
        devLeakModel.setRemark("2018-8-01");
        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(post("/leakManager")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(devLeakModel)))
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
        mockMvc.perform(delete("/leakManager/del")
                .param("idList", "4")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getDetails()throws Exception {
        mockMvc.perform(get("/leakManager/4")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    @Transactional
    public void putInfo() throws Exception{
        DevLeakModel devLeakModel = new DevLeakModel();
        devLeakModel.setId(new Long(4));
        devLeakModel.setDevId(new Long(2));
        devLeakModel.setDevId(new Long(2));
        devLeakModel.setHandleMethod("111");
        devLeakModel.setOccurTime("2018-7-30");
        devLeakModel.setLeakNo("0001");
        devLeakModel.setLeakDegree("1");
        devLeakModel.setPlanExeTime("描述");
        devLeakModel.setRemark("2018-8-01");
        devLeakModel.setRemark("");
        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(put("/leakManager")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(devLeakModel)))
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
        mockMvc.perform(get("/leakManager/getDev")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }
}