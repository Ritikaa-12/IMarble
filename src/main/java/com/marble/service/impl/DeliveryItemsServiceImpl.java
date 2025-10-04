package com.marble.service.impl;

import com.marble.dto.DeliveryItemsDto;
import com.marble.entities.Delivery;
import com.marble.entities.DeliveryItems;
import com.marble.entities.SellsProduct;

import com.marble.repos.DeliveryItemsRepository;
import com.marble.repos.DeliveryRepository;
import com.marble.repos.SellsProductRepository;
import com.marble.service.DeliveryItemsService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryItemsServiceImpl implements DeliveryItemsService {

    private final DeliveryItemsRepository deliveryItemsRepository;
    private final DeliveryRepository deliveryRepository;
    private final SellsProductRepository sellsProductRepository; // Assuming this repository exists

    public DeliveryItemsServiceImpl(DeliveryItemsRepository deliveryItemsRepository, 
                                    DeliveryRepository deliveryRepository, 
                                    SellsProductRepository sellsProductRepository) {
        this.deliveryItemsRepository = deliveryItemsRepository;
        this.deliveryRepository = deliveryRepository;
        this.sellsProductRepository = sellsProductRepository;
    }

    @Override
    public DeliveryItemsDto addItemToDelivery(DeliveryItemsDto itemDto) {
      
        Delivery delivery = deliveryRepository.findById(itemDto.getDeliveryId())
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

      
        SellsProduct sellsProduct = sellsProductRepository.findById(itemDto.getSellsProductId())
                .orElseThrow(() -> new RuntimeException("Sells Product not found"));

       

        DeliveryItems newItem = new DeliveryItems();
        newItem.setDelivery(delivery);
        newItem.setSells_product(sellsProduct);
        newItem.setQuantity(itemDto.getQuantity());

        DeliveryItems savedItem = deliveryItemsRepository.save(newItem);
        return entityToDto(savedItem);
    }

    @Override
    public List<DeliveryItemsDto> getItemsForDelivery(Integer deliveryId) {
        List<DeliveryItems> items = deliveryItemsRepository.findByDeliveryDeliveryId(deliveryId);
        return items.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public void removeItemFromDelivery(Integer deliverItemId) {
        DeliveryItems item = deliveryItemsRepository.findById(deliverItemId)
                .orElseThrow(() -> new RuntimeException("Delivery item not found"));
        deliveryItemsRepository.delete(item);
    }

   
    private DeliveryItemsDto entityToDto(DeliveryItems item) {
        DeliveryItemsDto dto = new DeliveryItemsDto();
        dto.setDeliverItemId(item.getDeliverItemId());
        dto.setQuantity(item.getQuantity());
        dto.setDeliveryId(item.getDelivery().getDeliveryId());
        dto.setSellsProductId(item.getSells_product().getSellsProductId()); 
        
       
        if (item.getSells_product().getProduct() != null) {
            dto.setProductName(item.getSells_product().getProduct().getTitle());
        }
        
        return dto;
    }
}
