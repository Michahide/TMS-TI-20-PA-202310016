package com.ibik.academicservices.academicservices.program_study;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class ProgramStudyServices {
    @Autowired
    private ProgramStudyRepo programStudyRepo;

    //inserting into table
    public ProgramStudy save(ProgramStudy programStudy){
        //query DTO
        return programStudyRepo.save(programStudy);
    }

    public ProgramStudy findOne(int id){
        return programStudyRepo.findById(id).get();
    }

    public Iterable<ProgramStudy> findAll(){
        return programStudyRepo.findAll();
    }

    public void removeOne(int id){
        programStudyRepo.deleteById(id);
    }

    public ProgramStudy update(ProgramStudy programStudy) {
        ProgramStudy result = findOne(programStudy.getId());

        result.setName(programStudy.getName());
        result.setDescription(programStudy.getDescription());
        result.setCode(programStudy.getCode());
        result.setPrograms(programStudy.getPrograms());
        result.setFaculty(programStudy.getFaculty());
        result.setDepartment_id(programStudy.getDepartment_id());
        result.setIs_active(programStudy.isIs_active());

        return result;
    }
}
