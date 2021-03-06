package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Record;
import com.example.domain.RecordRepository;
import com.example.web.RecordForm;

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

    public Record save(RecordForm recordForm) {
        Long id = recordForm.getId();
        Record record = id == null ? new Record() : this.findOne(id);
        record.setDate(recordForm.getDate());
        record.setName(recordForm.getName());
        record.setPa(recordForm.getPa());
        record.setHit(recordForm.getHit());
        record.setRbi(recordForm.getRbi());
        record.setBb(recordForm.getBb());
        record.setK(recordForm.getK());
        return this.save(record);
    }

    public void delete(Long id) {
        recordRepository.delete(id);
    }

    public void deleteAll() {
        recordRepository.deleteAll();
    }
}
