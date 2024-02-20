package com.sivleen.securityservice.dao;

import com.sivleen.securityservice.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialDao extends JpaRepository<UserCredential,Integer> {
    Optional<UserCredential> findByName(String username);
}
