package com.abo.shopping.service;

import com.abo.shoppingclient.dto.UserDTO;
import com.abo.shoppingclient.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserService {

    public UserDTO getUserByIdentification(String identification, String key) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl("http://localhost:8080/user/identification/" + "/user/identification/" + identification);
            builder.queryParam("key", key);
            ResponseEntity<UserDTO> response = restTemplate
                    .getForEntity(builder.toUriString(), UserDTO.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException();
        }
    }
}
