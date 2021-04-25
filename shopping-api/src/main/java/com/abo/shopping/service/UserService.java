package com.abo.shopping.service;

import com.abo.shoppingclient.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    public UserDTO getUserByCpf(String identification) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/identification/" + identification;
        ResponseEntity<UserDTO> response =
                restTemplate.getForEntity(url, UserDTO.class);
        return response.getBody();
    }
}