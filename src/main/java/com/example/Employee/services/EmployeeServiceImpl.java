package com.example.Employee.services;


import com.example.Employee.entity.EmployeeEntity;
import com.example.Employee.model.Employee;
import com.example.Employee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
@Override
    public Employee createEmployee(Employee employee)
    {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        //to copy values from employee to employeeEntity we use following
        BeanUtils.copyProperties(employee,employeeEntity);
        employeeRepository.save(employeeEntity);
        return employee;

    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities
                =employeeRepository.findAll();
        List<Employee> employees = employeeEntities
                .stream()
                .map(emp -> new Employee(emp.getId(),emp.getFirstName(),emp.getLastName(),
                        emp.getEmailId()))
                .collect(Collectors.toList());
        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        EmployeeEntity employee = employeeRepository.findById(id).get();
        employeeRepository.delete(employee);
        return true;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        Employee employee=new Employee();
        BeanUtils.copyProperties(employeeEntity,employee);

        return employee;
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        employeeEntity.setEmailId(employee.getEmailId());
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeRepository.save(employeeEntity);
        return employee;



    }

    //@Override
//    public Page<EmployeeEntity> findPaginated(int pageNo, int pageSize) {
//
//        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
//
//        return this.employeeRepository.findAll(pageable);
    }

