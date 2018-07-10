package com.youngc.pipeline.controller.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngc.pipeline.bean.param.DataRoleUnitBean;
import com.youngc.pipeline.model.SysDataRoleModel;
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
public class SysDataRoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getDataRoleList()throws Exception {
        mockMvc.perform(get("/dataRole/getList")
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
    public void getDataRoleInfo()throws Exception {
        mockMvc.perform(get("/dataRole/getInfo/1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    @Transactional
    public void postInfo() throws Exception{
        SysDataRoleModel sysDataRoleModel = new SysDataRoleModel();

        sysDataRoleModel.setDroleName("万华");
        sysDataRoleModel.setStatus(1);
        sysDataRoleModel.setDroleDesc("描述");
        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(post("/dataRole/post")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(sysDataRoleModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.droleName").value(equalTo("万华")))
                .andExpect(jsonPath("$.data.droleDesc").value(equalTo("描述")))
                .andExpect(jsonPath("$.data.status").value(equalTo(1)))
        ;
    }

    @Test
    @Transactional
    public void putDataRoleInfo() throws Exception{
        SysDataRoleModel sysDataRoleModel = new SysDataRoleModel();
        sysDataRoleModel.setDroleId(new Long(1));
        sysDataRoleModel.setDroleName("万华");
        sysDataRoleModel.setStatus(1);
        sysDataRoleModel.setDroleDesc("描述");
        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(put("/dataRole/put")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(sysDataRoleModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.droleName").value(equalTo("万华")))
                .andExpect(jsonPath("$.data.droleDesc").value(equalTo("描述")))
                .andExpect(jsonPath("$.data.status").value(equalTo(1)))
        ;
    }

    @Test
    @Transactional
    public void deleteList()throws Exception {
        mockMvc.perform(delete("/dataRole/del")
                .param("idList", "1,8")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getOrgUnitTree() throws Exception{
        mockMvc.perform(get("/dataRole/orgUnitTree")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getDataUnit()throws Exception {
        mockMvc.perform(get("/dataRole/DataUnit")
                .param("droleId","1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    @Transactional
    public void putDataUnit() throws Exception{
        DataRoleUnitBean dataRoleUnitBean = new DataRoleUnitBean();
        dataRoleUnitBean.setDroleId(new Long(1));
        dataRoleUnitBean.setUnitId("1,2,3,4");

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(put("/dataRole/putDataUnit")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(dataRoleUnitBean)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }
}