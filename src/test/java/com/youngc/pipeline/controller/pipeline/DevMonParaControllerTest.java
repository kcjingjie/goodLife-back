package com.youngc.pipeline.controller.pipeline;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngc.pipeline.model.DevMonParaModel;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DevMonParaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getList()throws Exception{
        mockMvc.perform(get("/devMonPara/getList")
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
    public void getListAll()throws Exception{
        mockMvc.perform(get("/devMonPara/getListAll")
                .param("deviceId","8")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data",notNullValue()));
        ;
    }

    @Test
    @Transactional
    public void postInfo()throws Exception{
        DevMonParaModel devMonParaModel = new DevMonParaModel();
        Long id = new Long(10);
        Long userId = new Long(1);
        devMonParaModel.setDeviceId(id);
        devMonParaModel.setParaName("电流");
        devMonParaModel.setParaId("10002");
        devMonParaModel.setParaDataType(1);
        devMonParaModel.setParaType(1);
        devMonParaModel.setParaUnit("A");
        devMonParaModel.setRemark("");
        devMonParaModel.setAddPerson(userId);
        devMonParaModel.setLastPerson(userId);

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/devMonPara")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(devMonParaModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.deviceId").value(equalTo(10)))
                .andExpect(jsonPath("$.data.paraName").value(equalTo("电流")))
                .andExpect(jsonPath("$.data.paraId").value(equalTo("10002")))
                .andExpect(jsonPath("$.data.paraDataType").value(equalTo(1)))
                .andExpect(jsonPath("$.data.paraUnit").value(equalTo("A")))
                .andExpect(jsonPath("$.data.paraType").value(equalTo(1)))
                .andExpect(jsonPath("$.data.remark").value(equalTo("")))
                ;
    }

    @Test
    public void getDetails()throws Exception{
        mockMvc.perform(get("/devMonPara/9"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.msg").value(equalTo("操作成功！")))

        ;
    }

    @Test
    @Transactional
    public void putInfo()throws Exception{
        DevMonParaModel devMonParaModel = new DevMonParaModel();
        Long id = new Long(10);
        Long deviceId = new Long(10);
        Long userId = new Long(1);
        devMonParaModel.setId(id);
        devMonParaModel.setDeviceId(deviceId);
        devMonParaModel.setParaName("电流");
        devMonParaModel.setParaId("10002");
        devMonParaModel.setParaDataType(1);
        devMonParaModel.setParaType(1);
        devMonParaModel.setParaUnit("A");
        devMonParaModel.setRemark("");
        devMonParaModel.setAddPerson(userId);
        devMonParaModel.setLastPerson(userId);

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(put("/devMonPara")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(devMonParaModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.deviceId").value(equalTo(10)))
                .andExpect(jsonPath("$.data.paraName").value(equalTo("电流")))
                .andExpect(jsonPath("$.data.paraId").value(equalTo("10002")))
                .andExpect(jsonPath("$.data.paraDataType").value(equalTo(1)))
                .andExpect(jsonPath("$.data.paraUnit").value(equalTo("A")))
                .andExpect(jsonPath("$.data.paraType").value(equalTo(1)))
                .andExpect(jsonPath("$.data.remark").value(equalTo("")))
        ;
    }

    @Test
    @Transactional
    public void deleteInfo()throws Exception{
        mockMvc.perform(delete("/devMonPara/del")
                .param("idList", "10")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getInfoByCode()throws Exception{
        mockMvc.perform(get("/devMonPara/code")
                .param("deviceId", "8")
                .param("paraId", "10002")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data",notNullValue()));
    }


}
