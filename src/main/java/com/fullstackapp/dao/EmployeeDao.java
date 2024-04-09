package com.fullstackapp.dao;

import java.util.List;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fullstackapp.entity.Employee;
import com.fullstackapp.exception.ResourceNotFoundException;

@Repository
public class EmployeeDao {

	@Autowired
	SessionFactory factory;

	public List<Employee> getAllEmployees() {

		Session session=factory.openSession();

		Query query=session.createQuery("from Employee");

		return query.list();

	}

	public Employee createEmployee(Employee employee) {

		Session session=factory.openSession();

		Transaction tx=session.beginTransaction();

		session.save(employee);

		tx.commit();

		return employee;
	}

	public Employee getEmployeeById(int id) {

		Session session=factory.openSession();

		Employee employee=session.get(Employee.class, id);

		return employee;
	}

	public Employee updateEmployee(int id,Employee employeeDetails) throws ResourceNotFoundException {

		Session session=factory.openSession();

		Transaction tx=session.beginTransaction();

		Employee employee=session.get(Employee.class, id);

		if(employee!=null) {

			employee.setFirstName(employeeDetails.getFirstName());
			employee.setLastName(employeeDetails.getLastName());
			employee.setSalary(employeeDetails.getSalary());

			session.save(employee);

			tx.commit();

			return employee;

		}

		else {

			throw new ResourceNotFoundException("Employee does not exist with given id");

		}

	}

	public Employee deleteEmployee(int id) throws ResourceNotFoundException {

		Session session=factory.openSession();

		Transaction tx=session.beginTransaction();

		Employee employee=session.get(Employee.class, id);

		if(employee!=null) {

			session.delete(employee);

			tx.commit();

			return employee;

		}
		else {
			throw new ResourceNotFoundException("Employee does not exist with given id");

		}


	}
}