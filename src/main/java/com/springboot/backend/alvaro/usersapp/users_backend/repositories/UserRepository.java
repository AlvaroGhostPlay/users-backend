package com.springboot.backend.alvaro.usersapp.users_backend.repositories;

import com.springboot.backend.alvaro.usersapp.users_backend.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
