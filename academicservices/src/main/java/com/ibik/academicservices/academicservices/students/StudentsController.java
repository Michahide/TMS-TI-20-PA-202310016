package com.ibik.academicservices.academicservices.students;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ProgressMonitor;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Error;
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
import com.ibik.academicservices.academicservices.dto.SearchData;

import io.micrometer.core.ipc.http.HttpSender.Response;

@RestController
@RequestMapping("/api/students") //isinya endpoint

public class StudentsController {
    @Autowired
    private StudentsServices studentsServices;

    @PostMapping()
    public ResponseEntity<ResponseData<Students>> postStudents(@Valid @RequestBody Students students, Errors errors){
        ResponseData<Students> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                // System.out.println(error.getDefaultMessage());
                responseData.getMessage().add(error.getDefaultMessage());
            }

            responseData.setResult(false);
            responseData.setData(null);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);

            // throw new RuntimeException("Validation Error");
        }
        responseData.setResult(true);
        List<Students> value = new ArrayList<>();
        value.add(studentsServices.save(students));
        responseData.setData(value);
        return ResponseEntity.ok(responseData);
        // return studentsServices.save(students);
    }

    @GetMapping
    // public ResponseEntity<ResponseData<Students>> fetchStudents(Errors errors){
    //     ResponseData<Students> responseData = new ResponseData<>();

    //     if(errors.hasErrors()){
    //         for(ObjectError error : errors.getAllErrors()){
    //             // System.out.println(error.getDefaultMessage());
    //             responseData.getMessage().add(error.getDefaultMessage());
    //         }

    //         responseData.setResult(false);
    //         responseData.setData(null);

    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);

    //         // throw new RuntimeException("Validation Error");
    //     }
    //     responseData.setResult(true);
    //     Iterable<Students> value = studentsServices.findAll();
    //     // value.add(studentsServices.save(students));
    //     responseData.setData(value);
    //     return ResponseEntity.ok(responseData);
    //     // try {
    //     //     return studentsServices.findAll();
    //     // } catch (Exception e) {
    //     //     // TODO: handle exception
    //     //     System.out.println("Cannot update");
    //     // }
    // }
    public ResponseEntity<ResponseData<Students>> fetchStudents() {
        ResponseData<Students> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<Students> value = (List<Students>) studentsServices.findAll();
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }


    @GetMapping("/{id}") //menambahkan parameter untuk mencari berdasarkan id
    public ResponseEntity<ResponseData<Students>> fetchStudentsById(@PathVariable("id") int id){
        ResponseData<Students> responseData = new ResponseData<>();
        try {
            responseData.setResult(true);
            List<Students> value = new ArrayList<>();
            value.add(studentsServices.findOne(id));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        // return studentsServices.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Students>> updateStudents(@Valid @RequestBody Students students, Errors errors){
        ResponseData<Students> responseData = new ResponseData<>();
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
            List<Students> value = new ArrayList<>();
            value.add(studentsServices.update(students));
            responseData.setData(value);

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.getMessage().add("ID is Required");
            responseData.setResult(false);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        // return studentsServices.save(students);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Void>> deleteStudentsById(@PathVariable("id") int id){
        ResponseData<Void> responseData = new ResponseData<>();
        try {
            studentsServices.removeOne(id);
            responseData.setResult(true);
            responseData.getMessage().add("Successfully Removed");

            return ResponseEntity.ok(responseData);
        } catch (Exception ex) {
            responseData.setResult(false);
            responseData.getMessage().add(ex.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<ResponseData<Students>> getStudentByName(@RequestBody SearchData searchData){
        ResponseData<Students> responseData = new ResponseData<>();
        try{
            Iterable<Students> values = studentsServices.findByName(searchData.getSearchKey());
            responseData.setResult(true);
            responseData.setMessage(null);
            responseData.setData(values);
            return ResponseEntity.ok(responseData);
        } catch (Exception e){
            List <String> message = new ArrayList<>();
            message.add(e.getMessage());
            responseData.setMessage(message);
            responseData.setData(null);
            responseData.setResult(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

    }

    
}
