package com.mobin.quizappstarter.repositories;

import com.mobin.quizappstarter.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Integer> {
}
