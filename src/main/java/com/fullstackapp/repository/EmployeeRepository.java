package com.fullstackapp.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fullstackapp.dao.EmployeeDao;
import com.fullstackapp.entity.Employee;
import com.fullstackapp.exception.ResourceNotFoundException;

@Service
public class EmployeeRepository {
	
	@Autowired
	EmployeeDao dao;
	
	public List<Employee> getAllEmployees() {
		
		return dao.getAllEmployees();
		
	}
	
	public Employee createEmployee(Employee employee) {
		
		return dao.createEmployee(employee);
		
	}

	public Employee getEmployeeById(int id) throws ResourceNotFoundException {
		
		Employee employee= dao.getEmployeeById(id);
		
		if(employee!=null) {
			
			return employee;
		}
		else 
			
		throw new ResourceNotFoundException("Employee does not exist with given id");
	}
	
	public Employee updateEmployee(int id , Employee employeeDetails) throws ResourceNotFoundException {
		
		return dao.updateEmployee(id, employeeDetails);

	}
	
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(int id) throws ResourceNotFoundException {
		
		dao.deleteEmployee(id);
		
		Map<String,Boolean> response = new HashMap<String,Boolean>();
	
		response.put("delete", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
	
	}
	
	
	
	
	
	
}
