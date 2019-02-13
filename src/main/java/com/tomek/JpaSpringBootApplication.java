package com.tomek;

import com.tomek.model.Car;
import com.tomek.repository.CarRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JpaSpringBootApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(JpaSpringBootApplication.class, args);
        /*
        general concept of Spring Data is to define method like that:
        public List<T> findByName(String name);
        and spring generates rest of code
        */
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("A4", "Audi", 49000.0));
        cars.add(new Car("Auris", "Toyota", 35000.0));
        cars.add(new Car("Insignia", "Opel", 29500.0));

        CarRepository carRepo = ctx.getBean(CarRepository.class);
        cars.forEach(carRepo::save); // save all

        Car firstCar = carRepo.findById(1L).get(); // get first
        carRepo.delete(firstCar); // remove it

        // get and print all
        carRepo.findAll().forEach(System.out::println);

        ctx.close();
    }
}