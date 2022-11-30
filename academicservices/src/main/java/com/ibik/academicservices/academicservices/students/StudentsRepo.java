package com.ibik.academicservices.academicservices.students;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.;


//Remove @RepositoryRestResource below to disable auto REST api:
// @RepositoryRestResource
// public interface repositoryName extends CrudRepository<entityName, entityIdType>{}

public interface StudentsRepo extends CrudRepository<Students, Integer> {
    //function query JPAQL atau MYSQL Query
    @Query("SELECT a FROM Students a WHERE (a.firstname like %:name% or a.middlename like %:name% or a.lastname like %:name% or a.npm like %:name%)")
    public Iterable<Students> findStudentByName(@PathParam("name") String name);

    @Query("SELECT a FROM Students a WHERE a.email= :email AND CONCAT(DATE_FORMAT(birth_date, '%d%m%Y'), '@', npm) = :password")
    public Iterable<Students> studentAuth(@PathParam("email") String email, @PathParam("password") String password);

    // @Query("SELECT a FROM Students a WHERE ")
}
