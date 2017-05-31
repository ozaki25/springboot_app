package com.example.domain;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Record {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date date;

    @NotEmpty
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

    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Record() {
        this.pa = 0;
        this.hit = 0;
        this.rbi = 0;
        this.bb = 0;
        this.k = 0;
    }

    public Record(Date date, String name, int pa, int hit, int rbi, int bb, int k) {
        this.date = date == null ? null : (Date) date.clone();
        this.name = name;
        this.pa = pa;
        this.hit = hit;
        this.rbi = rbi;
        this.bb = bb;
        this.k = k;
    }

    public Long getId() {
        return this.id;
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

    public Timestamp getCreatedAt() {
        return this.createdAt == null ? null : new Timestamp(this.createdAt.getTime());
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt == null ? null : new Timestamp(this.updatedAt.getTime());
    }

    public void setAttributes(Date date, String name, Integer pa,
                              Integer hit, Integer rbi, Integer bb, Integer k) {
        this.date = date == null ? null : (Date) date.clone();
        this.name = name;
        this.pa = pa;
        this.hit = hit;
        this.rbi = rbi;
        this.bb = bb;
        this.k = k;
    }

    @PrePersist
    public void prePersist() {
        Timestamp ts = new Timestamp((new Date()).getTime());
        this.createdAt = ts;
        this.updatedAt = ts;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Timestamp((new Date()).getTime());
    }
}
