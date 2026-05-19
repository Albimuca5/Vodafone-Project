package org.example.vodafoneproject.repositories;

import org.example.vodafoneproject.models.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBalanceRepository extends JpaRepository<UserBalance, Long> {
}