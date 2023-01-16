package com.web.project.emp.Controller;

import java.util.HashMap;
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

import com.web.project.emp.Entity.Employee;
import com.web.project.emp.Exception.EmpResourceNotFoundException;
import com.web.project.emp.Repository.EmployeeRepository;

//import jakarta.validation.Valid;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")

public class EmpController {
	
	@Autowired
	private EmployeeRepository empRepository;
	
	@GetMapping("/employees")                            //get All Employees
	public List<Employee> getAllEmployee(){
		return empRepository.findAll();
	}
	
	@PostMapping("/employees")                                         //create employee
	public Employee createEmployee( @RequestBody Employee employee) {  //@Valid
		return empRepository.save(employee);
		
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = empRepository.findById(id)
				.orElseThrow(() -> new EmpResourceNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(employee);
	}
	
	// update employee 
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = empRepository.findById(id).orElseThrow(() -> new EmpResourceNotFoundException("Employee not exist with id :" + id));
		
		employee.setFirstname(employeeDetails.getFirstname());
		employee.setLastname(employeeDetails.getLastname());
		
		//changes are written here
        employee.setEmailId(employeeDetails.getEmailId());        
        employee.setEmpId(employeeDetails.getEmpId());
		employee.setDepartment(employeeDetails.getDepartment());
		
		Employee updatedEmployee = empRepository.save(employee); 
		return ResponseEntity.ok(updatedEmployee);
	}
	
	// delete employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = empRepository.findById(id)
				.orElseThrow(() -> new EmpResourceNotFoundException("Employee not exist with id :" + id));
		
		empRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}