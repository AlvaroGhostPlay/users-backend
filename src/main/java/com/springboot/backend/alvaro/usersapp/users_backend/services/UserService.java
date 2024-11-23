package com.springboot.backend.alvaro.usersapp.users_backend.services;

import com.springboot.backend.alvaro.usersapp.users_backend.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(@NonNull Long id);

    @Modifying
    User save(User user);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id = :id")
    void deleteById(Long id);

    void insertUser(User user);

    User updateUser(Long id, User user);

}
