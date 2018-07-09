package com.youngc.pipeline.controller.system;

import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UnitContorllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUnitList() {
    }

    @Test
    public void getUnitDetails() {
    }

    @Test
    public void getUnitByCode() {
    }

    @Test
    public void putUser() {
    }

    @Test
    public void postUser() {
    }

    @Test
    public void deleteUserList() {
    }
}