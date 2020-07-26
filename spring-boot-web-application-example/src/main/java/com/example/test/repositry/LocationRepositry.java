package com.example.test.repositry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.test.model.Location;



@Repository
public interface LocationRepositry extends CrudRepository<Location, Integer>{

}
