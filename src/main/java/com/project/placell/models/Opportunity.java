package com.project.placell.models;

import com.project.placell.util.StringListConverter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String company;
    private String role;
    private String description;
    private String website;
    private Date registrationDeadline;

    @Column(name="allowed_branches")
    @Convert(converter = StringListConverter.class)
    private List<String> allowedBranches;

    @ManyToMany
    @JoinTable(
            name = "student_registered",
            joinColumns = @JoinColumn(name = "opportunity_id",referencedColumnName = "id",
                    nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id",
                    nullable = false, updatable = false))
    private Set<Student> studentsRegistered = new HashSet<>();

    Opportunity() {}

    Opportunity(Long id, String company, String role, String description, String website, List<String> allowedBranches,Date registrationDeadline) {
        this.id = id;
        this.company = company;
        this.role = role;
        this.description = description;
        this.website = website;
        this.allowedBranches = allowedBranches;
        this.registrationDeadline = registrationDeadline;
    }

    public void registerOne(Student student){
        studentsRegistered.add(student);
    }

    public boolean isActive(){
        return registrationDeadline.before(new Date());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<String> getAllowedBranches() {
        return allowedBranches;
    }

    public void setAllowedBranches(List<String> allowedBranches) {
        this.allowedBranches = allowedBranches;
    }

    public Set<Student> getStudentsRegistered() {
        return studentsRegistered;
    }

    public void setStudentsRegistered(Set<Student> studentsRegistered) {
        this.studentsRegistered = studentsRegistered;
    }

    public Date getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(Date registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }
}
