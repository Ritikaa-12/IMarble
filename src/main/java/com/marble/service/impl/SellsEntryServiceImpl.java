package com.marble.service.impl;

import com.marble.dto.SellsEntryDto;
import com.marble.dto.SellsProductDto;
import com.marble.entities.*;
import com.marble.enums.SellsStatus;
import com.marble.repos.*;
import com.marble.service.SellsEntryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellsEntryServiceImpl implements SellsEntryService {

    private final SellsEntryRepository sellsEntryRepository;
    private final SellsProductRepository sellsProductRepository;
    private final ClientRepository clientRepository;
    private final ShopRepository shopRepository;
    private final ProductRepository productRepository;

    public SellsEntryServiceImpl(SellsEntryRepository sellsEntryRepository, SellsProductRepository sellsProductRepository, ClientRepository clientRepository, ShopRepository shopRepository, ProductRepository productRepository) {
        this.sellsEntryRepository = sellsEntryRepository;
        this.sellsProductRepository = sellsProductRepository;
        this.clientRepository = clientRepository;
        this.shopRepository = shopRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional // Ensures that if any part fails, the whole operation is rolled back
    public SellsEntryDto createSellsEntry(SellsEntryDto sellsEntryDto) {
        Client client = clientRepository.findById(sellsEntryDto.getClientId()).orElseThrow(() -> new RuntimeException("Client not found"));
        Shop shop = shopRepository.findById(sellsEntryDto.getShopId()).orElseThrow(() -> new RuntimeException("Shop not found"));

        SellsEntry sellsEntry = new SellsEntry();
        sellsEntry.setClient(client);
        sellsEntry.setShop(shop);
        sellsEntry.setDate(LocalDate.now());
        sellsEntry.setInvoiceNo(sellsEntryDto.getInvoiceNo());
        sellsEntry.setStatus(SellsStatus.BOOKED);
        
        float totalBillAmount = 0f;
        List<SellsProduct> sellsProductList = new ArrayList<>();

        for (SellsProductDto productDto : sellsEntryDto.getSellsProducts()) {
            Product product = productRepository.findById(productDto.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
            
            SellsProduct sellsProduct = new SellsProduct();
            sellsProduct.setProduct(product);
            sellsProduct.setQuantity(productDto.getQuantity());
            
            float amount = (float) (product.getPricePerUnit() * productDto.getQuantity());
            sellsProduct.setAmount(amount);
            totalBillAmount += amount;
            
            sellsProduct.setSellsEntry(sellsEntry); // Link back to the parent entry
            sellsProductList.add(sellsProduct);
        }

        sellsEntry.setBillAmount(totalBillAmount);
        sellsEntry.setSellsProducts(sellsProductList);

        SellsEntry savedSellsEntry = sellsEntryRepository.save(sellsEntry);
        
        return entityToDto(savedSellsEntry);
    }

    @Override
    public SellsEntryDto getSellsEntryById(Integer sellsEntryId) {
        // 1. Find the SellsEntry by its ID from the database.
        SellsEntry sellsEntry = sellsEntryRepository.findById(sellsEntryId)
                .orElseThrow(() -> new RuntimeException("Sells Entry not found with id: " + sellsEntryId));
        
        // 2. Convert the found entity to a DTO and return it.
        return entityToDto(sellsEntry);
    }

    @Override
    public SellsEntryDto updateSellsEntryStatus(Integer sellsEntryId, SellsStatus status) {
        // 1. Find the SellsEntry to update.
        SellsEntry sellsEntry = sellsEntryRepository.findById(sellsEntryId)
                .orElseThrow(() -> new RuntimeException("Sells Entry not found with id: " + sellsEntryId));

        // 2. Set the new status.
        sellsEntry.setStatus(status);

        // 3. Save the changes back to the database.
        SellsEntry updatedSellsEntry = sellsEntryRepository.save(sellsEntry);

        // 4. Convert the updated entity to a DTO and return it.
        return entityToDto(updatedSellsEntry);
    }

    
    private SellsEntryDto entityToDto(SellsEntry sellsEntry) {
        SellsEntryDto dto = new SellsEntryDto();
        dto.setSellsEntryId(sellsEntry.getSellsEntryId());
        dto.setType(sellsEntry.getType());
        dto.setBillAmount(sellsEntry.getBillAmount());
        dto.setDate(sellsEntry.getDate());
        dto.setInvoiceNo(sellsEntry.getInvoiceNo());
        dto.setStatus(sellsEntry.getStatus());
        dto.setClientId(sellsEntry.getClient().getClientId());
        dto.setShopId(sellsEntry.getShop().getShopId());


        if (sellsEntry.getSellsProducts() != null) {
            List<SellsProductDto> productDtos = sellsEntry.getSellsProducts().stream()
                    .map(this::sellsProductEntityToDto) 
                    .collect(Collectors.toList());
            dto.setSellsProducts(productDtos);
        }
        
        return dto;
    }

  
    private SellsProductDto sellsProductEntityToDto(SellsProduct sellsProduct) {
        SellsProductDto dto = new SellsProductDto();
        dto.setSellsProductId(sellsProduct.getSellsProductId());
        dto.setQuantity(sellsProduct.getQuantity());
        dto.setAmount(sellsProduct.getAmount());
        dto.setSellsEntryId(sellsProduct.getSellsEntry().getSellsEntryId());
        dto.setProductId(sellsProduct.getProduct().getProductId());
        

        dto.setProductName(sellsProduct.getProduct().getTitle());
        dto.setProductPricePerUnit(sellsProduct.getProduct().getPricePerUnit());
        
        return dto;
    }
}