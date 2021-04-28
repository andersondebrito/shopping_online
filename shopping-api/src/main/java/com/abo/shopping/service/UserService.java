package com.abo.shopping.service;

import com.abo.shoppingclient.dto.UserDTO;
import com.abo.shoppingclient.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UserService {

    @Value("${USER_API_URL:http://localhost:8080/user/identification/}")
    private String userApiURL;

    public UserDTO getUserByIdentification(String identification, String key) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            UriComponentsBuilder builder = UriComponentsBuilder
                    .fromHttpUrl(userApiURL + identification);
            builder.queryParam("key", key);
            ResponseEntity<UserDTO> response = restTemplate
                    .getForEntity(builder.toUriString(), UserDTO.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException();
        }
    }
}
