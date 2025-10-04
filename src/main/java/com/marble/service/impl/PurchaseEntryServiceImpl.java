package com.marble.service.impl;

import com.marble.dto.PurchaseEntryDto;
import com.marble.entities.Client;
import com.marble.entities.Purchase_Entry;
import com.marble.repos.ClientRepository;
import com.marble.repos.PurchaseEntryRepository;
import com.marble.service.PurchaseEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseEntryServiceImpl implements PurchaseEntryService {

    @Autowired
    private PurchaseEntryRepository purchaseEntryRepository;

    @Autowired
    private ClientRepository clientRepository;

    // ---------------- DTO <-> Entity Mapper ----------------
    private PurchaseEntryDto entityToDto(Purchase_Entry purchaseEntry) {
        PurchaseEntryDto dto = new PurchaseEntryDto();
        dto.setPurchaseEntryId(purchaseEntry.getPurchaseEntryId());
        dto.setPurchaseDate(purchaseEntry.getPurchaseDate());
        dto.setInvoiceNo(purchaseEntry.getInvoiceNo());
        dto.setTotalAmount(purchaseEntry.getTotalAmount());
        dto.setClientId(purchaseEntry.getClient().getClientId());
        return dto;
    }

    private Purchase_Entry dtoToEntity(PurchaseEntryDto dto) {
        Purchase_Entry entry = new Purchase_Entry();
        entry.setPurchaseEntryId(dto.getPurchaseEntryId());
        entry.setPurchaseDate(dto.getPurchaseDate() != null ? dto.getPurchaseDate() : LocalDate.now());
        entry.setInvoiceNo(dto.getInvoiceNo());
        entry.setTotalAmount(dto.getTotalAmount());

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id " + dto.getClientId()));
        entry.setClient(client);

        return entry;
    }

    // ---------------- Service Methods ----------------

    @Override
    public PurchaseEntryDto createPurchaseEntry(PurchaseEntryDto dto) {
        Purchase_Entry entry = dtoToEntity(dto);
        Purchase_Entry saved = purchaseEntryRepository.save(entry);
        return entityToDto(saved);
    }

    @Override
    public PurchaseEntryDto getPurchaseEntryById(Integer id) {
        Purchase_Entry entry = purchaseEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase entry not found with id " + id));
        return entityToDto(entry);
    }

    @Override
    public List<PurchaseEntryDto> getAllPurchaseEntry() {
        return purchaseEntryRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseEntryDto updatePurchaseEntry(Integer id, PurchaseEntryDto dto) {
        Purchase_Entry existing = purchaseEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase entry not found with id " + id));

        existing.setPurchaseDate(dto.getPurchaseDate());
        existing.setInvoiceNo(dto.getInvoiceNo());
        existing.setTotalAmount(dto.getTotalAmount());

        if (!existing.getClient().getClientId().equals(dto.getClientId())) {
            Client client = clientRepository.findById(dto.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client not found with id " + dto.getClientId()));
            existing.setClient(client);
        }

        Purchase_Entry updated = purchaseEntryRepository.save(existing);
        return entityToDto(updated);
    }

    @Override
    public void deletePurchaseEntry(Integer id) {
        Purchase_Entry entry = purchaseEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase entry not found with id " + id));
        purchaseEntryRepository.delete(entry);
    }
}
