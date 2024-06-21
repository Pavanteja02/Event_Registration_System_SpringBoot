package com.abc.event_registration_system.service;

import com.abc.event_registration_system.dto.response.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO findById(Long id);
    UserDTO findByUsername(String username);
    List<UserDTO> findAll();
}
