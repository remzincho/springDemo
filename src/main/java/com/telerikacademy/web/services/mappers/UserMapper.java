package com.telerikacademy.web.services.mappers;

import com.telerikacademy.web.models.DTOs.UserDTO;
import com.telerikacademy.web.models.User;
import com.telerikacademy.web.repositories.contracts.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final IUserRepository userRepository;

    @Autowired
    public UserMapper(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User fromDto(UserDTO userDTO) {
        User user = new User();
        dtoToObject(userDTO, user);
        return user;
    }

    public User fromDto(UserDTO userDTO, int id) {
        User user = userRepository.getById(id);
        dtoToObject(userDTO, user);
        return user;
    }

    private void dtoToObject(UserDTO userDTO, User user) {
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setAdmin(false);
    }
}
