package com.aplicationRegistreUser.controller;

import com.aplicationRegistreUser.DTO.UserRegistreDTO;
import com.aplicationRegistreUser.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registre")
public class RegistreUserController {

    @Autowired
    private UsersService usersService;

    @ModelAttribute("user")
    public UserRegistreDTO returnNewUserRegistreDTO() {
        return new UserRegistreDTO();
    }

    @GetMapping
    public String viewFormRegistre() {
        return "registre";
    }

    @PostMapping
    public String registreAccountOfUser(@ModelAttribute("user") UserRegistreDTO registreDTO) {
        usersService.saveUsers(registreDTO);
        return "redirect:/registre?exito";
    }
}
