package com.youngc.pipeline.controller.pipeline;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngc.pipeline.model.TypeManageModel;
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
public class TypeManageControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void getDictionaryList()throws Exception {
        mockMvc.perform(get("/type/getList")
                .param("keyWord", "")
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
    public void getIdByCode() throws Exception{
        mockMvc.perform(get("/type/getByValue")
                .param("value", "10002")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getTypeInfo() throws Exception {
        mockMvc.perform(get("/type/getInfo/7"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.msg").value(equalTo("操作成功！")))

        ;
    }

    @Test
    @Transactional
    public void putTypeInfo() throws Exception {
        TypeManageModel typeManageModel=new TypeManageModel();
        Long id = new Long(7);
        typeManageModel.setModelId(id);
        typeManageModel.setModelCode("10002");
        typeManageModel.setModelName("模型名字");
        typeManageModel.setModelType(id);
        typeManageModel.setModelEquip("丙烯酸乙酯");
        typeManageModel.setModelDesc("描述");
        typeManageModel.setStatus(id);
        typeManageModel.setLastPerson(id);

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(put("/type/put")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(typeManageModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.modelId").value(equalTo(7)))
                .andExpect(jsonPath("$.data.modelCode").value(equalTo("10002")))
                .andExpect(jsonPath("$.data.modelName").value(equalTo("模型名字")))
                .andExpect(jsonPath("$.data.modelType").value(equalTo(7)))
                .andExpect(jsonPath("$.data.modelEquip").value(equalTo("丙烯酸乙酯")))
                .andExpect(jsonPath("$.data.modelDesc").value(equalTo("描述")))
                .andExpect(jsonPath("$.data.status").value(equalTo(7)))
                .andExpect(jsonPath("$.data.lastPerson").value(equalTo(1)))
        ;
    }

    @Test
    @Transactional
    public void postRoleInfo() throws Exception {
        TypeManageModel typeManageModel=new TypeManageModel();
        Long id = new Long(7);
        typeManageModel.setModelCode("10002");
        typeManageModel.setModelName("模型名字");
        typeManageModel.setModelType(id);
        typeManageModel.setModelEquip("丙烯酸乙酯");
        typeManageModel.setModelDesc("描述");
        typeManageModel.setStatus(id);
        typeManageModel.setLastPerson(id);
        typeManageModel.setAddPerson(id);
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/type/post")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(typeManageModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.modelCode").value(equalTo("10002")))
                .andExpect(jsonPath("$.data.modelName").value(equalTo("模型名字")))
                .andExpect(jsonPath("$.data.modelType").value(equalTo(7)))
                .andExpect(jsonPath("$.data.modelEquip").value(equalTo("丙烯酸乙酯")))
                .andExpect(jsonPath("$.data.modelDesc").value(equalTo("描述")))
                .andExpect(jsonPath("$.data.status").value(equalTo(7)))
                .andExpect(jsonPath("$.data.lastPerson").value(equalTo(1)))
                .andExpect(jsonPath("$.data.addPerson").value(equalTo(1)))
        ;
    }

    @Test
    @Transactional
    public void deleteRoleList()  throws Exception{
        mockMvc.perform(delete("/type/deleteType")
                .param("idList", "7")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }
}