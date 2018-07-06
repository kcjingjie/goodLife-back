package com.youngc.pipeline.controller.pipeline;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngc.pipeline.model.DevModelMonParaModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.security.acl.LastOwnerException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DevModelMonParaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getList()throws Exception{
        mockMvc.perform(get("/devModelMonPara/getList")
                .param("modelId", "3")
                .param("pageNum", "1")
                .param("pageSize","10")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data",notNullValue()))
        ;
    }

    @Test
    @Transactional
    public void postInfo()throws Exception{
        DevModelMonParaModel devModelMonParaModel = new DevModelMonParaModel();

        Long modelId=new Long(1);
        Long userId = new Long(1);
        devModelMonParaModel.setModelId(modelId);
        devModelMonParaModel.setParaName("电压");
        devModelMonParaModel.setParaId("10001");
        devModelMonParaModel.setParaDataType(1);
        devModelMonParaModel.setParaType(1);
        devModelMonParaModel.setParaUnit("V");
        devModelMonParaModel.setRemark("");
        devModelMonParaModel.setAddPerson(userId);
        devModelMonParaModel.setLastPerson(userId);

        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(post("/devModelMonPara")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(devModelMonParaModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.modelId").value(equalTo(1)))
                .andExpect(jsonPath("$.data.paraName").value(equalTo("电压")))
                .andExpect(jsonPath("$.data.paraId").value(equalTo("10001")))
                .andExpect(jsonPath("$.data.paraDataType").value(equalTo(1)))
                .andExpect(jsonPath("$.data.paraUnit").value(equalTo("V")))
                .andExpect(jsonPath("$.data.paraType").value(equalTo(1)))
                .andExpect(jsonPath("$.data.remark").value(equalTo("")))
               /* .andExpect(jsonPath("$.data.addPerson").value(equalTo(28)))
                .andExpect(jsonPath("$.data.lastPerson").value(equalTo(28)))*/
        ;
    }

    @Test
    public void getDetails()throws Exception{
        mockMvc.perform(get("/devModelMonPara/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.msg").value(equalTo("操作成功！")))

        ;
    }

    @Test
    @Transactional
    public void putInfo()throws Exception{
        DevModelMonParaModel devModelMonParaModel = new DevModelMonParaModel();

        Long id =new Long(1);
        Long modelId=new Long(1);
        Long userId = new Long(1);
        devModelMonParaModel.setId(id);
        devModelMonParaModel.setModelId(modelId);
        devModelMonParaModel.setParaName("电压");
        devModelMonParaModel.setParaId("10001");
        devModelMonParaModel.setParaDataType(1);
        devModelMonParaModel.setParaType(1);
        devModelMonParaModel.setParaUnit("V");
        devModelMonParaModel.setRemark("");
        devModelMonParaModel.setAddPerson(userId);
        devModelMonParaModel.setLastPerson(userId);

        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(put("/devModelMonPara")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(devModelMonParaModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.modelId").value(equalTo(1)))
                .andExpect(jsonPath("$.data.paraName").value(equalTo("电压")))
                .andExpect(jsonPath("$.data.paraId").value(equalTo("10001")))
                .andExpect(jsonPath("$.data.paraDataType").value(equalTo(1)))
                .andExpect(jsonPath("$.data.paraUnit").value(equalTo("V")))
                .andExpect(jsonPath("$.data.paraType").value(equalTo(1)))
                .andExpect(jsonPath("$.data.remark").value(equalTo("")))

        ;
    }

    @Test
    @Transactional
    public void deleteInfo()throws Exception{
        mockMvc.perform(delete("/devModelMonPara/del")
                .param("idList", "7")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getInfoByCode()throws Exception{
        mockMvc.perform(get("/devModelMonPara/code")
                .param("modelId", "3")
                .param("paraId", "10002")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data",notNullValue()));
    }
}
