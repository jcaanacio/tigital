package com.accenture.tigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.accenture.tigital.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
