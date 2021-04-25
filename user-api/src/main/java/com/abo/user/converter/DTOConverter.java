package com.abo.user.converter;

import com.abo.shoppingclient.dto.UserDTO;
import com.abo.user.model.User;

public class DTOConverter {

    public static UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setAddress(user.getAddress());
        userDTO.setIdentification(user.getIdentification());
        return userDTO;
    }

    public static User convert(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setAddress(userDTO.getAddress());
        user.setIdentification(userDTO.getIdentification());
        return user;
    }
}
