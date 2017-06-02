package com.example.service;

import static org.assertj.core.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.Record;
import com.example.web.RecordForm;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordServiceTests {
    private static final Integer RECORD_SIZE = 3;
    private static final Date DUMMY_DATE = new Date();
    private static final String DUMMY_NAME = "ozaki";
    private static final String DUMMY_NAME_2 = "test";
    private static final Integer DUMMY_VALUE = 1;

    @Autowired
    private RecordService recordService;

    @Before
    public void setup() {
        recordService.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        for (int i = 0; i < RECORD_SIZE; i++) {
            recordService.save(new Record(DUMMY_DATE, DUMMY_NAME, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE));
        }
        List<Record> results = recordService.findAll();
        assertThat(results.size()).isEqualTo(RECORD_SIZE);
    }

    @Test
    public void findOne() throws Exception {
        Record record = recordService.save(new Record(DUMMY_DATE, DUMMY_NAME, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE));
        Record result = recordService.findOne(record.getId());
        assertThat(result.getId()).isEqualTo(record.getId());
    }

    @Test
    public void save() throws Exception {
        Record result = recordService.save(new Record(DUMMY_DATE, DUMMY_NAME, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE));
        assertThat(result.getId()).isNotNull();
    }

    @Test
    public void saveRecordForm() throws Exception {
        RecordForm recordForm = new RecordForm();
        recordForm.setDate(DUMMY_DATE);
        recordForm.setName(DUMMY_NAME);
        recordForm.setPa(DUMMY_VALUE);
        recordForm.setHit(DUMMY_VALUE);
        recordForm.setRbi(DUMMY_VALUE);
        recordForm.setBb(DUMMY_VALUE);
        recordForm.setK(DUMMY_VALUE);
        Record result = recordService.save(recordForm);
        assertThat(result.getId()).isNotNull();
    }

    @Test
    public void saveRecordFormExistId() throws Exception {
        Record record = recordService.save(new Record(DUMMY_DATE, DUMMY_NAME, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE));
        RecordForm recordForm = new RecordForm();
        recordForm.setId(record.getId());
        recordForm.setDate(DUMMY_DATE);
        recordForm.setName(DUMMY_NAME_2);
        recordForm.setPa(DUMMY_VALUE);
        recordForm.setHit(DUMMY_VALUE);
        recordForm.setRbi(DUMMY_VALUE);
        recordForm.setBb(DUMMY_VALUE);
        recordForm.setK(DUMMY_VALUE);
        Record result = recordService.save(recordForm);
        assertThat(result.getName()).isEqualTo(DUMMY_NAME_2);
    }

    @Test
    public void delete() throws Exception {
        Record record = recordService.save(new Record(DUMMY_DATE, DUMMY_NAME, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE));
        Long id = record.getId();
        recordService.delete(id);
        Record result = recordService.findOne(id);
        assertThat(result).isNull();
    }

    @Test
    public void deleteAll() throws Exception {
        for (int i = 0; i < RECORD_SIZE; i++) {
            recordService.save(new Record(DUMMY_DATE, DUMMY_NAME, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE));
        }
        recordService.deleteAll();
        List<Record> results = recordService.findAll();
        assertThat(results.size()).isEqualTo(0);
    }
}
