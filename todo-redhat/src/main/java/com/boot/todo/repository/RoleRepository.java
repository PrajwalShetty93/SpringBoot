package com.boot.todo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.todo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	ArrayList<Role> findByUsername(String name);

	List<Role> findByUsernameAndRole(String username, String string);


}