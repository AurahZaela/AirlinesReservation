package com.synergisticit.controller;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller  // As long as session (logged-in, cookie not expired) is there, this controller bean is in scope inside IOC container.
@SessionAttributes("user")  // IOC container has all the beans (objects) and lets you autowire (inject) them. Default scope is singleton scope.
public class LoginController {
    
    @Autowired UserService userService;
    @Autowired RoleService roleService;
    
    @GetMapping("login")
    public String login(@RequestParam(required = false) String logout, @RequestParam(required = false) String error,
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {
        
        String messageAfterLogInOut = "";  // message after login logout
        
        if (error != null) {  // error is empty string, not null, if parameter has passed (login?error)
            messageAfterLogInOut = "Invalid Credentials";
        }
        if (logout != null) {  // logout is empty string, not null, if parameter has passed (login?logout)
            // Following lines can also be done by SecurityConfig .logout() - invalidate session & clear auth: .invalidateHttpSession(true).clearAuthentication(true)
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, auth);
            }
            messageAfterLogInOut = "Logged out";
        }
        model.addAttribute("messageAfterLogInOut", messageAfterLogInOut);
        
        // return login.jsp if login failed. If success, go to home using SecurityConfig: .defaultSuccessUrl("/home", true)
        return "login";
    }
    
    @GetMapping("accessDenied")
    public String accessDeniedPage(Principal principal, Model model) {  // User is logged in but its role do not have access.
        String message = principal.getName() + ", unauthorized access";
        model.addAttribute("message", message);
        return "accessDenied";
    }
    
    @GetMapping("register")
    public String register(User user, Model model) {  // not including parameters caused "Neither BindingResult nor plain target object for bean name 'user' available as request attribute" error.
        return "register";
    }
    
    @PostMapping("registerUser")
    public String registerUser(@ModelAttribute User user, BindingResult br, Model model) {  // BindingResult must come before Model, otherwise Model will send to error page before BindingResult do its job.
        System.out.println("why not working");
        if (!br.hasErrors()) {
            Role userRole = roleService.getRoleById(2L);  // USER is role id 3.
            List<Role> roleList = new ArrayList<>();
            roleList.add(userRole);
            
            user.setRoles(roleList);
            userService.saveUser(user);
            model.addAttribute("messageAfterLogInOut", "Thank you for sign up. Please sign in.");
            return "login";
        }
        // else
        model.addAttribute("messageAfterLogInOut", "Error occured during sign up.");
        return "register";  // do not redirect (redirect:register), keep the info user entered and show error messages
    }
    
    /*
    @GetMapping("/user/{username}")
    @ResponseBody
    public String getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username).getEmail();
    }
    */

}
