package com.marble.service.impl;

import com.marble.dto.SellsProductDto;
import com.marble.entities.Product;
import com.marble.entities.SellsEntry;
import com.marble.entities.SellsProduct;
import com.marble.repos.ProductRepository;
import com.marble.repos.SellsEntryRepository;
import com.marble.repos.SellsProductRepository;
import com.marble.service.SellsProductService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellsProductServiceImpl implements SellsProductService {

    private final SellsProductRepository sellsProductRepository;
    private final SellsEntryRepository sellsEntryRepository;
    private final ProductRepository productRepository;

    public SellsProductServiceImpl(SellsProductRepository sellsProductRepository, SellsEntryRepository sellsEntryRepository, ProductRepository productRepository) {
        this.sellsProductRepository = sellsProductRepository;
        this.sellsEntryRepository = sellsEntryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public SellsProductDto addProductToSellsEntry(SellsProductDto sellsProductDto) {
        // Find the parent Sells_Entry record
        SellsEntry sellsEntry = sellsEntryRepository.findById(sellsProductDto.getSellsEntryId())
                .orElseThrow(() -> new RuntimeException("Sells Entry not found"));
        
        // Find the product being sold
        Product product = productRepository.findById(sellsProductDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        SellsProduct sellsProduct = new SellsProduct();
        sellsProduct.setSellsEntry(sellsEntry);
        sellsProduct.setProduct(product);
        sellsProduct.setQuantity(sellsProductDto.getQuantity());
        
        
        float amount = (float) (sellsProductDto.getQuantity() * product.getPricePerUnit());
        sellsProduct.setAmount(amount);

        SellsProduct savedSellsProduct = sellsProductRepository.save(sellsProduct);
        
      
        return entityToDto(savedSellsProduct);
    }

    @Override
    public List<SellsProductDto> getProductsForSellsEntry(Integer sellsEntryId) {
        List<SellsProduct> sellsProducts = sellsProductRepository.findBySellsEntrySellsEntryId(sellsEntryId);
        return sellsProducts.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public SellsProductDto updateProductQuantity(Integer sellsProductId, Integer newQuantity) {
        SellsProduct sellsProduct = sellsProductRepository.findById(sellsProductId)
                .orElseThrow(() -> new RuntimeException("Sells product line item not found"));

        sellsProduct.setQuantity(newQuantity);
     
        float newAmount = (float) (newQuantity * sellsProduct.getProduct().getPricePerUnit());
        sellsProduct.setAmount(newAmount);

        SellsProduct updatedSellsProduct = sellsProductRepository.save(sellsProduct);
        
     
        
        return entityToDto(updatedSellsProduct);
    }

    @Override
    public void removeProductFromSellsEntry(Integer sellsProductId) {
        SellsProduct sellsProduct = sellsProductRepository.findById(sellsProductId)
                .orElseThrow(() -> new RuntimeException("Sells product line item not found"));
        sellsProductRepository.delete(sellsProduct);
        
      
    }
    
  
    private SellsProductDto entityToDto(SellsProduct sellsProduct) {
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