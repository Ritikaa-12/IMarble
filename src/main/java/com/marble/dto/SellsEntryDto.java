package com.marble.dto;

import com.marble.enums.SellsStatus;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class SellsEntryDto {
    private Integer sellsEntryId;
    private String type;
    private Float billAmount;
    private LocalDate date;
    private String invoiceNo;
    private SellsStatus status;

    private Integer clientId;
    private Integer shopId;


    private List<SellsProductDto> sellsProducts;
}