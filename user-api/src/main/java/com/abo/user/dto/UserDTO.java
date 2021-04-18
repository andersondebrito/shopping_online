package com.abo.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class UserDTO {

    private String name;
    private String id;
    private String address;
    private String email;
    private String phone;
    private Date registerDate;
}
