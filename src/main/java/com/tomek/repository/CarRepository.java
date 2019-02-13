package com.tomek.repository;

import com.tomek.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository // annotation is optional but makes code cleaner
public interface CarRepository extends CrudRepository<Car, Long> {
    /*
     interface CarRepository by itself has no methods but in fact extends generic CrudRepository interface
       CarRepo inherits from CrudRepository and has access to its methods (check docs) even if this class looks empty
       Spring Data automatically generates implementation of interface
    */
}