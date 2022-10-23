package com.ibik.academicservices.academicservices.Courses;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="courses")

public class Courses implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 10)
    @NotEmpty(message = "Code is required")
    private String code;

    @Column(length = 11)
    @Min(value = 1, message = "Credit is required")
    private int credit;

    @Column(nullable = false, columnDefinition =  "TINYINT(1)")
    private boolean is_active;

    @Column(length = 50)
    @NotEmpty(message = "Name is required")
    private String name;

    public Courses(int id, @NotEmpty(message = "Code is required") String code,
            @Min(value = 1, message = "Credit is required") int credit, boolean is_active,
            @NotEmpty(message = "Name is required") String name) {
        this.id = id;
        this.code = code;
        this.credit = credit;
        this.is_active = is_active;
        this.name = name;
    }

    public Courses(){
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

