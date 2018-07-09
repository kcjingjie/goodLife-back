package com.youngc.pipeline.controller.pipeline;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngc.pipeline.model.DevUnitModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.LOCATION_FORWARD;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DevUnitControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void getList() throws Exception{
        mockMvc.perform(get("/devUnit/getList")
                .param("deviceId", "8")
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
       DevUnitModel devUnitModel = new DevUnitModel();

        Long deviceId = new Long(1);
        Long userId = new Long(1);
        Long unitId = new Long(1);
        devUnitModel.setDeviceId(deviceId);
        devUnitModel.setUnitName("法兰");
        devUnitModel.setUnitVersion("V-1.2");
        devUnitModel.setUnitNumber(unitId);
        devUnitModel.setUnitMaterial("铁");
        devUnitModel.setRemark("");
        devUnitModel.setAddPerson(userId);
        devUnitModel.setLastPerson(userId);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/devUnit")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(devUnitModel)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.deviceId").value(equalTo(1)))
                .andExpect(jsonPath("$.data.unitName").value(equalTo("法兰")))
                .andExpect(jsonPath("$.data.unitVersion").value(equalTo("V-1.2")))
                .andExpect(jsonPath("$.data.unitNumber").value(equalTo(1)))
                .andExpect(jsonPath("$.data.unitMaterial").value(equalTo("铁")))
                .andExpect(jsonPath("$.data.remark").value(equalTo("")))
                .andExpect(jsonPath("$.data.addPerson").value(equalTo(1)))
                .andExpect(jsonPath("$.data.lastPerson").value(equalTo(1)))
        ;
    }

    @Test
    public void getDetails() throws Exception{
        mockMvc.perform(get("/devUnit/1"))
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
        DevUnitModel devUnitModel = new DevUnitModel();

        Long id = new Long(1);
        Long deviceId = new Long(1);
        Long userId = new Long(1);
        Long unitId = new Long(1);
        devUnitModel.setId(id);
        devUnitModel.setDeviceId(deviceId);
        devUnitModel.setUnitName("法兰");
        devUnitModel.setUnitVersion("V-1.2");
        devUnitModel.setUnitNumber(unitId);
        devUnitModel.setUnitMaterial("铁");
        devUnitModel.setRemark("");
        devUnitModel.setAddPerson(userId);
        devUnitModel.setLastPerson(userId);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(put("/devUnit")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(devUnitModel)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.deviceId").value(equalTo(1)))
                .andExpect(jsonPath("$.data.unitName").value(equalTo("法兰")))
                .andExpect(jsonPath("$.data.unitVersion").value(equalTo("V-1.2")))
                .andExpect(jsonPath("$.data.unitNumber").value(equalTo(1)))
                .andExpect(jsonPath("$.data.unitMaterial").value(equalTo("铁")))
                .andExpect(jsonPath("$.data.remark").value(equalTo("")))
                .andExpect(jsonPath("$.data.lastPerson").value(equalTo(1)))
        ;
    }

    @Test
    @Transactional
    public void deleteInfo() throws Exception{
        mockMvc.perform(delete("/devUnit/del")
                .param("idList", "1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }
}