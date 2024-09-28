package com.blogging.Controllers;

import com.blogging.Service.UserService;
import com.blogging.payloads.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService service;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto dto)
    {
        UserDto createduser= this.service.createUser(dto);
        return new ResponseEntity<>(createduser, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> UpdateUser(@Valid @RequestBody UserDto dto,@PathVariable Integer id)
    {
        UserDto updateuser= this.service.updateUser(dto,id);
        return new ResponseEntity<>(updateuser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@Valid @PathVariable Integer id)
    {
        this.service.deleteUser(id);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        List<UserDto> res= this.service.getAllUsers();
        return  ResponseEntity.ok(res);

    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getSingleUser(@Valid @PathVariable Integer id)
    {
        return ResponseEntity.ok(this.service.getUserById(id));
    }





}
