package com.example.web;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.domain.Record;

public class RecordForm {
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date date;

    @NotEmpty
    private String name;

    @NotNull
    @Min(0)
    private Integer pa;

    @NotNull
    @Min(0)
    private Integer hit;

    @NotNull
    @Min(0)
    private Integer rbi;

    @NotNull
    @Min(0)
    private Integer bb;

    @NotNull
    @Min(0)
    private Integer k;

    public void setRecord(Record record) {
        this.id = record.getId();
        Date date = record.getDate();
        this.date = date == null ? null : (Date) date.clone();
        this.name = record.getName();
        this.pa = record.getPa();
        this.hit = record.getHit();
        this.rbi = record.getRbi();
        this.bb = record.getBb();
        this.k = record.getK();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date == null ? null : (Date) this.date.clone();
    }

    public void setDate(Date date) {
        this.date = date == null ? null : (Date) date.clone();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPa() {
        return this.pa;
    }

    public void setPa(Integer pa) {
        this.pa = pa;
    }

    public Integer getHit() {
        return this.hit;
    }

    public void setHit(Integer hit) {
        this.hit = hit;
    }

    public Integer getRbi() {
        return this.rbi;
    }

    public void setRbi(Integer rbi) {
        this.rbi = rbi;
    }

    public Integer getBb() {
        return bb;
    }

    public void setBb(Integer bb) {
        this.bb = bb;
    }

    public Integer getK() {
        return k;
    }

    public void setK(Integer k) {
        this.k = k;
    }
}
