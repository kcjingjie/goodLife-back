package com.youngc.pipeline.service.controller.system;

import com.youngc.pipeline.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UnitControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    private String token;

    @Before
    public void init() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    //查询单位信息
    @Test
    public void getUnitList() throws Exception {
        String result = mvc.perform(get("/unit")
                .param("keyword", "")
                .param("pageNum", "1")
                .param("pageSize", "10")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString();



        mvc.perform(get("/unit")
                .param("keyword", "")
                .param("pageNum", "1")
                .param("pageSize", "10")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("code\":\"200")));
    }

}
