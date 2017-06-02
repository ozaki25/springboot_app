package com.example;


import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void main() throws Exception {
        Application.main(new String[]{});
        String output = this.outputCapture.toString();
        assertThat(output.contains("Started App")).isTrue();
        assertThat(output.contains("Exception")).isFalse();
    }

    @Test
    public void index() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("/records"))
            .andDo(print());
    }
}
