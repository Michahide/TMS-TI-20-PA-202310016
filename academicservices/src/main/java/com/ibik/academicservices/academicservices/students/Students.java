package com.ibik.academicservices.academicservices.students;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.ibik.academicservices.academicservices.Courses.Courses;
import com.ibik.academicservices.academicservices.program_study.ProgramStudy;
import com.ibik.academicservices.academicservices.programs.Programs;


@Entity
@Table(name="students")

public class Students implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 15, unique = true)
    @NotEmpty (message = "NPM is Required")
    private String npm;

    @Column(length = 10)
    @NotEmpty (message = "First Name is Required")
    private String firstname;

    @Column(length = 10)
    private String middlename;

    @Column(length = 10)
    @NotEmpty (message = "Last Name is Required")
    private String lastname;

    //before
    // @Column(length = 11)
    // @Min (value = 1,message = "Program is Required")
    // private int program_id;

    //after
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Programs programs;

    // @Column(length = 11)
    // @Min (value = 1,message = "Program Study is Required")
    // private int program_study_id;

    @ManyToOne
    @JoinColumn(name = "program_study_id")
    private ProgramStudy programStudy;

    // @Column(length = 11)
    // @Min (value = 1, message = "Department is Required")
    // private int department_id;

    @ManyToMany
    @JoinTable(
        name = "student_rel_courses",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Courses> courses;

    public Students(int id, @NotEmpty(message = "NPM is Required") String npm,
            @NotEmpty(message = "First Name is Required") String firstname, String middlename,
            @NotEmpty(message = "Last Name is Required") String lastname, Programs programs, ProgramStudy programStudy,
            Set<Courses> courses) {
        this.id = id;
        this.npm = npm;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.programs = programs;
        this.programStudy = programStudy;
        this.courses = courses;
    }

    public Students(){
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Programs getPrograms() {
        return programs;
    }

    public void setPrograms(Programs programs) {
        this.programs = programs;
    }

    public ProgramStudy getProgramStudy() {
        return programStudy;
    }

    public void setProgramStudy(ProgramStudy programStudy) {
        this.programStudy = programStudy;
    }

    public Set<Courses> getCourses() {
        return courses;
    }

    public void setCourses(Set<Courses> courses) {
        this.courses = courses;
    }
    

    
}
