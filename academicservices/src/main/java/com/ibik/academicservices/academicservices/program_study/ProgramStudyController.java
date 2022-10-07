package com.ibik.academicservices.academicservices.program_study;

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
@RequestMapping("/api/programstudy") //isinya endpoint

public class ProgramStudyController {
    @Autowired
    private ProgramStudyServices programsStudyServices;

    // @GetMapping //
    // public String HelloWorld(){
    //     return "<h1>Hello</h1>";
    // }

    @PostMapping()
    public ProgramStudy postProgramStudy(@RequestBody ProgramStudy programStudy){
        return programsStudyServices.save(programStudy);
    }

    @GetMapping
    public Iterable<ProgramStudy> fetchProgramStudy(){
        return programsStudyServices.findAll();
    }

    @GetMapping("/{id}") //menambahkan parameter untuk mencari berdasarkan id
    public ProgramStudy fetchProgramStudyById(@PathVariable("id") int id){
        return programsStudyServices.findOne(id);
    }

    @PutMapping
    public ProgramStudy updateProgramStudy(@RequestBody ProgramStudy programStudy){
        //update query
        return programsStudyServices.save(programStudy);
    }

    @DeleteMapping("/{id}")
    public void deleteProgramStudyById(@PathVariable("id") int id){
        programsStudyServices.removeOne(id);
    }
    
}
