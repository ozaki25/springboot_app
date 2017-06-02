package com.example.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordRepositoryTests {
    private static final Integer RECORD_SIZE = 3;
    private static final Date DUMMY_DATE = new Date();
    private static final String DUMMY_NAME = "ozaki";
    private static final Integer DUMMY_VALUE = 1;

    @Autowired
    private RecordRepository recordRepository;

    @Before
    public void setup() {
        recordRepository.deleteAll();
    }

    @Test
    public void findAll() throws Exception {
        for (int i = 0; i < RECORD_SIZE; i++) {
            recordRepository.save(new Record(DUMMY_DATE, DUMMY_NAME, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE));
        }
        List<Record> results = recordRepository.findAll();
        assertThat(results.size()).isEqualTo(RECORD_SIZE);
    }

    @Test
    public void findOne() throws Exception {
        Record record = recordRepository.save(new Record(DUMMY_DATE, DUMMY_NAME, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE));
        Record result = recordRepository.findOne(record.getId());
        assertThat(result.getId()).isEqualTo(record.getId());
    }

    @Test
    public void save() throws Exception {
        Record result = recordRepository.save(new Record(DUMMY_DATE, DUMMY_NAME, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE));
        assertThat(result.getId()).isNotNull();
        assertThat(result.getDate()).isEqualTo(DUMMY_DATE);
        assertThat(result.getName()).isEqualTo(DUMMY_NAME);
        assertThat(result.getPa()).isEqualTo(DUMMY_VALUE);
        assertThat(result.getHit()).isEqualTo(DUMMY_VALUE);
        assertThat(result.getRbi()).isEqualTo(DUMMY_VALUE);
        assertThat(result.getBb()).isEqualTo(DUMMY_VALUE);
        assertThat(result.getK()).isEqualTo(DUMMY_VALUE);
    }

    @Test
    public void delete() throws Exception {
        Record record = recordRepository.save(new Record(DUMMY_DATE, DUMMY_NAME, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE));
        Long id = record.getId();
        recordRepository.delete(id);
        Record result = recordRepository.findOne(id);
        assertThat(result).isNull();
    }

    @Test
    public void deleteAll() throws Exception {
        for (int i = 0; i < RECORD_SIZE; i++) {
            recordRepository.save(new Record(DUMMY_DATE, DUMMY_NAME, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE, DUMMY_VALUE));
        }
        recordRepository.deleteAll();
        List<Record> results = recordRepository.findAll();
        assertThat(results.size()).isEqualTo(0);
    }
}
