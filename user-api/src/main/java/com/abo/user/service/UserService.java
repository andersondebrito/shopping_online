package com.abo.user.service;

import com.abo.shoppingclient.dto.UserDTO;
import com.abo.user.converter.DTOConverter;
import com.abo.user.model.User;
import com.abo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAll() {
        List<User> usuarios = userRepository.findAll();
        return usuarios
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById(long userId) {
        Optional<User> usuario = userRepository.findById(userId);
        if (usuario.isPresent()) {
            return DTOConverter.convert(usuario.get());
        }
        return null;
    }

    public UserDTO save(UserDTO userDTO) {
        User user = userRepository.save(DTOConverter.convert(userDTO));
        return DTOConverter.convert(user);
    }

    public UserDTO delete(long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        }
        return null;
    }

    public UserDTO findByIdentification(String identification) {
        User user = userRepository.findByIdentification(identification);
        if (user != null) {
            return DTOConverter.convert(user);
        }
        return null;
    }

    public List<UserDTO> queryByName(String name) {
        List<User> usuarios = userRepository.queryByNameLike(name);
        return usuarios
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }
}
