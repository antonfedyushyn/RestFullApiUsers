package com.gmail.fediushyn.anton.controllers;

import com.gmail.fediushyn.anton.DTO.UsersDTO;
import com.gmail.fediushyn.anton.DTO.UsersGetResponse;
import com.gmail.fediushyn.anton.DTO.UsersModifyResponse;
import com.gmail.fediushyn.anton.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(value = "{id}")
    public UsersGetResponse getUser(@PathVariable("id") Long id){
        return usersService.getUserById(id);
    }

    @GetMapping
    public List<UsersDTO> getUsers(){
        return usersService.getAllUsers();
    }

    @PostMapping
    public UsersModifyResponse addUser(UsersDTO user){
        return usersService.addUser(user);
    }

    @PutMapping
    public UsersModifyResponse editUser(UsersDTO user){
        return usersService.updateUser(user);
    }

    @DeleteMapping
    public UsersModifyResponse deleteUser(@RequestParam("id") Long id){
        return usersService.deleteUser(id);
    }

}
