package com.example.redis_crud_nguyenvanlen.repository;

import com.example.redis_crud_nguyenvanlen.model.Employee;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    private HashOperations hashOperations;//crud hash


    private ListOperations listOperations; //crud list



    private RedisTemplate redisTemplate;

    public EmployeeRepository(RedisTemplate redisTemplate) {
//        this.hashOperations = redisTemplate.opsForHash();
        //---------------------------------------------CRUD LIST-------------------------------------------------------
        this.listOperations = redisTemplate.opsForList();
        this.redisTemplate = redisTemplate;

    }
    //---------------------------------------------CRUD HASH-------------------------------------------------------
//    public void saveEmployee(Employee employee){
//        hashOperations.put("EMPLOYEE", employee.getId(), employee);
//    }
//    public List<Employee> findAll(){
//
//        return hashOperations.values("EMPLOYEE");
//    }
//    public Employee findById(Integer id){
//
//        return (Employee) hashOperations.get("EMPLOYEE", id);
//    }
//
//    public void update(Employee employee){
//        saveEmployee(employee);
//    }
//    public void delete(Integer id){
//        hashOperations.delete("EMPLOYEE", id);
//    }

    //-----------------------------------------------------------CRUD LIST---------------------------------------------------------------------
    public void saveEmployee(Employee employee){
        listOperations.put("EMPLOYEE", employee.getId(), employee);
    }
    public List<Employee> findAll(){

        return listOperations.values("EMPLOYEE");
    }
    public Employee findById(Integer id){

        return (Employee) listOperations.get("EMPLOYEE", id);
    }

    public void update(Employee employee){
        saveEmployee(employee);
    }
    public void delete(Integer id){
        listOperations.delete("EMPLOYEE", id);
    }

    //-----------------------------------------------------------CRUD SET------------------------------------
}
