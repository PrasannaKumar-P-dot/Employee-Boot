package com.project.service;

import java.util.List;

import com.project.model.EmployeeDto;
import com.project.model.EmployeeResponse;

public interface EmployeeService {
	
	public EmployeeResponse addEmployee(EmployeeDto employeeDto);
	
	public List<EmployeeResponse> getAllEmployee();
	
	public EmployeeResponse getById(Integer id);
	
	public EmployeeResponse findByFirstName(String name);
	
	public void deleteEmployee(int id);
	
	public EmployeeResponse updateEmployee(EmployeeDto employeeDto, int id);


}
