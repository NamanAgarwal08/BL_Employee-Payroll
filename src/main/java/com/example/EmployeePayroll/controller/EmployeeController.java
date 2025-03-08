package com.example.EmployeePayroll.controller;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/get/{id}")
    public EmployeeDTO get(@PathVariable Long id){
        log.info("Fetching employee details corresponding to id : {}", id);
        return employeeService.get(id);
    }

    @PostMapping("/create")
    public EmployeeDTO create(@RequestBody EmployeeDTO newEmp){
        log.info("Creating new employee", newEmp);
        return employeeService.create(newEmp);
    }

    @PatchMapping("/edit/{id}")
    public EmployeeDTO edit(@RequestBody EmployeeDTO emp, @PathVariable Long id){
        log.info("Editing employee details corresponding to id: {}",id);
        return employeeService.edit(emp, id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        log.warn("Deleting employee details corresponding to id: {}", id);
        return employeeService.delete(id);
    }


//    //for list
//    @GetMapping("/all")  // ✅ New API to Fetch All Employees
//    public List<EmployeeDTO> getAllEmployees() {
//        log.info("Fetching all employee details from list.");
//        return employeeService.getAllEmployees();
//    }

}