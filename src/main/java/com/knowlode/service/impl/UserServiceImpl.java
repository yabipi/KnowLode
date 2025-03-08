package com.knowlode.service.impl;

import com.knowlode.dto.UserDTO;
import com.knowlode.entity.User;
import com.knowlode.repository.UserRepository;
import com.knowlode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user = userRepository.save(user);
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        BeanUtils.copyProperties(userDTO, user, "id");
        user = userRepository.save(user);
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(user, userDTO);
                    return userDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
} 