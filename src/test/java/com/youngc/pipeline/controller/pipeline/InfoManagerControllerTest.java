package com.youngc.pipeline.controller.pipeline;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngc.pipeline.model.PipeInfoModel;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class InfoManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void getOrgUnitTree()throws Exception {
        mockMvc.perform(get("/pipeInfo/orgUnitTree")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data",notNullValue()))
        ;
    }

    @Test
    public void getList() throws Exception{
        mockMvc.perform(get("/pipeInfo/getList")
                .param("keyWord", "")
                .param("pid","5")
                .param("pageNum", "1")
                .param("pageSize","10")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data",notNullValue()))
                ;
    }

    @Test
    @Transactional
    public void postInfo() throws Exception{
        PipeInfoModel pipeInfoModel = new PipeInfoModel();

        Long unitId = new Long(1);
        Long modelId = new Long(1);
        Long imageId = new Long(1);
        Long userId = new Long(1);
        pipeInfoModel.setUnitId(unitId);
        pipeInfoModel.setImageId(imageId);
        pipeInfoModel.setDeviceAlias("name");
        pipeInfoModel.setDeviceName("NAME-1");
        pipeInfoModel.setStatus(1);
        pipeInfoModel.setDeviceDesc("描述信息");

        pipeInfoModel.setAddPerson(userId);
        pipeInfoModel.setLastPerson(userId);

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/pipeInfo")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(pipeInfoModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.unitId").value(equalTo(1)))
                .andExpect(jsonPath("$.data.imageId").value(equalTo(1)))
                .andExpect(jsonPath("$.data.deviceAlias").value(equalTo("name")))
                .andExpect(jsonPath("$.data.deviceName").value(equalTo("NAME-1")))
                .andExpect(jsonPath("$.data.deviceDesc").value(equalTo("描述信息")))
                .andExpect(jsonPath("$.data.status").value(equalTo(1)))
                .andExpect(jsonPath("$.data.addPerson").value(equalTo(1)))
                .andExpect(jsonPath("$.data.lastPerson").value(equalTo(1)))
        ;
    }

    @Test
    public void getDetails()throws Exception {
        mockMvc.perform(get("/pipeInfo/7"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.msg").value(equalTo("操作成功！")))

        ;
    }

    @Test
    @Transactional
    public void putInfo() throws Exception{
        PipeInfoModel pipeInfoModel = new PipeInfoModel();

        Long id = new Long(7);
        Long modelId = new Long(1);
        Long imageId = new Long(1);
        Long userId = new Long(1);
        pipeInfoModel.setDeviceId(id);
        pipeInfoModel.setImageId(imageId);
        pipeInfoModel.setDeviceAlias("name");
        pipeInfoModel.setDeviceName("NAME-1");
        pipeInfoModel.setStatus(1);
        pipeInfoModel.setDeviceDesc("描述信息");

        pipeInfoModel.setAddPerson(userId);
        pipeInfoModel.setLastPerson(userId);

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(put("/pipeInfo")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(pipeInfoModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.imageId").value(equalTo(1)))
                .andExpect(jsonPath("$.data.deviceAlias").value(equalTo("name")))
                .andExpect(jsonPath("$.data.deviceName").value(equalTo("NAME-1")))
                .andExpect(jsonPath("$.data.deviceDesc").value(equalTo("描述信息")))
                .andExpect(jsonPath("$.data.status").value(equalTo(1)))
                .andExpect(jsonPath("$.data.lastPerson").value(equalTo(1)))
        ;
    }

    @Test
    @Transactional
    public void deleteInfo() throws Exception{
        mockMvc.perform(delete("/pipeInfo/del")
                .param("idList", "7")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getInfoByCode() throws Exception{
        mockMvc.perform(get("/pipeInfo/code")
                .param("code", "10002")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data",notNullValue()));
    }

    @Test
    public void getModel() throws Exception{
        mockMvc.perform(get("/pipeInfo/getModel")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data",notNullValue()));
    }

    @Test
    public void getImageUrl()throws Exception {
        mockMvc.perform(get("/pipeInfo/getImgUrl")
                .param("deviceId","14")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data",notNullValue()));
    }
}