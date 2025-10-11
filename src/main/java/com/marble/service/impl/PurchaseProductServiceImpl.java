package com.marble.service.impl;

import com.marble.dto.PurchaseProductDto;
import com.marble.entities.Product;
import com.marble.entities.PurchaseProduct;
import com.marble.entities.Purchase_Entry;
import com.marble.repos.ProductRepository;
import com.marble.repos.PurchaseEntryRepository;
import com.marble.repos.PurchaseProductRepository;
import com.marble.service.PurchaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseProductServiceImpl implements PurchaseProductService {

    @Autowired
    private PurchaseProductRepository purchaseProductRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private PurchaseEntryRepository purchaseEntryRepo;

    // Convert entity to DTO
    private PurchaseProductDto convertToDto(PurchaseProduct entity) {
        PurchaseProductDto dto = new PurchaseProductDto();
        dto.setPurchaseProductId(entity.getPurchaseProductId());
        dto.setPurchaseEntryId(entity.getPurchaseEntry().getPurchaseEntryId());
        dto.setProductId(entity.getProduct().getProductId());
        dto.setQuantity(entity.getQuantity());
        dto.setAmount(entity.getAmount());
        return dto;
    }

    // Convert DTO to entity
    private PurchaseProduct convertToEntity(PurchaseProductDto dto) {
        PurchaseProduct entity = new PurchaseProduct();
        if(dto.getPurchaseProductId() != null) {
            entity.setPurchaseProductId(dto.getPurchaseProductId());
        }
        Purchase_Entry entry = purchaseEntryRepo.findById(dto.getPurchaseEntryId())
                .orElseThrow(() -> new RuntimeException("PurchaseEntry not found"));
        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        entity.setPurchaseEntry(entry);
        entity.setProduct(product);
        entity.setQuantity(dto.getQuantity());
        entity.setAmount(dto.getAmount());
        return entity;
    }

    @Override
    public PurchaseProductDto addProductToProductEntry(PurchaseProductDto dto) {
        PurchaseProduct entity = convertToEntity(dto);
        return convertToDto(purchaseProductRepo.save(entity));
    }

    @Override
    public List<PurchaseProductDto> getProductsForProductEntry(Integer purchaseEntryId) {
        List<PurchaseProduct> list = purchaseProductRepo.findByPurchaseEntry_PurchaseEntryId(purchaseEntryId);
        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public PurchaseProductDto updateProductQuantity(Integer purchaseProductId, Integer newQuantity) {
        PurchaseProduct entity = purchaseProductRepo.findById(purchaseProductId)
                .orElseThrow(() -> new RuntimeException("PurchaseProduct not found"));
        entity.setQuantity(newQuantity);
        return convertToDto(purchaseProductRepo.save(entity));
    }

    @Override
    public void removeProductFromPurchaseEntry(Integer purchaseProductId) {
        purchaseProductRepo.deleteById(purchaseProductId);
    }

    @Override
    public PurchaseProductDto getPurchaseProductById(Integer purchaseProductId) {
        PurchaseProduct entity = purchaseProductRepo.findById(purchaseProductId)
                .orElseThrow(() -> new RuntimeException("PurchaseProduct not found"));
        return convertToDto(entity);
    }
}
