package com.marble.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

// Missing Item
public class ReturnRequest {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="return_id")
	private Integer returnId;
	
	//ho sakta h customer total 5 product m se 2 abhi return kre aur 3 baad m
    @ManyToOne				// ask ++++++++++++++++
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    //ho sakta h customer total 5 "same" product m se 2 abhi return kre aur 3 baad m
    @ManyToOne			// ask+++++++++++++++++++++++++
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;
	
    private Integer quantity;
    private String type;                    //           (missing/damage)
    private String description;
    
	
}
