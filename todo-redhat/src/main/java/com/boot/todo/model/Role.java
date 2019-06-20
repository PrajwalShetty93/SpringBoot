package com.boot.todo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Role {
    public Role( String username, String role) {
		super();
		this.username = username;
		this.role = role;
	}
    
    public Role()
    {
    	
    }

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  private String username;

   public String getUsername() {
	return username;
}

public Role setUsername(String username) {
	this.username = username;
	return this;
}

private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


	

	public String getRole() {
		return role;
	}

	public Role setRole(String role) {
		this.role = role;
		return this;
	}
	
	public Role build()
	{
		return new Role(username,role);
	}

  
}
