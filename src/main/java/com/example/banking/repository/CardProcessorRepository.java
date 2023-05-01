package com.example.banking.repository;

import com.example.banking.entities.Card;
import com.example.banking.entities.CardProcessor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardProcessorRepository extends JpaRepository<CardProcessor, Long> {
}