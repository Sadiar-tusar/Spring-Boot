package com.imranhss.project.restcontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imranhss.project.entity.Users;
import com.imranhss.project.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user/")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Map<String, String>> saveUser(
            @RequestPart(value = "user") String userJson,
            @RequestParam(value = "photo")MultipartFile file
            )throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Users  user = objectMapper.readValue(userJson, Users.class);
        try {
            userService.saveOrUpdate(user,file);
            Map<String,String> response = new HashMap<>();
            response.put("Message","User Added Successfully");
            return new  ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            Map<String,String> errorResponse = new HashMap<>();
            errorResponse.put("Message","User Added Failed" );
            return new  ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
