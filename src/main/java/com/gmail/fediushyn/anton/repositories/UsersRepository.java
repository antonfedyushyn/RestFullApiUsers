package com.gmail.fediushyn.anton.repositories;

import com.gmail.fediushyn.anton.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findUsersById(long id);

    //List<Users> findAllOrderById();

    boolean existsUsersById(long id);

    @Query("delete from Users u where u.id = :id")
    void deleteUsersById(@Param("id") long id);

    boolean existsUsersByFirstNameAndLastName(String firstName, String lastName);
}
