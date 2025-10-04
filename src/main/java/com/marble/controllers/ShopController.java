package com.marble.controllers;

import com.marble.dto.ShopDto;
import com.marble.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping
    public ResponseEntity<ShopDto> createShop(@RequestBody ShopDto shopDto) {
        return new ResponseEntity<>(shopService.createShop(shopDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopDto> getShopById(@PathVariable Integer id) {
        return ResponseEntity.ok(shopService.getShopById(id));
    }

    @GetMapping
    public ResponseEntity<List<ShopDto>> getAllShops() {
        return ResponseEntity.ok(shopService.getAllShops());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShopDto> updateShop(@PathVariable Integer id, @RequestBody ShopDto shopDto) {
        return ResponseEntity.ok(shopService.updateShop(id, shopDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShop(@PathVariable Integer id) {
        shopService.deleteShop(id);
        return ResponseEntity.ok("Shop deleted successfully");
    }
}
