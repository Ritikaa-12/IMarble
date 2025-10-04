package com.marble.repos;

import com.marble.entities.Orderr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderrRepository extends JpaRepository<Orderr, Integer> {
}