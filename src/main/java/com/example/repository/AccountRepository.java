package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    boolean existsByUsername(String username);
    
    @Query("SELECT a FROM Account a WHERE a.username = :username AND a.password = :password")
    Optional<Account> findByUsernameAndPassword(String username, String password);
}
