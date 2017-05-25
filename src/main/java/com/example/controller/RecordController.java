package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Record;
import com.example.service.RecordService;

@Controller
@RequestMapping("records")
public class RecordController {
   @Autowired
    private RecordService recordService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<Record> records = recordService.findAll();
        model.addAttribute("records", records);
        return "records/index";
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newRecord() {
        return "records/new";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        Record record = recordService.findOne(id);
        model.addAttribute("record", record);
        return "records/edit";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model) {
        Record record = recordService.findOne(id);
        model.addAttribute("record", record);
        return "records/show";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@ModelAttribute Record record) {
        System.out.println(record.getDate());
        recordService.save(record);
        return "redirect:/records";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public String update(@PathVariable Long id, @ModelAttribute Record record) {
        record.setId(id);
        recordService.save(record);
        return "redirect:/records";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String update(@PathVariable Long id) {
        recordService.delete(id);
        return "redirect:/records";
    }
}
