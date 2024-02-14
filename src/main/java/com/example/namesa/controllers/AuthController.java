package com.example.namesa.controllers;


import com.example.namesa.config.WebSecurityConfig;
import com.example.namesa.entities.Branch;
import com.example.namesa.entities.Visitor;
import com.example.namesa.service.ServiceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.*;

@Controller
public class AuthController {
    private PasswordEncoder passwordEncoder = WebSecurityConfig.passwordEncoder();
    private final ServiceHolder serviceHolder;
    @Autowired
    public AuthController(ServiceHolder serviceHolder) {
        this.serviceHolder = serviceHolder;
    }
    @GetMapping("/login")
    private String getLogin() {
        return "login";}
    @GetMapping("/reg")
    private String getReg(){return "reg";}
    @PostMapping("/reg")
    private  String reg(@RequestParam(name = "visitorLogin")String login,
                        @RequestParam(name = "visitorUsername")String username,
                        @RequestParam(name = "eMail")String eMail,
                        @RequestParam(name = "phoneNumber")String phoneNumber,
                        @RequestParam(name="visitorPassword")String visitorPassword){
        if(!serviceHolder.register(new Visitor(username, login, passwordEncoder.encode(visitorPassword), eMail, phoneNumber))) {
            return "reg";
        }
        return "login";
    }
    @GetMapping("/list")
    private String list(Model model)
    {
        model.addAttribute("objects", serviceHolder.getRowsBranches());
        return "list";
    }
    @GetMapping("/branch")
    private String branch(Model model, @RequestParam(name="branch")int branch)
    {
        model.addAttribute("branch", serviceHolder.getBranchById(branch));
        return "branch";
    }
    
}
