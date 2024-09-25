package com.synergisticit.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
    @RequestMapping(value={"home", "/"})
    public String home(Principal principal, Model model) {
        modelData(principal, model);
        return "home";
    }
    
    private void modelData(Principal principal, Model model) {
        String username = null;
        if (principal != null) {
            username = principal.getName();
        }
        
        
        // get this by either request.getAttribute("username") as <% javacodes %> or ${username} as javascript codes or "${username}" as jQuery codes in home.jsp.
        model.addAttribute("username", username);
        
    }
    
}
