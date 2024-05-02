package com.obulreddy.departmentservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obulreddy.departmentservice.client.EmployeeClient;
import com.obulreddy.departmentservice.model.Department;
import com.obulreddy.departmentservice.repository.DepartmentRepository;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private EmployeeClient employeeClient;

	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World";
	}

	@PostMapping
	public Department addDepartment(@RequestBody Department department) {
		LOGGER.info("Department add: {} ", department);
		departmentRepository.addDepartment(department);
		return department;
	}

	@GetMapping("/{id}")
	public Department findById(@PathVariable Long id) {
		LOGGER.info("Department findById: {} ", id);
		return departmentRepository.findById(id);

	}

	@GetMapping
	public List<Department> findAll() {
		LOGGER.info("Department findAll");
		return departmentRepository.findAll();
	}

	@GetMapping("/with-employees")
	public List<Department> findAllWIthEmployee() {
		LOGGER.info("Department findAll");
		List<Department> departments = departmentRepository.findAll();
		departments.forEach(department -> department.setEmployees(employeeClient.findByDepartment(department.getId())));
		return departments;
	}

}
