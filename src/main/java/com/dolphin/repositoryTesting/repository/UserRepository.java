package com.dolphin.repositoryTesting.repository;

import com.dolphin.repositoryTesting.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
