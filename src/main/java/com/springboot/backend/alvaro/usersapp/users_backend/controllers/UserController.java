package com.springboot.backend.alvaro.usersapp.users_backend.controllers;

import com.springboot.backend.alvaro.usersapp.users_backend.entities.User;
import com.springboot.backend.alvaro.usersapp.users_backend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired //Lo mismo que implementar el contructor
    private UserService service;


    @PostMapping
    public ResponseEntity<?> customInsert(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        service.insertUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("{id}")
    public ResponseEntity<?> customUpdate(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<User> userOptional = service.findById(id);
        if (userOptional.isPresent()) {
            service.updateUser(id, user);
            User updatedUser = service.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public List<User> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> show(@PathVariable Long id){
        Optional<User> userOptional = service.findById(id);
        if (userOptional.isPresent()){
            return ResponseEntity.ok(userOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    User updateUser(Long id, User user) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<User> userOptional = service.findById(id);
        if (userOptional.isPresent()){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private static ResponseEntity<Map<String, String>> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(),"El campo " + error.getField() + " " +error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
