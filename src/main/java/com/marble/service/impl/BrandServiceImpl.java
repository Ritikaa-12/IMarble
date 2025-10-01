package com.marble.service.impl;

import com.marble.dto.BrandDto;
import com.marble.entities.Brand;
import com.marble.repos.BrandRepository;
import com.marble.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    // Entity -> DTO
    private BrandDto entityToDto(Brand brand) {
        BrandDto dto = new BrandDto();
        dto.setBrandId(brand.getBrandId());
        dto.setTitle(brand.getTitle());
        dto.setImage(brand.getImage());
        dto.setStatus(brand.getStatus());
        return dto;
    }

    // DTO -> Entity
    private Brand dtoToEntity(BrandDto dto) {
        Brand brand = new Brand();
        brand.setTitle(dto.getTitle());
        brand.setImage(dto.getImage());
        brand.setStatus(dto.getStatus());
        return brand;
    }

    @Override
    public BrandDto createBrand(BrandDto brandDto) {
        Brand brand = dtoToEntity(brandDto);
        Brand saved = brandRepository.save(brand);
        return entityToDto(saved);
    }

    @Override
    public BrandDto getBrandById(Integer brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new RuntimeException("Brand not found with ID: " + brandId));
        return entityToDto(brand);
    }

    @Override
    public List<BrandDto> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BrandDto updateBrand(Integer brandId, BrandDto brandDto) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new RuntimeException("Brand not found with ID: " + brandId));

        brand.setTitle(brandDto.getTitle());
        brand.setImage(brandDto.getImage());
        brand.setStatus(brandDto.getStatus());

        Brand updated = brandRepository.save(brand);
        return entityToDto(updated);
    }

    @Override
    public void deleteBrand(Integer brandId) {
        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new RuntimeException("Brand not found with ID: " + brandId));
        brandRepository.delete(brand);
    }
}

