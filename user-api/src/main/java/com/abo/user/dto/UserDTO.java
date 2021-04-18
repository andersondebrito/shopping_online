package com.abo.user.dto;

import com.abo.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDTO {

    private String name;
    private String identification;
    private String address;
    private String email;
    private String phone;
    private Date registerDate;

    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setAddress(user.getAddress());
        userDTO.setIdentification(user.getIdentification());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setRegisterDate(user.getRegisterDate());
        return userDTO;
    }
}
