package com.youngc.pipeline.controller.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngc.pipeline.model.OrgModel;
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

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class OrgControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getTree() throws Exception{
        mockMvc.perform(get("/organization/tree")
                .param("keyword", "")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getOrgInfo()throws Exception {
        mockMvc.perform(get("/organization/41")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getOrgCode() throws Exception{
        mockMvc.perform(get("/organization/getOrgCode")
                .param("orgCode","001")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    @Transactional
    public void putOrgInfo() throws Exception{
        OrgModel orgModel = new OrgModel();

        orgModel.setOrgName("宁波");
        orgModel.setOrgCode("10001");
        orgModel.setOrgId(new Long(41));
        orgModel.setPid(new Long(0));
        orgModel.setOrgDesc("暂无描述");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(put("/organization")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(orgModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.orgName").value(equalTo("宁波")))
                .andExpect(jsonPath("$.data.orgDesc").value(equalTo("暂无描述")))
                .andExpect(jsonPath("$.data.orgCode").value(equalTo("10001")))
                .andExpect(jsonPath("$.data.pid").value(equalTo(0)))
        ;
    }

    @Test
    @Transactional
    public void postOrgInfo() throws Exception{
        OrgModel orgModel = new OrgModel();

        orgModel.setOrgName("宁波");
        orgModel.setOrgCode("10001");
        orgModel.setPid(new Long(41));
        orgModel.setOrgDesc("暂无描述");
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/organization")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(orgModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.orgName").value(equalTo("宁波")))
                .andExpect(jsonPath("$.data.orgDesc").value(equalTo("暂无描述")))
        ;
    }

    @Test
    @Transactional
    public void deleteOrg() throws Exception{
        mockMvc.perform(delete("/organization")
                .param("orgId", "41")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }
}