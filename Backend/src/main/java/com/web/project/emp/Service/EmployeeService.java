package com.web.project.emp.Service;

import java.util.List;

import com.web.project.emp.Entity.Employee;

public interface EmployeeService {
	
	List<Employee>getAllEmployees();
	
	void saveEmployee(Employee employee);
	
	Employee getEmployeeById(long id);
	
	void deleteEmployeeById(long id);

}
