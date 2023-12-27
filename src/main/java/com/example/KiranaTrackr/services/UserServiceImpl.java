package com.example.KiranaTrackr.services;

import com.example.KiranaTrackr.dtos.User.UserRequestDTO;
import com.example.KiranaTrackr.dtos.User.UserResponseDTO;
import com.example.KiranaTrackr.models.User;
import com.example.KiranaTrackr.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        BeanUtils.copyProperties(userRequestDTO, user);

        User savedUser = userRepository.save(user);

        UserResponseDTO responseDTO = new UserResponseDTO();
        BeanUtils.copyProperties(savedUser, responseDTO);

        return responseDTO;
    }
}
