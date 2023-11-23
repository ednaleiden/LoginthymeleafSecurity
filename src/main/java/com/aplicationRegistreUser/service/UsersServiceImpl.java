package com.aplicationRegistreUser.service;

import com.aplicationRegistreUser.DTO.UserRegistreDTO;
import com.aplicationRegistreUser.model.Role;
import com.aplicationRegistreUser.model.User;
import com.aplicationRegistreUser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private UserRepository repository;

    public UsersServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User saveUsers(UserRegistreDTO registreDTO) {
        User user = new User(registreDTO.getName(),
                registreDTO.getLastName(),
                registreDTO.getEmail(),
                passwordEncoder.encode(registreDTO.getPassword()),
                Arrays.asList(new Role("ROLE_USER")));
        return repository.save(user);
    }

    @Override
    public List<User> listUsers() {
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User or password invalidate");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), mapperateAuthoritiesRoles(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapperateAuthoritiesRoles(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    
}
