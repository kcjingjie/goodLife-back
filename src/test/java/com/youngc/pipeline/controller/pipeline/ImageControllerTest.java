package com.youngc.pipeline.controller.pipeline;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngc.pipeline.model.ImageMarkModel;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ImageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void uploadFileInfo()throws Exception {
    }

    @Test
    public void getFolderFileInfo()throws Exception {
        mockMvc.perform(get("/image/getList"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.msg").value(equalTo("操作成功！")))
        ;
    }

    @Test
    @Transactional
    public void deleteFileInfo()throws Exception {
    }

    @Test
    @Transactional
    public void postMarkInfo() throws Exception {
        ImageMarkModel imageMarkModel = new ImageMarkModel();
        Long imageId = new Long(1);
        Double x = new Double(23.36);
        Double y = new Double(36.23);
        Long userId = new Long(1);
        imageMarkModel.setImageId(imageId);
        imageMarkModel.setAxisX(x);
        imageMarkModel.setAxisY(y);
        imageMarkModel.setRemark("标注信息");

        imageMarkModel.setAddPerson(userId);
        imageMarkModel.setLastPerson(userId);

        ObjectMapper mapper = new ObjectMapper();

        //调用接口，传入添加的参数
        mockMvc.perform(post("/image/postImageMark")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(imageMarkModel)))
                //判断返回值，是否达到预期，
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                //使用jsonPath解析返回值，判断具体的内容
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.data.imageId").value(equalTo(1)))
                .andExpect(jsonPath("$.data.axisX").value(equalTo(23.36)))
                .andExpect(jsonPath("$.data.axisY").value(equalTo(36.23)))
                .andExpect(jsonPath("$.data.remark").value(equalTo("标注信息")))
                .andExpect(jsonPath("$.data.addPerson").value(equalTo(1)))
                .andExpect(jsonPath("$.data.lastPerson").value(equalTo(1)))
        ;
    }

    @Test
    public void getMarkList() throws Exception{
        mockMvc.perform(get("/image/getImageMarkList")
                .param("imageId","1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(jsonPath("$.code").value(containsString("200")))
                .andExpect(jsonPath("$.msg").value(equalTo("操作成功！")))

        ;
    }
}