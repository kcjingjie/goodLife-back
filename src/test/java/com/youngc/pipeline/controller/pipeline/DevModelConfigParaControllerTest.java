package com.youngc.pipeline.controller.pipeline;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngc.pipeline.model.DevModelConfigParaModel;
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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DevModelConfigParaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getList()throws Exception{
        mockMvc.perform(get("/devModelConfig/getList")
                .param("modelId", "3")
                .param("pageNum", "1")
                .param("pageSize","10")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data",notNullValue())
        );
    }

    @Test
    @Transactional
    public void postInfo()throws Exception{
        DevModelConfigParaModel devModelConfigParaModel = new DevModelConfigParaModel();

        Long modelId = new Long(1);
        Long userId = new Long(1);
        devModelConfigParaModel.setModelId(modelId);
        devModelConfigParaModel.setParaName("温度");
        devModelConfigParaModel.setParaId("10001");
        devModelConfigParaModel.setParaValue("30");
        devModelConfigParaModel.setParaType(1);
        devModelConfigParaModel.setParaUnit("℃");
        devModelConfigParaModel.setRemark("");
        devModelConfigParaModel.setAddPerson(userId);
        devModelConfigParaModel.setLastPerson(userId);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/devModelConfig")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(devModelConfigParaModel)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.modelId").value(equalTo(1)))
                .andExpect(jsonPath("$.data.paraName").value(equalTo("温度")))
                .andExpect(jsonPath("$.data.paraId").value(equalTo("10001")))
                .andExpect(jsonPath("$.data.paraValue").value(equalTo("30")))
                .andExpect(jsonPath("$.data.paraUnit").value(equalTo("℃")))
                .andExpect(jsonPath("$.data.paraType").value(equalTo(1)))
                .andExpect(jsonPath("$.data.remark").value(equalTo("")))
        ;
    }

    @Test
    public void getDetails()throws Exception{
        mockMvc.perform(get("/devModelConfig/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.msg").value(equalTo("操作成功！")))

        ;
    }

    @Test
    @Transactional
    public void putInfo ()throws Exception{
        DevModelConfigParaModel devModelConfigParaModel = new DevModelConfigParaModel();

        Long modelId = new Long(1);
        Long userId = new Long(1);
        Long id = new Long(3);
        devModelConfigParaModel.setId(id);
        devModelConfigParaModel.setModelId(modelId);
        devModelConfigParaModel.setParaName("温度");
        devModelConfigParaModel.setParaId("10001");
        devModelConfigParaModel.setParaValue("30");
        devModelConfigParaModel.setParaType(1);
        devModelConfigParaModel.setParaUnit("℃");
        devModelConfigParaModel.setRemark("");
        devModelConfigParaModel.setAddPerson(userId);
        devModelConfigParaModel.setLastPerson(userId);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(put("/devModelConfig")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(devModelConfigParaModel)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.modelId").value(equalTo(1)))
                .andExpect(jsonPath("$.data.paraName").value(equalTo("温度")))
                .andExpect(jsonPath("$.data.paraId").value(equalTo("10001")))
                .andExpect(jsonPath("$.data.paraValue").value(equalTo("30")))
                .andExpect(jsonPath("$.data.paraUnit").value(equalTo("℃")))
                .andExpect(jsonPath("$.data.paraType").value(equalTo(1)))
                .andExpect(jsonPath("$.data.remark").value(equalTo("")))
        ;
    }

    @Test
    @Transactional
    public void deleteInfo()throws Exception{
        mockMvc.perform(delete("/devModelConfig/del")
                .param("idList", "3")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getInfoByCode()throws Exception{
        mockMvc.perform(get("/devModelConfig/code")
                .param("modelId", "3")
                .param("paraId", "10001")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data",notNullValue()));
    }
}
