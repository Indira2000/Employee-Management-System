package com.web.project.emp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.web.project.emp.Entity.Employee;

//@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
}