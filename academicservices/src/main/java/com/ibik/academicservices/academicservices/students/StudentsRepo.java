package com.ibik.academicservices.academicservices.students;

import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.;


//Remove @RepositoryRestResource below to disable auto REST api:
// @RepositoryRestResource
// public interface repositoryName extends CrudRepository<entityName, entityIdType>{}

public interface StudentsRepo extends CrudRepository<Students, Integer> {
    //function query JPAQL atau MYSQL Query
}
