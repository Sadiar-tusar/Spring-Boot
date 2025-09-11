package com.sadiar.erp.restcontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadiar.erp.entity.Employee;
import com.sadiar.erp.entity.User;
import com.sadiar.erp.service.AuthService;
import com.sadiar.erp.service.EmployeeService;
import com.sadiar.erp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    private final EmployeeService empService;
    private final AuthService authService;

    public EmployeeRestController(EmployeeService empService, AuthService authService) {
        this.empService = empService;
        this.authService = authService;
    }

    @GetMapping
    public List<Employee> getAll() {
        return empService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(empService.getEmployeeById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @PostMapping
//    public Employee create(@RequestBody Employee emp) {
//        return empService.createEmployee(emp);
//    }

    @PostMapping("")
    public ResponseEntity<Map<String, String>> registerEmployee(
            @RequestPart(value = "user") String userJson,
            @RequestPart(value = "employee") String employeeJson,
            @RequestParam(value = "photo") MultipartFile file
    ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(userJson, User.class);
        Employee employee = objectMapper.readValue(employeeJson, Employee.class);

        try {
            authService.registerEmployee(user, file, employee);
            Map<String, String> response = new HashMap<>();
            response.put("Message", "User Added Successfully ");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("Message", "User Add Faild " + e);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @GetMapping("all")
    public ResponseEntity<List<Employee>> getAllUsers() {
        List<Employee> employeeList = empService.getAllEmployees();
        return ResponseEntity.ok(employeeList);

    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee emp) {
        return empService.updateEmployee(id, emp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        empService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
