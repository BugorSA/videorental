package com.example.repositories;

import com.example.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByGoogleUsername(String googleUsername);

    User findByName(String username);

    User findByGoogleName(String username);
}
