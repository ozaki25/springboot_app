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

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordServiceTests {
    private final Integer recordSize = 3;

    @Autowired
    private RecordService recordService;

    @Before
    public void setup() {
        recordService.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        for (int i = 0; i < recordSize; i++) {
            recordService.save(new Record(new Date(), "ozaki", 1, 1, 1, 1, 1));
        }
        List<Record> results = recordService.findAll();
        assertThat(results.size()).isEqualTo(recordSize);
    }

    @Test
    public void findOne() throws Exception {
        Record record = recordService.save(new Record(new Date(), "ozaki", 1, 1, 1, 1, 1));
        Record result = recordService.findOne(record.getId());
        assertThat(result.getId()).isEqualTo(record.getId());
    }

    @Test
    public void save() throws Exception {
        Record result = recordService.save(new Record(new Date(), "ozaki", 1, 1, 1, 1, 1));
        assertThat(result.getId()).isNotNull();
    }

    @Test
    public void delete() throws Exception {
        Record record = recordService.save(new Record(new Date(), "ozaki", 1, 1, 1, 1, 1));
        Long id = record.getId();
        recordService.delete(id);
        Record result = recordService.findOne(id);
        assertThat(result).isNull();
    }

    @Test
    public void deleteAll() throws Exception {
        for (int i = 0; i < recordSize; i++) {
            recordService.save(new Record(new Date(), "ozaki", 1, 1, 1, 1, 1));
        }
        recordService.deleteAll();
        List<Record> results = recordService.findAll();
        assertThat(results.size()).isEqualTo(0);
    }
}
