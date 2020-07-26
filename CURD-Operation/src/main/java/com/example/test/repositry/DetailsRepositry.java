package com.example.test.repositry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.test.model.Teacher;


@Repository
public interface DetailsRepositry extends CrudRepository<Teacher, Integer>{

}
