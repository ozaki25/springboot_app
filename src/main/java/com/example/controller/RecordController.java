package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Record;
import com.example.service.RecordService;

@RestController
@RequestMapping("records")
public class RecordController {
   @Autowired
    private RecordService recordService;

    @GetMapping
    public List<Record> index() {
        return recordService.findAll();
    }

    @PostMapping
    public Record create(@RequestBody Record record) {
        return recordService.save(record);
    }
}
