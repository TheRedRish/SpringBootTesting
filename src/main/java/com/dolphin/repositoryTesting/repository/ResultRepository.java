package com.dolphin.repositoryTesting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dolphin.repositoryTesting.model.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {
}
