package com.gmail.fediushyn.anton.services;

import com.gmail.fediushyn.anton.DTO.UsersDTO;
import com.gmail.fediushyn.anton.DTO.UsersGetResponse;
import com.gmail.fediushyn.anton.DTO.UsersModifyResponse;

import java.util.List;

public interface UsersService {
    List<UsersDTO> getAllUsers();

    UsersGetResponse getUserById(long id);

    UsersModifyResponse addUser(UsersDTO user);

    UsersModifyResponse updateUser(UsersDTO user);

    UsersModifyResponse deleteUser(long id);

    UsersModifyResponse deleteUser(UsersDTO user);
}
