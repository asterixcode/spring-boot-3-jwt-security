package com.asterixcode.springbootsecurity.user;

import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<User, Integer>{

  Optional<User> findByEmail(String email);

}
