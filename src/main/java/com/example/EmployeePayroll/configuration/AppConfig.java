package com.example.EmployeePayroll.configuration;

import com.example.EmployeePayroll.dto.EmployeeDTO;
import com.example.EmployeePayroll.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper createMapper(){
        return new ModelMapper();
    }

    @Bean
    public EmployeeService createEmployeeService(){
        return new EmployeeService();
    }

    @Bean
    public List<EmployeeDTO> createList(){
        return new ArrayList<>();
    }

}
