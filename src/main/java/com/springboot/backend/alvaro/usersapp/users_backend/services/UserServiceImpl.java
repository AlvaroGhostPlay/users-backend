package com.springboot.backend.alvaro.usersapp.users_backend.services;

import com.springboot.backend.alvaro.usersapp.users_backend.entities.User;
import com.springboot.backend.alvaro.usersapp.users_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void insertUser(User user) {
        repository.insertUser(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword()
        );
    }

    @Override
    @Transactional
    public User updateUser(Long id, User user) {
        repository.updateUser(
                id,
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword()
        );
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("Error: Usuario no encontrado después de la actualización."));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List)repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(@NonNull Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return this.repository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
