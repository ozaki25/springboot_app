package com.example.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.Record;
import com.example.service.RecordService;

@Controller
@RequestMapping("records")
public class RecordController {
   @Autowired
    private RecordService recordService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("records", recordService.findAll());
        return "records/index";
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newRecord(RecordForm recordForm) {
        recordForm.setRecord(new Record());
        return "records/new";
    }

    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, RecordForm recordForm) {
        Record record = recordService.findOne(id);
        recordForm.setRecord(record);
        return "records/edit";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model) {
        Record record = recordService.findOne(id);
        model.addAttribute("record", record);
        return "records/show";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid RecordForm recordForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "records/new";
        }
        recordService.save(recordForm);
        return "redirect:/records";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public String update(@PathVariable Long id, @Valid RecordForm recordForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "records/edit";
        }
        recordForm.setId(id);
        recordService.save(recordForm);
        return "redirect:/records";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String destroy(@PathVariable Long id) {
        recordService.delete(id);
        return "redirect:/records";
    }
}
