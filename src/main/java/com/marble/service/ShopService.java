package com.marble.service;

import com.marble.dto.ShopDto;
import java.util.List;

public interface ShopService {
	ShopDto createShop(ShopDto shopDto);
	ShopDto getShopById(Integer shopId);
	List<ShopDto> getAllShops();
	ShopDto updateShop(Integer shopId,ShopDto shopDto);
	void deleteShop(Integer shopId);
	
}
