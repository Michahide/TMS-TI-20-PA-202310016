package com.ibik.academicservices.academicservices.program_study;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ProgressMonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibik.academicservices.academicservices.dto.ResponseData;

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
    public ResponseEntity<ResponseData<ProgramStudy>> postProgramStudy(@RequestBody ProgramStudy programStudy, Errors errors){
        ResponseData<ProgramStudy> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setResult(false);
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setResult(true);
        List<ProgramStudy> value = new ArrayList<>();
        value.add(programsStudyServices.save(programStudy));
        responseData.setData(value);
        return ResponseEntity.ok(responseData);
        // return programsStudyServices.save(programStudy);
    }

    @GetMapping
    public ResponseEntity<ResponseData<ProgramStudy>> fetchProgramStudy(){
        ResponseData<ProgramStudy> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<ProgramStudy> value = (List<ProgramStudy>) programsStudyServices.findAll();
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        // return programsStudyServices.findAll();
    }

    @GetMapping("/{id}") //menambahkan parameter untuk mencari berdasarkan id
    public ResponseEntity<ResponseData<ProgramStudy>> fetchProgramStudyById(@PathVariable("id") int id){
        ResponseData<ProgramStudy> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<ProgramStudy> value = new ArrayList<>();
            value.add(programsStudyServices.findOne(id));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        // return programsStudyServices.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<ProgramStudy>> updateProgramStudy(@RequestBody ProgramStudy programStudy, Errors errors){
        ResponseData<ProgramStudy> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setResult(false);
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        try {
            responseData.setResult(true);
            List<ProgramStudy> value = new ArrayList<>();
            value.add(programsStudyServices.update(programStudy));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.getMessage().add("ID is Required");
            responseData.setResult(false);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        //update query
        // return programsStudyServices.save(programStudy);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deleteProgramStudyById(@PathVariable("id") int id){
        ResponseData<Void> responseData = new ResponseData<>();
        try {
            programsStudyServices.removeOne(id);
            responseData.setResult(true);
            responseData.getMessage().add("Successfully Remove");

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        // programsStudyServices.removeOne(id);
    }
    
}
