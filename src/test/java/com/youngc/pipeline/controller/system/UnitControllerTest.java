package com.youngc.pipeline.controller.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import com.youngc.pipeline.model.UnitModel;
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
public class UnitControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUnitList() throws Exception{
        mockMvc.perform(get("/unit")
                .param("keyword", "")
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
    public void getUnitDetails() throws Exception{
        mockMvc.perform(get("/unit/7"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.msg").value(equalTo("操作成功！")))
                .andExpect(jsonPath("$.data.unitId").isNumber())
                .andExpect(jsonPath("$.data.unitCode").isString())
                .andExpect(jsonPath("$.data.unitName").isString())
                .andExpect(jsonPath("$.data.phoneOne").isString())
                .andExpect(jsonPath("$.data.contactOne").isString())
                .andExpect(jsonPath("$.data.email").isString())
                .andExpect(jsonPath("$.data.address").isString())
        ;
    }

    @Test
    @Transactional
    public void getUnitByCode()throws Exception {
        mockMvc.perform(get("/unit/code")
                .param("code","001")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data",notNullValue()));
        ;
    }

    @Test
    @Transactional
    public void putUser()throws Exception {
        UnitModel unitModel = new UnitModel();
        Long unitId = new Long(7);
        Long ordId = new Long(1);
        unitModel.setUnitId(unitId);
        unitModel.setOrgId(ordId);
        unitModel.setAddress("青岛市");
        unitModel.setContactOne("张三");
        unitModel.setContactTwo("");
        unitModel.setEmail("126365@126.com");
        unitModel.setPhoneOne("15698546325");
        unitModel.setPhoneTwo("");
        unitModel.setRemark("");
        unitModel.setUnitName("单位1");
        unitModel.setUnitCode("000");

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(put("/unit")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(unitModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.unitId").value(equalTo(7)))
                .andExpect(jsonPath("$.data.unitCode").value(equalTo("000")))
                .andExpect(jsonPath("$.data.unitName").value(equalTo("单位1")))
                .andExpect(jsonPath("$.data.phoneOne").value(equalTo("15698546325")))
                .andExpect(jsonPath("$.data.contactOne").value(equalTo("张三")))
                .andExpect(jsonPath("$.data.email").value(equalTo("126365@126.com")))
                .andExpect(jsonPath("$.data.address").value(equalTo("青岛市")))
        ;

    }
    @Test
    @Transactional
    public void postUser()throws Exception {
        UnitModel unitModel = new UnitModel();
        Long ordId = new Long(1);
        unitModel.setOrgId(ordId);
        unitModel.setAddress("青岛市");
        unitModel.setContactOne("张三");
        unitModel.setContactTwo("");
        unitModel.setEmail("126365@126.com");
        unitModel.setPhoneOne("15698546325");
        unitModel.setPhoneTwo("");
        unitModel.setRemark("");
        unitModel.setUnitName("单位1");
        unitModel.setUnitCode("000");

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/unit")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(unitModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.unitCode").value(equalTo("000")))
                .andExpect(jsonPath("$.data.unitName").value(equalTo("单位1")))
                .andExpect(jsonPath("$.data.phoneOne").value(equalTo("15698546325")))
                .andExpect(jsonPath("$.data.contactOne").value(equalTo("张三")))
                .andExpect(jsonPath("$.data.email").value(equalTo("126365@126.com")))
                .andExpect(jsonPath("$.data.address").value(equalTo("青岛市")))
        ;
    }

    @Test
    @Transactional
    public void deleteUserList()throws Exception {
        mockMvc.perform(delete("/unit")
                .param("unitId", "7,8")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }
}