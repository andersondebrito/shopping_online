package com.abo.user.controller;

import com.abo.user.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    public static List<UserDTO> users = new ArrayList<UserDTO>();

    @GetMapping("/")
    public String getMessage() {
        return "user-api is working!";
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return users;
    }

    @GetMapping("/users/{identification}")
    public UserDTO getUsersFilter(@PathVariable String identification) {
        for (UserDTO userFilter: users) {
            if (userFilter.getIdentification().equals(identification)) {
                return userFilter;
            }
        }
        return null;
    }

    @PostMapping("/newUser")
    UserDTO insert(@RequestBody UserDTO userDTO) {
        userDTO.setRegisterDate(new Date());
        users.add(userDTO);
        return userDTO;
    }

    @DeleteMapping("/user/{identification}")
    public boolean delete(@PathVariable String identification) {
        for (UserDTO userFilter: users) {
            if (userFilter.getIdentification().equals(identification)) {
                users.remove(userFilter);
                return true;
            }
        }
        return false;
    }
}
