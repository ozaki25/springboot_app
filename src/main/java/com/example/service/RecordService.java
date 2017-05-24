package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Record;
import com.example.repository.RecordRepository;

@Service
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;

    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    public Record findOne(Long id) {
        return recordRepository.findOne(id);
    }

    public Record save(Record record) {
        return recordRepository.save(record);
    }

    public void delete(Long id) {
        recordRepository.delete(id);
    }
}
