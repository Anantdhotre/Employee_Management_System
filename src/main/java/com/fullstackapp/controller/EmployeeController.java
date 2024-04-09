package com.fullstackapp.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstackapp.entity.Employee;
import com.fullstackapp.exception.ResourceNotFoundException;
import com.fullstackapp.repository.EmployeeRepository;

@RestController
@CrossOrigin("http://localhost:4200")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		
		return employeeRepository.getAllEmployees();
	}
	
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		
		return employeeRepository.createEmployee(employee);
		
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) throws ResourceNotFoundException {
		
		Employee employee= employeeRepository.getEmployeeById(id);
	
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		
		Employee employee=employeeRepository.updateEmployee(id, employeeDetails);
	
		return ResponseEntity.ok(employee);
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<ResponseEntity<Map<String,Boolean>>> deleteEmployee(@PathVariable int id) throws ResourceNotFoundException {
		 
		ResponseEntity<Map<String,Boolean>> employee=employeeRepository.deleteEmployee(id);

		return ResponseEntity.ok(employee);
	}
}