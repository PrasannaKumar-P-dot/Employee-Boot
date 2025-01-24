package com.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exception.ResourceNotFoundException;
import com.project.model.Employee;
import com.project.model.EmployeeDto;
import com.project.model.EmployeeResponse;
import com.project.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepo employeeRepo;
	String str;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public EmployeeResponse addEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setEmail(employeeDto.getEmail());
		employee.setDob(employeeDto.getDob());
		employeeRepo.save(employee);
		return new EmployeeResponse(employee.getId(),employee.getFirstName()+" "+employee.getLastName(),
				employee.getEmail(),employee.getDob());
	}

	@Override
	public List<EmployeeResponse> getAllEmployee() {
		List<Employee> list = employeeRepo.findAll();
		return list.stream()
		           .map(emp -> new EmployeeResponse(
		                   emp.getId(),
		                   emp.getFirstName() + " " + emp.getLastName(), // Concatenating firstName and lastName
		                   emp.getEmail(),
		                   emp.getDob()
		           ))
		           .collect(Collectors.toList());
		
	}

	@Override
	public EmployeeResponse getById(Integer id){
		Optional<Employee> emp1 = employeeRepo.findById(id);
		System.out.println(emp1);
		if(emp1.isPresent()) {
			Employee employee2 = emp1.get();
			return new EmployeeResponse(employee2.getId(), employee2.getFirstName()+" "+ employee2.getLastName(), employee2.getEmail(),
					employee2.getDob());
		}else {
		
			System.out.println("Employee Not Found with id: "+id);
			throw new ResourceNotFoundException("Employee not found with id: "+id);
		
		}
	}

	@Override
	public void deleteEmployee(int id) {
		employeeRepo.deleteById(id);
	}

	@Override
	public EmployeeResponse updateEmployee(EmployeeDto employeeDto, int id) {
		Optional<Employee> employee3 = employeeRepo.findById(id);
		
		if(employee3.isPresent()) {
			Employee empd = employee3.get();
			empd.setFirstName(employeeDto.getFirstName());
			empd.setLastName(employeeDto.getLastName());
			empd.setEmail(employeeDto.getEmail());
			empd.setDob(employeeDto.getDob());
			employeeRepo.save(empd);
			
			return new EmployeeResponse(empd.getId(),empd.getFirstName() +" "+ empd.getLastName(), empd.getEmail(),
					empd.getDob());
		}else {
			throw new RuntimeException("Employee not found with id: "+id);
		}
	}

	@Override
	public EmployeeResponse findByFirstName(String name) {
		
		Employee employee = employeeRepo.findByFirstName(name);
		EmployeeResponse response = mapper.map(employee, EmployeeResponse.class);
		response.setFullName(employee.getFirstName()+" "+employee.getLastName());
		return response;
	}
	
}
