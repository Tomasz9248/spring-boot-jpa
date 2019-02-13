package com.tomek.repository;

import com.tomek.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    //example specific queries
    // name of the method literally tells what method does

    List<Car> findByBrand(String brand);

    Car findFirstByBrand(String brand);

    Car findFirstByPrice(double price);
    //Car findTopByPrice(double price);

    List<Car> findFirst3ByBrand(String brand);

    List<Car> findAllByBrandAndPrice(String brand, double price);

    List<Car> findAllByBrandOrBrand(String brand, String brand2);

    List<Car> findAllByBrandLike(String pattern);

    List<Car> findAllByNameEndingWith(String pattern);

    List<Car> findAllByPriceLessThan(double price);

    List<Car> findAllByPriceBetween(double low, double high);

    List<Car> findAllByBrandOrderByPriceAsc(String brand);

    List<Car> findAllByBrandNotNull();

    List<Car> findAllByNameIn(Collection<String> names);
}