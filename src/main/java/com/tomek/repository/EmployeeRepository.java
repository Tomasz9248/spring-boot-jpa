package com.tomek.repository;

import java.util.List;

import com.tomek.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    // to avoid long method names @Query annotations allow to define full query in JPQL and send parameter from method to query
    // with named parameters (:name) its necessary to use @Param to mark which method param correspond with each param in query
    @Query("SELECT e.lastName FROM Employee e WHERE e.salary>=:salary ORDER BY e.lastName ASC")
    List<String> namesForSalaryAsc(@Param("salary") double salary);

    // if static query is defined in entity its possible to use its name instead of full query
    // rest is same like above
    @Query(name = "Employee.findByLastNameAndSalary")
    List<Employee> findByLastNameAndSalary(@Param("lastName") String lastName,
                                           @Param("salary") double salary);


}