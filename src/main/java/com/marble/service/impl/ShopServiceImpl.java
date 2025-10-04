package com.marble.service.impl;


import org.springframework.stereotype.Service;

import com.marble.dto.ShopDto;
import com.marble.entities.Shop;
import com.marble.repos.ShopRepository;
import com.marble.service.ShopService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    // Entity -> DTO
    private ShopDto entityToDto(Shop shop) {
        ShopDto dto = new ShopDto();
        dto.setShopId(shop.getShopId());
        dto.setName(shop.getName());
        dto.setMobile(shop.getMobile());
        dto.setLocation(shop.getLocation());
        dto.setStatus(shop.getStatus());
        return dto;
    }

    // DTO -> Entity
    private Shop dtoToEntity(ShopDto dto) {
        Shop shop = new Shop();
        shop.setShopId(dto.getShopId());
        shop.setName(dto.getName());
        shop.setMobile(dto.getMobile());
        shop.setLocation(dto.getLocation());
        shop.setStatus(dto.getStatus());
       
        return shop;
    }

    @Override
    public ShopDto createShop(ShopDto shopDto) {
        Shop shop= dtoToEntity(shopDto);
        Shop saved = shopRepository.save(shop);
        return entityToDto(saved);
    }

    @Override
    public ShopDto getShopById(Integer shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Shop not found with ID: " + shopId));
        return entityToDto(shop);
    }

    @Override
    public List<ShopDto> getAllShops() {
        return shopRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ShopDto updateShop(Integer shopId,ShopDto dto) {
      Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("shop not found with ID: " + shopId));
      
        shop.setShopId(dto.getShopId());
        shop.setName(dto.getName());
        shop.setMobile(dto.getMobile());
        shop.setLocation(dto.getLocation());
        shop.setStatus(dto.getStatus());

        Shop updated = shopRepository.save(shop);
        
        return entityToDto(updated);
    }

    @Override
    public void deleteShop(Integer shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Shop not found with ID: " + shopId));
        shopRepository.delete(shop);
    }
}

