package com.example.Employee.services;

import com.example.Employee.entity.EmployeeEntity;
import com.example.Employee.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    boolean deleteEmployee(Long id);

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employee);

//    Page<EmployeeEntity> findPaginated(int pageNo, int pageSize);
}
