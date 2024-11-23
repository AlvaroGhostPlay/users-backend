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
        Optional<User> lastUser = repository.findLastUser();
        // Calcular el siguiente ID (asignar 1 si no hay registros)
        Long nextId = lastUser.map(u -> u.getId() + 1).orElse(1L);

        // Asignar el ID calculado al nuevo usuario
        user.setId(nextId);
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
