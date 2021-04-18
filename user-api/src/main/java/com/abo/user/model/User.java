package com.abo.user.model;

import com.abo.user.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String identification;
    private String address;
    private String email;
    private String phone;
    private Date registerDate;

    // gets e sets omitidos
    public static User convert(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setIdentification(userDTO.getIdentification());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setRegisterDate(userDTO.getRegisterDate());
        return user;
    }

}
