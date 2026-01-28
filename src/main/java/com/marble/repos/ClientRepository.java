package com.marble.repos;

import com.marble.entities.Client;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
	public interface ClientRepository extends JpaRepository<Client, Integer> {
	    Optional<Client> findByEmail(String email);
	}

