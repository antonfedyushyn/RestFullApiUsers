package com.gmail.fediushyn.anton.services;

import com.gmail.fediushyn.anton.DTO.UsersDTO;
import com.gmail.fediushyn.anton.DTO.UsersGetResponse;
import com.gmail.fediushyn.anton.DTO.UsersModifyResponse;
import com.gmail.fediushyn.anton.entity.Users;
import com.gmail.fediushyn.anton.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    private static final int CODE_SUCCESS   = 0;
    private static final int CODE_ERROR     = 1;

    @Autowired
    UsersServiceImpl(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Transactional(readOnly = true)
    public List<UsersDTO> getAllUsers(){
        List<Users> users = usersRepository.findAll();
        List<UsersDTO> res = new ArrayList<>();
        for (Users user: users) {
            res.add(new UsersDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone()));
        }
        return res;
    }

    @Transactional(readOnly = true)
    public UsersGetResponse getUserById(long id){
        Users user = usersRepository.findUsersById(id);
        if (user == null) {
            return new UsersGetResponse(CODE_ERROR, "user with ID "+id+" not found!", null);
        } else {
            return new UsersGetResponse(CODE_SUCCESS, "", new UsersDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone()));
        }

    }

    @Transactional
    public UsersModifyResponse addUser(UsersDTO user){
        if ((user.getFirstName() == null) || (user.getFirstName().isEmpty())) return new UsersModifyResponse(CODE_ERROR, "not set first name!");
        if ((user.getLastName() == null) || (user.getLastName().isEmpty())) return new UsersModifyResponse(CODE_ERROR, "not set last name!");
        if (usersRepository.existsUsersByFirstNameAndLastName(user.getFirstName(), user.getLastName()))
            return new UsersModifyResponse(CODE_ERROR, "user wiht this first name and last name exists!");
        usersRepository.save(new Users(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone()));
        return new UsersModifyResponse(CODE_SUCCESS, "");
    }

    @Transactional
    public UsersModifyResponse updateUser(UsersDTO user){
        if (user.getId() <= 0) return new UsersModifyResponse(CODE_ERROR, "not set ID user!");
        if ((user.getFirstName() == null) || (user.getFirstName().isEmpty())) return new UsersModifyResponse(CODE_ERROR, "not set first name!");
        if ((user.getLastName() == null) || (user.getLastName().isEmpty())) return new UsersModifyResponse(CODE_ERROR, "not set last name!");
        Users users = usersRepository.findUsersById(user.getId());
        if (users == null) return new UsersModifyResponse(CODE_ERROR, "user woth this ID not found!");
        users.setFirstName(user.getFirstName());
        users.setLastName(user.getLastName());
        users.setEmail(user.getEmail());
        users.setPhone(user.getPhone());
        usersRepository.save(users);
        return new UsersModifyResponse(CODE_SUCCESS, "");
    }

    @Transactional
    public UsersModifyResponse deleteUser(long id){
        if (usersRepository.existsUsersById(id)) {
            try {
                //usersRepository.deleteUsersById(user.getId());
                usersRepository.deleteById(id);
            } catch (Exception e) {
                return new UsersModifyResponse(CODE_ERROR, "error delete udser! "+e.getMessage());
            }
        }
        return new UsersModifyResponse(CODE_SUCCESS, "");
    }

    @Transactional
    public UsersModifyResponse deleteUser(UsersDTO user){
        if (usersRepository.existsUsersById(user.getId()))
            //usersRepository.deleteUsersById(user.getId());
            usersRepository.deleteById(user.getId());
        return new UsersModifyResponse(CODE_SUCCESS, "");
    }
}
