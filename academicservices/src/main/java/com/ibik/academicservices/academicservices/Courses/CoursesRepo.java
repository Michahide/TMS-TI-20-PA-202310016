package com.ibik.academicservices.academicservices.Courses;

import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.;


//Remove @RepositoryRestResource below to disable auto REST api:
// @RepositoryRestResource
// public interface repositoryName extends CrudRepository<entityName, entityIdType>{}

public interface CoursesRepo extends CrudRepository<Courses, Integer> {
    //function query JPAQL atau MYSQL Query
}

