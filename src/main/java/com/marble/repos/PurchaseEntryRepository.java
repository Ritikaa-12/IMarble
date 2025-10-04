package com.marble.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marble.entities.Purchase_Entry;

@Repository
public interface PurchaseEntryRepository extends JpaRepository<Purchase_Entry, Integer> {
}