package com.example.redis;

import java.beans.PersistenceDelegate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class EmployeeController {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    private static final String EMPLOYEE_KEY_PREFIX = "emp::";
    private static final String EMPLOYEE_LIST_KEY_PREFIX = "emp_list";
    private static final String EMPLOYEE_HASH_KEY_PREFIX = "emp_hash::";

    private String getKey(long id) {
        return EMPLOYEE_KEY_PREFIX + id;
    }


    @PostMapping("/employee")
    public void savePerson(@RequestBody Employee employee) {
        String key = getKey(employee.getId());

        redisTemplate.opsForValue().set(key, employee);
    }

    @GetMapping("/employee")
    public Employee getEmployee(@RequestParam("id") int id) {
        String key = getKey(id);
        return (Employee) redisTemplate.opsForValue().get(key);
    }

    // List Operations (key - String, Value - List<Person>)
    @PostMapping("/lpush/employee")
    public void lpush(@RequestBody Employee employee) {
        redisTemplate.opsForList().leftPushAll(EMPLOYEE_LIST_KEY_PREFIX, employee);
    }

    // set Operation

    // Hash operatiom

    private String getHashKey(long id) {
        return EMPLOYEE_HASH_KEY_PREFIX + id;
    }

    @PostMapping("/hash/employee")
    public void saveEmployeeInHash(@RequestBody List<Employee> employeeList){
        employeeList.forEach(employee -> {
                    Map map = objectMapper.convertValue(employee, Map.class);
                    redisTemplate.opsForHash().putAll(getHashKey(employee.getId()),map);
                });
    }

}
