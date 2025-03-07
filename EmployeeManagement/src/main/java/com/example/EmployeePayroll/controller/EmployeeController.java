package com.example.EmployeePayroll.controller;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }


    @GetMapping("/get/{id}")
    public EmployeeDTO get(@PathVariable Long id){

        return employeeService.get(id);
    }

    @PostMapping("/create")
    public EmployeeDTO create(@RequestBody EmployeeDTO newEmp){
        return employeeService.create(newEmp);
    }

    @PatchMapping("/edit/{id}")
    public EmployeeDTO edit(@RequestBody EmployeeDTO emp, @PathVariable Long id){
        return employeeService.edit(emp, id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        return employeeService.delete(id);
    }


    //for list
    @GetMapping("/all")  // ✅ New API to Fetch All Employees
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

}