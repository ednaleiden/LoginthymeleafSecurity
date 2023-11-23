package com.aplicationRegistreUser.controller;

import com.aplicationRegistreUser.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistreController {

    @Autowired
    private UsersService servicio;
    @GetMapping("/login")
    public String startSession(){
        return "login";
    }

    @GetMapping("/")
    public String seePageStart(Model model){
        model.addAttribute("users", servicio.listUsers());
        return "index";
    }
}
