package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.exception.ResourceNotFoundException;
import com.project.model.Employee;
import com.project.model.EmployeeDto;
import com.project.model.EmployeeResponse;
import com.project.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping("/emp")
	public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody EmployeeDto employeeDto) {
		EmployeeResponse response1 = employeeService.addEmployee(employeeDto);
		return ResponseEntity.status(201).body(response1);
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeResponse>> getAllEmployee(){
		List<EmployeeResponse> emps = employeeService.getAllEmployee();
		return ResponseEntity.ok(emps);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponse> getById(@PathVariable(value="id") Integer id){

		EmployeeResponse response = employeeService.getById(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/name")
	public ResponseEntity<EmployeeResponse> findByFirstName(@RequestParam(name="name") String name){
		
		EmployeeResponse response1 = employeeService.findByFirstName(name);
		if(response1 == null) {
			return ResponseEntity.status(404).body(response1);
		}else {
		return ResponseEntity.ok(response1);
		}
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable(name = "id") int id) {
		employeeService.deleteEmployee(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable(value="id") int id) {
		try {
		EmployeeResponse updateEmployee = employeeService.updateEmployee(employeeDto, id);
		return ResponseEntity.ok(updateEmployee);
		}catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
