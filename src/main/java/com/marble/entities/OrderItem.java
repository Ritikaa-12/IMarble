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
public class OrderItem {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="orderItem_id")
	private Integer orderItemId;
	
	//many order item belong to one order(ek order m bht sare items bula sakte h)
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    //many order item belong to on product
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
	@Column(nullable = false)
	private Integer quantity;
	
	private Float discount;
	// ask+++++++++++++++++++++++++++++++++++
	private Float tax;
	
	private Float price;
	
	private Float subTotal;
}
