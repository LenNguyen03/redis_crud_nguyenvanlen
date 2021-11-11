package com.example.redis_crud_nguyenvanlen.repository;

import com.example.redis_crud_nguyenvanlen.model.Employee;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    private static final String KEY = "employees";

    private HashOperations hashOperations;
    private ListOperations<String, Employee> listOperations;
    private RedisTemplate redisTemplate;

    public EmployeeRepository(RedisTemplate redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
       ////////CRUD LIST///////////////////////////////
        this.listOperations = redisTemplate.opsForList();

        this.redisTemplate = redisTemplate;
    }


    public void saveEmployeeForHash(Employee employee) {
        hashOperations.put("EMPLOYEE", employee.getId(), employee);
    }

    public Employee findById(Integer id){

        return (Employee) hashOperations.get("EMPLOYEE", id);
    }


//    public void update(Employee employee){
//        saveEmployee(employee);
//    }
    public void delete(Integer id){
        hashOperations.delete("EMPLOYEE", id);
    }


    public List<Employee> findAll() {
        return hashOperations.values(KEY);
    }

    /////////////////////////////////CRUD LIST///////////////////////////////////////////////
    public void saveEmployeeForList(Employee employee) {
        listOperations.leftPush(KEY, employee);
    }

    public Employee getEmployeeAtIndex(Long index) {
        return listOperations.index(KEY, index);
    }


    public void deleteAll(Employee employee) {
        listOperations.remove(KEY, 1, employee);
    }

    public long getNumberOfEmployees() {
        return listOperations.size(KEY);

    }
}