package com.ibik.academicservices.academicservices.programs;

import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.;


//Remove @RepositoryRestResource below to disable auto REST api:
// @RepositoryRestResource
// public interface repositoryName extends CrudRepository<entityName, entityIdType>{}

public interface ProgramsRepo extends CrudRepository<Programs, Integer> {
    //function query JPAQL atau MYSQL Query
}
