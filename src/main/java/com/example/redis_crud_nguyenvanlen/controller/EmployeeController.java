package com.example.redis_crud_nguyenvanlen.controller;


import com.example.redis_crud_nguyenvanlen.model.Employee;
import com.example.redis_crud_nguyenvanlen.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee) {
        employeeRepository.saveEmployeeForHash(employee);
        return employee;
    }

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable("id") Integer id) {
        return employeeRepository.findById(id);
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

//    @PutMapping("/employee")
//    public void update(@RequestBody Employee employee) {
//        employeeRepository.update(employee);
//    }

    @DeleteMapping("/employees/{id}")
    public void delete(@PathVariable("id") Integer id){
        employeeRepository.delete(id);
    }

//////////////////////////////////LIST OPS////////////////////////////////////////////////////

    @PostMapping("/list")
    public Employee saveEmployeeByList(@RequestBody Employee employee) {
        employeeRepository.saveEmployeeForList(employee);
        return employee;
    }

    @GetMapping("/list/{index}")
    public Employee findByEmployeeByIndex(@PathVariable("index") long index) {
        return employeeRepository.getEmployeeAtIndex(index);
    }

    @DeleteMapping("/list")
    public void DeleteForList(@RequestBody Employee employee) {
        employeeRepository.deleteAll(employee);
        System.out.println("Delete Sucess !");
    }

    @GetMapping("/list/number")
    public long getNumberOfEmployees() {
        return employeeRepository.getNumberOfEmployees();
    }
}