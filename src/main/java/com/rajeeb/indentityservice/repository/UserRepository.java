package com.rajeeb.indentityservice.repository;

import com.rajeeb.indentityservice.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserCredential,Integer> {
    Optional<UserCredential>    findByUsername(String username);
}
