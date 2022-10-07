package com.ibik.academicservices.academicservices.students;

import javax.swing.ProgressMonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students") //isinya endpoint

public class StudentsController {
    @Autowired
    private StudentsServices studentsServices;

    @PostMapping()
    public Students postStudents(@RequestBody Students students){
        return studentsServices.save(students);
    }

    @GetMapping
    public Iterable<Students> fetchStudents(){
        return studentsServices.findAll();
    }

    @GetMapping("/{id}") //menambahkan parameter untuk mencari berdasarkan id
    public Students fetchStudentsById(@PathVariable("id") int id){
        return studentsServices.findOne(id);
    }

    @PutMapping
    public Students updateStudents(@RequestBody Students students){
        //update query
        return studentsServices.save(students);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentsById(@PathVariable("id") int id){
        studentsServices.removeOne(id);
    }
    
}
