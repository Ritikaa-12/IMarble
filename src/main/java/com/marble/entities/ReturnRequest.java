package com.marble.entities;

import com.marble.enums.ReturnRequestStatus;
import com.marble.enums.ReturnRequestType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ReturnRequest {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer returnId;
	
    @ManyToOne				
    @JoinColumn(name = "order_id", nullable = false)
    private Orderr order;

    @ManyToOne			
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;
	
    private Integer quantity;
    
    @Enumerated(EnumType.STRING)
    private ReturnRequestType type; // (missing/damage)
    
    private String description;
    
    @Enumerated(EnumType.STRING)
    private ReturnRequestStatus status; // (PENDING_APPROVAL, APPROVED, REJECTED)
}