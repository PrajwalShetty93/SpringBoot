package com.boot.todo.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.todo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

	List<User> findByRole(String string);
	Optional<User> findById(Long id);
}