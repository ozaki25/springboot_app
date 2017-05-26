package com.example.controller;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.model.Record;
import com.example.service.RecordService;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecordControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RecordService recordService;

    @Before
    public void setup() {
        recordService.deleteAll();
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
        Record record = recordService.save(new Record(new Date(), "ozaki", 1, 1, 1, 1, 1));
        mockMvc.perform(get("/records/" + record.getId()))
            .andExpect(view().name("records/show"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Show Record")))
            .andDo(print());
    }

    @Test
    public void edit() throws Exception {
        Record record = recordService.save(new Record(new Date(), "ozaki", 1, 1, 1, 1, 1));
        mockMvc.perform(get("/records/" + record.getId() + "/edit"))
            .andExpect(view().name("records/edit"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Editing Record")))
            .andDo(print());
    }

    @Test
    public void create() throws Exception {
        mockMvc.perform(
                post("/records")
                    .param("date", "2017/01/01")
                    .param("name", "potaro")
                    .param("pa", "1")
                    .param("hit", "1")
                    .param("rbi", "1")
                    .param("bb", "1")
                    .param("k", "1"))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("/records"))
            .andDo(print());
    }

    @Test
    public void update() throws Exception {
        Record record = recordService.save(new Record(new Date(), "ozaki", 1, 1, 1, 1, 1));
        mockMvc.perform(
            put("/records/" + record.getId())
                .param("date", "2017/01/01")
                .param("name", "potaro")
                .param("pa", "1")
                .param("hit", "1")
                .param("rbi", "1")
                .param("bb", "1")
                .param("k", "1"))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("/records"))
            .andDo(print());
    }

    @Test
    public void destroy() throws Exception {
        Record record = recordService.save(new Record(new Date(), "ozaki", 1, 1, 1, 1, 1));
        mockMvc.perform(delete("/records/" + record.getId()))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("/records"))
            .andDo(print());
    }
}
