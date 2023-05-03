package com.example.banking.security.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EditorRepository extends JpaRepository<Editor, Integer> {

  Optional<Editor> findByEmail(String email);

}
