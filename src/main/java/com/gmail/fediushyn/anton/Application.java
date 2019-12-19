package com.gmail.fediushyn.anton;

import com.gmail.fediushyn.anton.DTO.UsersDTO;
import com.gmail.fediushyn.anton.services.UsersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(final UsersService usersService) {
        return strings -> {
            try {
                List<UsersDTO> users = usersService.getAllUsers();
                if ((users == null) || (users.isEmpty())) {
                    usersService.addUser(new UsersDTO("first name 1", "last name 1", "e-mail 1", "phone 1"));
                    usersService.addUser(new UsersDTO("first name 2", "last name 2", "e-mail 2", "phone 2"));
                    usersService.addUser(new UsersDTO("first name 3", "last name 3", "e-mail 3", "phone 3"));
                    usersService.addUser(new UsersDTO("first name 4", "last name 4", "e-mail 4", "phone 4"));
                    usersService.addUser(new UsersDTO("first name 5", "last name 5", "e-mail 5", "phone 5"));
                }
            } catch (Exception e){

            }
        };
    }
}

