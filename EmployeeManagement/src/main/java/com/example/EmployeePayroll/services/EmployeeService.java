package com.example.EmployeePayroll.services;

import com.example.EmployeePayroll.controller.EmployeeController;
import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.entities.EmployeeEntity;
import com.example.EmployeePayroll.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {

        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO get(Long id){

        EmployeeEntity empFound = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find Employee with given id"));

        return modelMapper.map(empFound, EmployeeDTO.class);

    }

    public EmployeeDTO create(EmployeeDTO newEmp){

        EmployeeEntity employeeEntity = modelMapper.map(newEmp, EmployeeEntity.class);
        employeeRepository.save(employeeEntity);

        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO edit(EmployeeDTO emp, Long id){
        //finding employee
        EmployeeEntity foundEmp = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("No employee found for given id"));

        //updating details
        foundEmp.setName(emp.getName());
        foundEmp.setSalary(emp.getSalary());

        //saving in database
        employeeRepository.save(foundEmp);

        return modelMapper.map(foundEmp, EmployeeDTO.class);
    }

    public String delete(Long id){
        employeeRepository.deleteById(id);
        return "Employee Deleted";
    }

}