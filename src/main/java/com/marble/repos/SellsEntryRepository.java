package com.marble.repos;

import com.marble.entities.SellsEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SellsEntryRepository extends JpaRepository<SellsEntry, Integer> {
    
    List<SellsEntry> findByClientId(Integer clientId);
    Optional<SellsEntry> findByInvoiceNo(String invoiceNo);
}