package com.springboot.backend.alvaro.usersapp.users_backend.repositories;

import com.springboot.backend.alvaro.usersapp.users_backend.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO PGMAV.USERS (ID, NAME, LASTNAME, EMAIL, USERNAME, PASSWORD) " +
            "VALUES (:id, :name, :lastname, :email, :username, :password)", nativeQuery = true)
    void insertUser(Long id, String name, String lastname, String email, String username, String password);

    // Update personalizado
    @Transactional
    @Modifying
    @Query(value = "UPDATE PGMAV.USERS SET NAME = :name, LASTNAME = :lastname, EMAIL = :email, " +
            "USERNAME = :username, PASSWORD = :password WHERE ID = :id", nativeQuery = true)
    void updateUser(Long id, String name, String lastname, String email, String username, String password);

    @Query(value = "SELECT * FROM PGMAV.USERS ORDER BY ID DESC FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
    Optional<User> findLastUser();



}
