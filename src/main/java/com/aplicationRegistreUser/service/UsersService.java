package com.aplicationRegistreUser.service;

import com.aplicationRegistreUser.DTO.UserRegistreDTO;
import com.aplicationRegistreUser.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsersService extends UserDetailsService {


    User saveUsers(UserRegistreDTO registreDTO);

    List<User> listUsers();
}
