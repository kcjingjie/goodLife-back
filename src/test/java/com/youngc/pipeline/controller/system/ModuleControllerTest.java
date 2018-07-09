package com.youngc.pipeline.controller.system;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.Mapping;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ModuleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void getTree() throws Exception{

    }

    @Test
    public void getModuleTree()throws Exception {
    }

    @Test
    public void getGroupInfo()throws Exception {
    }

    @Test
    public void getControlId() throws Exception{
    }

    @Test
    public void getOrgInfo()throws Exception {
    }

    @Test
    public void postGroupInfo()throws Exception {
    }

    @Test
    public void deleteModule()throws Exception {
    }
}