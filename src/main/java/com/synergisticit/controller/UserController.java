package com.synergisticit.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;
import com.synergisticit.validation.UserValidator;

import jakarta.validation.Valid;

@Controller
public class UserController {
    
    @Autowired UserService userService;
    @Autowired RoleService roleService;
    @Autowired UserValidator userValidator;
    
    //@PreAuthorize("hasAuthority('Admin')")
    @RequestMapping("userForm")
    public String userForm(User user, Model model) {
        modelData(model);
        return "userForm";
    }
    
    @RequestMapping("saveUser")
    public String saveUser(@Valid @ModelAttribute User user, BindingResult br, Model model) {  // BindingResult must come before Model, otherwise Model will send to error page before BindingResult do its job
        userValidator.validate(user, br);
                
        if (!br.hasErrors()) {
            userService.saveUser(user);
            // do not need to call modelData(model) because it's in userForm method
            return "redirect:userForm";  // redirect to url userForm which calls userForm method
        }
        
        modelData(model);
        return "userForm";  // do not redirect, keep the info user entered and show error messages
    }
    
    @RequestMapping("updateUser")
    public String updateUser(User user, Model model) {  // User object created with only userId is passed in by request param
        User retrievedUser = userService.getUserById(user.getUserId());
        
        // Having model.addAttribute("user", retrievedUser) and letting user form to be filled in by path without specifying value
        // led to error due to user.role to be filled in selection form of String - cannot convert Object (Role) to String.
        model.addAttribute("retrievedUser", retrievedUser);
        
        List<Role> selectedRoles = new ArrayList<>(retrievedUser.getRoles());
        model.addAttribute("selectedRoles", selectedRoles);
        
        modelData(model);
        return "userForm";
    }
    
    @RequestMapping("deleteUser")
    public String deleteUser(User user, Model model) {
        userService.deleteUserById(user.getUserId());
        
        modelData(model);
        return "redirect:userForm";
    }
    
    private void modelData(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("ListofAllRoles", roleService.getAllRoles());
    }
}
