package com.abo.user.controller;

import com.abo.shoppingclient.dto.UserDTO;
import com.abo.user.exception.UserNotFoundException;
import com.abo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/")
    public List<UserDTO> getUsers() {
        List<UserDTO> users = userService.getAll();
        return users;
    }
    @GetMapping("/user/{id}")
    UserDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }
    @PostMapping("/user")
    UserDTO newUser(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }
    @GetMapping("/user/identification/{identification}")
    UserDTO findByCpf(@PathVariable String identification) {
        return userService.findByIdentification(identification);
    }

    @DeleteMapping("/user/{id}")
    UserDTO delete(@PathVariable Long id)
            throws UserNotFoundException {
        return userService.delete(id);
    }

    @GetMapping("/user/search")
    public List<UserDTO> queryByName(
            @RequestParam(name="name", required = true)
                    String name) {
        return userService.queryByName(name);
    }
}
