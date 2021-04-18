package com.abo.user.controller;

import com.abo.user.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    public static List<UserDTO> usuarios = new ArrayList<UserDTO>();

    @GetMapping("/")
    public String getMessage() {
        return "user-api is working!";
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return usuarios;
    }

    @GetMapping("/users/{cpf}")
    public UserDTO getUsersFilter(@PathVariable String cpf) {
        for (UserDTO userFilter: usuarios) {
            if (userFilter.getId().equals(cpf)) {
                return userFilter;
            }
        }
        return null;
    }

    @PostMapping("/newUser")
    UserDTO insert(@RequestBody UserDTO userDTO) {
        userDTO.setRegisterDate(new Date());
        usuarios.add(userDTO);
        return userDTO;
    }

    @DeleteMapping("/user/{cpf}")
    public boolean delete(@PathVariable String cpf) {
        for (UserDTO userFilter: usuarios) {
            if (userFilter.getId().equals(cpf)) {
                usuarios.remove(userFilter);
                return true;
            }
        }
        return false;
    }
}
