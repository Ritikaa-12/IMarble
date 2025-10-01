package com.marble.service;

import com.marble.dto.BrandDto;
import java.util.List;

public interface BrandService {
    BrandDto createBrand(BrandDto brandDto);
    BrandDto getBrandById(Integer brandId);
    List<BrandDto> getAllBrands();
    BrandDto updateBrand(Integer brandId, BrandDto brandDto);
    void deleteBrand(Integer brandId);
}
