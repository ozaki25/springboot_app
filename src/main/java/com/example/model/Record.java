package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Record {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date date;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "plate_appearances")
    private Integer pa;

    @NotNull
    private Integer hit;

    @NotNull
    @Column(name = "runs_batted_in")
    private Integer rbi;

    @NotNull
    @Column(name = "base_on_balls")
    private Integer bb;

    @NotNull
    @Column(name = "strikeouts")
    private Integer k;

    public Record() {
        this.pa = 0;
        this.hit = 0;
        this.rbi = 0;
        this.bb = 0;
        this.k = 0;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return (Date) this.date.clone();
    }

    public void setDate(Date date) {
        this.date = new Date(date.getTime());
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
        return this.bb;
    }

    public void setBb(Integer bb) {
        this.bb = bb;
    }

    public Integer getK() {
        return this.k;
    }

    public void setK(Integer k) {
        this.k = k;
    }
}
