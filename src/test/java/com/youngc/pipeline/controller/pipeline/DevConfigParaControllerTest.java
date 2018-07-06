package com.youngc.pipeline.controller.pipeline;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngc.pipeline.model.DevConfigParaModel;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

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
public class DevConfigParaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getList() throws Exception{
        mockMvc.perform(get("/devConfigPara/getList")
                .param("deviceId", "1")
                .param("pageNum", "1")
                .param("pageSize","10")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data",notNullValue()))
               /* .andExpect(jsonPath("$.data[0].id").value(equalTo(44)))
                .andExpect(jsonPath("$.data[0].paraName").value(containsString("介质")))
                .andExpect(jsonPath("$.data[0].paraId").value(containsString("10001")))
                .andExpect(jsonPath("$.data[0].paraValue").value(containsString("丙烯")))*/
               ;

    }

    @Test
    public void getListAll() throws Exception{
        mockMvc.perform(get("/devConfigPara/getListAll")
                .param("deviceId","1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data",notNullValue()));
        ;
    }
    @Test
    @Transactional
    public void postInfo() throws Exception {
        DevConfigParaModel devConfigParaModel = new DevConfigParaModel();
        Long id = new Long(10);
        Long userId = new Long(1);
        devConfigParaModel.setDeviceId(id);
        devConfigParaModel.setParaName("管道长度");
        devConfigParaModel.setParaId("10002");
        devConfigParaModel.setParaValue("10");
        devConfigParaModel.setParaType(1);
        devConfigParaModel.setParaUnit("m");
        devConfigParaModel.setRemark("");
        devConfigParaModel.setAddPerson(userId);
        devConfigParaModel.setLastPerson(userId);

        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(post("/devConfigPara")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(devConfigParaModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.deviceId").value(equalTo(10)))
                .andExpect(jsonPath("$.data.paraName").value(equalTo("管道长度")))
                .andExpect(jsonPath("$.data.paraId").value(equalTo("10002")))
                .andExpect(jsonPath("$.data.paraValue").value(equalTo("10")))
                .andExpect(jsonPath("$.data.paraUnit").value(equalTo("m")))
                .andExpect(jsonPath("$.data.paraType").value(equalTo(1)))
                .andExpect(jsonPath("$.data.remark").value(equalTo("")))
                .andExpect(jsonPath("$.data.addPerson").value(equalTo(1)))
                .andExpect(jsonPath("$.data.lastPerson").value(equalTo(1)))
               ;

    }

    @Test
    public void getDetails()throws Exception{
        mockMvc.perform(get("/devConfigPara/37"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.msg").value(equalTo("操作成功！")))

            ;
               // .andExpect(jsonPath("$.data.id").value(equalTo(37)))
    }

    @Test
    @Transactional
    public void putInfo()throws Exception{
        DevConfigParaModel devConfigParaModel = new DevConfigParaModel();
        Long devId = new Long(10);
        Long userId = new Long(1);
        Long id = new Long(37);
        devConfigParaModel.setId(id);
        devConfigParaModel.setDeviceId(devId);
        devConfigParaModel.setParaName("管道长度");
        devConfigParaModel.setParaId("10002");
        devConfigParaModel.setParaValue("10");
        devConfigParaModel.setParaType(1);
        devConfigParaModel.setParaUnit("m");
        devConfigParaModel.setRemark("");
        devConfigParaModel.setLastPerson(userId);

        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(put("/devConfigPara")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(devConfigParaModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.deviceId").value(equalTo(10)))
                .andExpect(jsonPath("$.data.paraName").value(equalTo("管道长度")))
                .andExpect(jsonPath("$.data.paraId").value(equalTo("10002")))
                .andExpect(jsonPath("$.data.paraValue").value(equalTo("10")))
                .andExpect(jsonPath("$.data.paraUnit").value(equalTo("m")))
                .andExpect(jsonPath("$.data.paraType").value(equalTo(1)))
                .andExpect(jsonPath("$.data.remark").value(equalTo("")))
                .andExpect(jsonPath("$.data.lastPerson").value(equalTo(1)))
               ;
    }
    @Test
    @Transactional
    public void deleteInfo()throws Exception{
        mockMvc.perform(delete("/devConfigPara/del")
                .param("idList", "37")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;

    }

    @Test
    public void getInfoByCode()throws Exception{
        mockMvc.perform(get("/devConfigPara/code")
                .param("deviceId", "8")
                .param("paraId", "10002")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data",notNullValue()));
    }



}