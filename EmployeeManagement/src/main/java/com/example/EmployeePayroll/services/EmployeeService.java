package com.example.EmployeePayroll.services;

import com.example.EmployeePayroll.controller.EmployeeController;
import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.entities.EmployeeEntity;
import com.example.EmployeePayroll.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {


    @Autowired
    EmployeeRepository employeeRepository;


    @Autowired
    ModelMapper modelMapper;

    @Autowired
    List<EmployeeDTO> employeeList;


    public EmployeeDTO get(Long id){


//      By List
        Optional<EmployeeDTO> empFound = employeeList.stream().filter(emp -> emp.getId().equals(id)).findFirst();
        if(empFound.isEmpty()){
            throw new RuntimeException("Cannot find employee with given id");
        }
        return empFound.get();

////        by Repo
//        EmployeeEntity empFound = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find Employee with given id"));
//        return modelMapper.map(empFound, EmployeeDTO.class);

    }


    public EmployeeDTO create(EmployeeDTO newEmp){

//        // Repo
//        EmployeeEntity employeeEntity = modelMapper.map(newEmp, EmployeeEntity.class);
//        employeeRepository.save(employeeEntity);
//
//        return modelMapper.map(employeeEntity, EmployeeDTO.class);

        // By List
        newEmp.setId(employeeList.size()+1L);
        employeeList.add(newEmp);
        return newEmp;
    }


    public EmployeeDTO edit(EmployeeDTO emp, Long id){
////        by Repo
//
//        //finding employee
//        EmployeeEntity foundEmp = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("No employee found for given id"));
//
//        //updating details
//        foundEmp.setName(emp.getName());
//        foundEmp.setSalary(emp.getSalary());
//
//        //saving in database
//        employeeRepository.save(foundEmp);
//
//        return modelMapper.map(foundEmp, EmployeeDTO.class);

//        by List

        //finding employee
        Optional<EmployeeDTO> empFound = employeeList.stream().filter(employee -> employee.getId().equals(id)).findFirst();
        if(empFound.isEmpty()){
            throw new RuntimeException("Cannot find employee with given id");
        }

        //updating details
        empFound.get().setName(emp.getName());
        empFound.get().setSalary(emp.getSalary());

        return empFound.get();

    }


    public String delete(Long id){
//        //by Repo
//        employeeRepository.deleteById(id);

        //By List
        //finding employee
        Optional<EmployeeDTO> empFound = employeeList.stream().filter(employee -> employee.getId().equals(id)).findFirst();
        if(empFound.isEmpty()){
            throw new RuntimeException("Cannot find employee with given id");
        }

        employeeList.remove(empFound.get());

        return "Employee Deleted";

    }


    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> dummyList = new ArrayList<>();
        for (EmployeeDTO emp : employeeList) {
            dummyList.add(new EmployeeDTO(emp.getId(), emp.getName(), emp.getSalary()));
        }

//        employeeList.stream().map(employee -> dummyList.add(new EmployeeDTO(employee.getId(), employee.getName(), employee.getSalary())));
        return dummyList;
    }


}