package com.tomek;

import com.tomek.model.Car;
import com.tomek.model.Employee;
import com.tomek.repository.CarRepository;
import com.tomek.repository.EmployeeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class JpaSpringBootApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext ctx = SpringApplication.run(JpaSpringBootApplication.class, args);

        /*
        if method has multiply conditions it becomes less cleaner fe:
         findByLastNameAndSalaryGreaterThanEqualOrderBySalaryAsc
        to avoid this use @Query annotation is repository interface (check EmployeeRepository)
         */

        EmployeeRepository employeeRepo = ctx.getBean(EmployeeRepository.class);

        Stream.of(new Employee("Jan", "Kowalski", 3000.0),
                new Employee("Wojciech", "Kowalski", 4000.0),
                new Employee("Ania", "Zawada", 3200.0),
                new Employee("Patryk", "Kostrzewski", 4500.0))
                .forEach(employeeRepo::save);

        System.out.println("Employees with last name Kowalski and salary >= 2000");
        employeeRepo.findByLastNameAndSalary("Kowalski", 2000)
                .forEach(System.out::println);

        System.out.println("Employee names with salary >= 4000");
        employeeRepo.namesForSalaryAsc(4000)
                .forEach(System.out::println);

        ctx.close();
    }
}