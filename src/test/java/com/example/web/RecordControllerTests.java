package com.example.web;


import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.domain.Record;
import com.example.service.RecordService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecordControllerTests {
    private static final Long DUMMY_ID = 1L;
    private static final Date DUMMY_DATE = new Date();
    private static final String DUMMY_NAME = "ozaki";
    private static final Integer DUMMY_VALUE = 1;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecordService recordService;

    @Before
    public void setup() {
        Record record = new Record(DUMMY_DATE, DUMMY_NAME, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE);
        record.setId(DUMMY_ID);
        when(recordService.findAll()).thenReturn(new ArrayList<Record>());
        when(recordService.findOne(DUMMY_ID)).thenReturn(record);
        when(recordService.save(record)).thenReturn(record);

    }

    @Test
    public void index() throws Exception {
        mockMvc.perform(get("/records"))
            .andExpect(view().name("records/index"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Listing Records")))
            .andDo(print());
    }

    @Test
    public void newRecord() throws Exception {
        mockMvc.perform(get("/records/new"))
            .andExpect(view().name("records/new"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("New Record")))
            .andDo(print());
        }

    @Test
    public void show() throws Exception {

        mockMvc.perform(get("/records/" + DUMMY_ID))
            .andExpect(view().name("records/show"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Show Record")))
            .andDo(print());
    }

    @Test
    public void edit() throws Exception {
        mockMvc.perform(get("/records/" + DUMMY_ID + "/edit"))
            .andExpect(view().name("records/edit"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Editing Record")))
            .andDo(print());
    }

    @Test
    public void create() throws Exception {
        mockMvc.perform(
                post("/records")
                    .param("date", new SimpleDateFormat("yyyy/MM/dd").format(DUMMY_DATE))
                    .param("name", DUMMY_NAME)
                    .param("pa", DUMMY_VALUE.toString())
                    .param("hit", DUMMY_VALUE.toString())
                    .param("rbi", DUMMY_VALUE.toString())
                    .param("bb", DUMMY_VALUE.toString())
                    .param("k", DUMMY_VALUE.toString()))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("/records"))
            .andDo(print());
    }

    @Test
    public void createInvalid() throws Exception {
        mockMvc.perform(post("/records"))
            .andExpect(view().name("records/new"))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(
                put("/records/" + DUMMY_ID)
                    .param("date", new SimpleDateFormat("yyyy/MM/dd").format(DUMMY_DATE))
                    .param("name", DUMMY_NAME)
                    .param("pa", DUMMY_VALUE.toString())
                    .param("hit", DUMMY_VALUE.toString())
                    .param("rbi", DUMMY_VALUE.toString())
                    .param("bb", DUMMY_VALUE.toString())
                    .param("k", DUMMY_VALUE.toString()))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("/records"))
            .andDo(print());
    }

    @Test
    public void updateInvalid() throws Exception {
        mockMvc.perform(put("/records/" + DUMMY_ID))
            .andExpect(view().name("records/edit"))
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    public void destroy() throws Exception {
        mockMvc.perform(delete("/records/" + DUMMY_ID))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("/records"))
            .andDo(print());
    }
}
