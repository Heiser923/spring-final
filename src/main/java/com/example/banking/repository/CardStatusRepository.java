package com.example.banking.repository;

import com.example.banking.entities.CardStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardStatusRepository extends JpaRepository<CardStatus, Long> {
}
