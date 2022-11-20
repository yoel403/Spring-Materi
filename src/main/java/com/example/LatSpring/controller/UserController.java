package com.example.LatSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LatSpring.model.dto.ResponseData;
import com.example.LatSpring.model.dto.UserDto;
import com.example.LatSpring.services.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserServices userService;

  private ResponseData <Object> responseData;

  @PostMapping
  public ResponseEntity<Object> signUp(@RequestBody UserDto request) {
    responseData = userService.register(request);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @PostMapping("/login")
  public ResponseEntity<Object> signIn(@RequestBody UserDto request) {
    responseData = userService.login(request);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @PutMapping("/email")
  public ResponseEntity<Object> update(@RequestBody UserDto request) {
    responseData = userService.updateUser(request);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }
}
