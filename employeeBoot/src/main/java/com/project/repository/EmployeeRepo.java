package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Employee;



@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	Employee  findByFirstName(String firstName);

}
