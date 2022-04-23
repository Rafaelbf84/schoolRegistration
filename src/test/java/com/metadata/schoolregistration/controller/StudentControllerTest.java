package com.metadata.schoolregistration.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/data.sql")
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getStudent() throws Exception {
        mockMvc.perform(get("/api/v1/students"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"firstName\":\"Albert\",\"lastName\":\"Einstein\"}")));
    }

    @Test
    public void getStudentsUnregistered() throws Exception {
        mockMvc.perform(get("/api/v1/students/unregistered"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void postStudent() throws Exception {
        mockMvc.perform(post("/api/v1/students").contentType(MediaType.APPLICATION_JSON).content("{\"firstName\":\"Rafael\",\"lastName\":\"Ferreira\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("\"firstName\":\"Rafael\",\"lastName\":\"Ferreira\"}")));
    }

    @Test
    public void getStudentById() throws Exception {
        mockMvc.perform(get("/api/v1/students/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1,\"firstName\":\"Albert\",\"lastName\":\"Einstein\"}")));
    }

    @Test
    public void deleteStudent() throws Exception {
        mockMvc.perform(delete("/api/v1/students/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
