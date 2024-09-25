package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.User;
import com.synergisticit.service.UserService;
import com.synergisticit.validation.UserValidator;


import jakarta.validation.Valid;

@RestController
@RequestMapping("r5")
public class UserRestController {
	@Autowired
	UserService userService;
	
	@Autowired
	UserValidator userValidator; 
	
	@PostMapping("user/save")
	public ResponseEntity<?> saveFlight(@Valid @RequestBody User user, BindingResult br){
		userValidator.validate(user, br);
		Long userId = user.getUserId();
		
		if(userService.existById(userId)) {
			return new ResponseEntity<String>("User already exists with id = " + userId, HttpStatus.FOUND);
		}
		
		else if(br.hasFieldErrors()) {
			String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
		}
		
		else
			userService.saveUser(user);
			return new ResponseEntity<User>(user,HttpStatus.CREATED);
		
	}
	
	@PutMapping("user/update")
	public ResponseEntity<?> updateFlight(@Valid @RequestBody User user, BindingResult br){
		userValidator.validate(user, br);
		
		Long userId = user.getUserId();
		if(!userService.existById(userId)) {
			return new ResponseEntity<String>("User does not exist with id= " + userId, HttpStatus.NOT_FOUND);
		}
		
		else if(br.hasFieldErrors()) {
			String errorMessage = "Invalid input for following properties:\n";
            for (FieldError f : br.getFieldErrors()) {
                errorMessage += f.getField() + ": " + f.getDefaultMessage() + "\n";
            }
            return new ResponseEntity<String>(errorMessage, HttpStatus.OK);
		}
		
		else 
			userService.saveUser(user);
			return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("user/getAll")
	public ResponseEntity<List<User>> getAllFlight(){
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.FOUND);
	}
	
	@DeleteMapping("user/delete")
    public ResponseEntity<String> deleteFlightById(@RequestParam Long userId) {
        if (userService.existById(userId)) {
        	userService.deleteUserById(userId);
            return new ResponseEntity<String>("User deleted with id=" + userId, HttpStatus.ACCEPTED);
        }
        else {
            return new ResponseEntity<String>("User does not exist with id=" + userId, HttpStatus.NOT_FOUND);
        }
    }

}
