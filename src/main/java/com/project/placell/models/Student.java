package com.project.placell.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name="enrolled_year",nullable = false)
    private String enrolledYear;
    @Column(name="roll_no",nullable = false)
    private String rollNo;
    @Column(name="branch",nullable = false)
    private String branch;
    @Column(name="batch",nullable = false)
    private int batch;

    private boolean enrolled;
    private boolean active;

    @Column(name = "resume_url")
    private String resumeUrl;

    @ManyToMany(mappedBy = "studentsRegistered", fetch = FetchType.LAZY)
    private Set<Opportunity> opportunitySet = new HashSet<>();

    Student() {}

    public Student(Long id, String name, String email, String enrolledYear, String rollNo, String branch, int batch, boolean enrolled, boolean active, String resumeUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.enrolledYear = enrolledYear;
        this.rollNo = rollNo;
        this.branch = branch;
        this.batch = batch;
        this.enrolled = enrolled;
        this.active = active;
        this.resumeUrl = resumeUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnrolledYear() {
        return enrolledYear;
    }

    public void setEnrolledYear(String enrolledYear) {
        this.enrolledYear = enrolledYear;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public boolean isEnrolled() {
        return enrolled;
    }

    public void setEnrolled(boolean enrolled) {
        this.enrolled = enrolled;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }
}
