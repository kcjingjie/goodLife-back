package com.youngc.pipeline.controller.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngc.pipeline.model.ModuleModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.Mapping;

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
public class ModuleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void getTree() throws Exception{
        mockMvc.perform(get("/module/tree")
                .param("keyword","")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getModuleTree()throws Exception {
        mockMvc.perform(get("/module/moduleTree")
                .param("keyword","")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getGroupInfo()throws Exception {
        mockMvc.perform(get("/module/1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getControlId() throws Exception{
        mockMvc.perform(get("/module/getControlId")
                .param("controlId","menu1")
                .param("moduleId","2")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    @Transactional
    public void putInfo()throws Exception {

        ModuleModel moduleModel = new ModuleModel();
        moduleModel.setModuleName("模块");
        moduleModel.setIcon("");
        moduleModel.setControlId("menu00");
        moduleModel.setType(new Long(1));
        moduleModel.setStatus(new Long(1));
        moduleModel.setPid(new Long(1));
        moduleModel.setPriority(new Long(1));
        moduleModel.setModuleDesc("");
        moduleModel.setModuleId(new Long(1));
        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(put("/module")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(moduleModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.moduleId").value(equalTo(1)))
                .andExpect(jsonPath("$.data.pid").value(equalTo(1)))
                .andExpect(jsonPath("$.data.moduleName").value(equalTo("模块")))
                .andExpect(jsonPath("$.data.controlId").value(equalTo("menu00")))
                .andExpect(jsonPath("$.data.moduleDesc").value(equalTo("")))
                .andExpect(jsonPath("$.data.type").value(equalTo(1)))
                .andExpect(jsonPath("$.data.status").value(equalTo(1)))
                .andExpect(jsonPath("$.data.priority").value(equalTo(1)))
        ;

    }

    @Test
    @Transactional
    public void postInfo()throws Exception {
        ModuleModel moduleModel = new ModuleModel();
        moduleModel.setModuleName("模块");
        moduleModel.setIcon("");
        moduleModel.setControlId("menu00");
        moduleModel.setType(new Long(1));
        moduleModel.setStatus(new Long(1));
        moduleModel.setPid(new Long(1));
        moduleModel.setPriority(new Long(1));
        moduleModel.setModuleDesc("");
        moduleModel.setModuleId(new Long(1));
        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(post("/module")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(moduleModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.pid").value(equalTo(1)))
                .andExpect(jsonPath("$.data.moduleName").value(equalTo("模块")))
                .andExpect(jsonPath("$.data.moduleDesc").value(equalTo("")))
                .andExpect(jsonPath("$.data.type").value(equalTo(1)))
                .andExpect(jsonPath("$.data.status").value(equalTo(1)))
                .andExpect(jsonPath("$.data.priority").value(equalTo(1)))
        ;

    }

    @Test
    @Transactional
    public void deleteModule()throws Exception {
        mockMvc.perform(delete("/module")
                .param("moduleId", "1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }
}