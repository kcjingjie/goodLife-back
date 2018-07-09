package com.youngc.pipeline.controller.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngc.pipeline.model.DictionaryQueryModel;
import com.youngc.pipeline.model.DictionaryValueModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
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
@AutoConfigureMockMvc
@SpringBootTest
public class DictionaryQueryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getDictionaryList()throws Exception {
        mockMvc.perform(get("/dict/get")
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
    @Transactional
    public void postDict()throws Exception  {
        DictionaryQueryModel dictionaryQueryModel = new DictionaryQueryModel();

        Long userId = new Long(1);
        dictionaryQueryModel.setDictValue("1");
        dictionaryQueryModel.setDictName("字典名称");
        dictionaryQueryModel.setRemark("备注");
        dictionaryQueryModel.setStatus(1);

        dictionaryQueryModel.setAddPerson(userId);
        dictionaryQueryModel.setLastPerson(userId);

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/dict/postDict")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(dictionaryQueryModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.dictValue").value(equalTo("1")))
                .andExpect(jsonPath("$.data.dictName").value(equalTo("字典名称")))
                .andExpect(jsonPath("$.data.status").value(equalTo(1)))
                .andExpect(jsonPath("$.data.remark").value(equalTo("备注")))
                .andExpect(jsonPath("$.data.addPerson").value(equalTo(1)))
                .andExpect(jsonPath("$.data.lastPerson").value(equalTo(1)))
        ;
    }

    @Test
    @Transactional
    public void deleteDictList()throws Exception  {
        mockMvc.perform(delete("/dict/deleteDict")
                .param("idList", "37")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getDictInfo()throws Exception  {
        mockMvc.perform(get("/dict/34"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.msg").value(equalTo("操作成功！")))

        ;
    }

    @Test
    @Transactional
    public void putDictInfo()throws Exception  {
        DictionaryQueryModel dictionaryQueryModel = new DictionaryQueryModel();
        Long id = new Long(34);
        Long userId = new Long(1);
        dictionaryQueryModel.setId(id);
        dictionaryQueryModel.setDictValue("1");
        dictionaryQueryModel.setDictName("字典名称");
        dictionaryQueryModel.setRemark("备注");
        dictionaryQueryModel.setStatus(1);

        dictionaryQueryModel.setLastPerson(userId);

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(put("/dict/putDict")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(dictionaryQueryModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.dictValue").value(equalTo("1")))
                .andExpect(jsonPath("$.data.dictName").value(equalTo("字典名称")))
                .andExpect(jsonPath("$.data.status").value(equalTo(1)))
                .andExpect(jsonPath("$.data.remark").value(equalTo("备注")))
                .andExpect(jsonPath("$.data.lastPerson").value(equalTo(1)))
        ;
    }

    @Test
    public void getInfoByValue()throws Exception  {
        mockMvc.perform(get("/dict/getByValue")
                .param("value","sex"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.msg").value(equalTo("操作成功！")))

        ;
    }

    @Test
    public void getDictValueList() throws Exception {
        mockMvc.perform(get("/dict/getDictValueList")
                .param("dictValue", "sex")
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
    public void postDictValue()throws Exception  {
        DictionaryValueModel dictionaryValueModel = new DictionaryValueModel();

        Long userId = new Long(1);
        dictionaryValueModel.setDictValue("type");
        dictionaryValueModel.setDataName("A");
        dictionaryValueModel.setRemark("");
        dictionaryValueModel.setDataValue(1);

        dictionaryValueModel.setAddPerson(userId);
        dictionaryValueModel.setLastPerson(userId);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/dict/postDictValue")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(dictionaryValueModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.dataName").value(equalTo("A")))
                .andExpect(jsonPath("$.data.dictValue").value(equalTo("type")))
                .andExpect(jsonPath("$.data.dataValue").value(equalTo(1)))
                .andExpect(jsonPath("$.data.remark").value(equalTo("")))
                .andExpect(jsonPath("$.data.addPerson").value(equalTo(1)))
                .andExpect(jsonPath("$.data.lastPerson").value(equalTo(1)))
        ;
    }

    @Test
    @Transactional
    public void deleteDictValueList()throws Exception  {
        mockMvc.perform(delete("/dict/deleteDictValue")
                .param("idList", "37")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
        ;
    }

    @Test
    public void getDictValueInfo() throws Exception {
        mockMvc.perform(get("/dict/getValue/34"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.msg").value(equalTo("操作成功！")))

        ;
    }

    @Test
    @Transactional
    public void putDictValueInfo() throws Exception {
        DictionaryValueModel dictionaryValueModel = new DictionaryValueModel();

        Long userId = new Long(1);
        Long id = new Long(34);
        dictionaryValueModel.setId(id);
        dictionaryValueModel.setDataName("A");
        dictionaryValueModel.setRemark("");
        dictionaryValueModel.setDataValue(1);

        dictionaryValueModel.setAddPerson(userId);
        dictionaryValueModel.setLastPerson(userId);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(put("/dict/putDictValue")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(dictionaryValueModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.dataName").value(equalTo("A")))
                .andExpect(jsonPath("$.data.dataValue").value(equalTo(1)))
                .andExpect(jsonPath("$.data.remark").value(equalTo("")))
                .andExpect(jsonPath("$.data.lastPerson").value(equalTo(1)))
        ;
    }

    @Test
    public void getDictValueInfoByValue()throws Exception  {
        mockMvc.perform(get("/dict/getDictValueByValue")
                .param("dictValue", "sex")
                .param("dataValue", "1")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data",notNullValue()));
    }

    @Test
    public void getDict() throws Exception {
        mockMvc.perform(get("/dict")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data",notNullValue()));
    }
}