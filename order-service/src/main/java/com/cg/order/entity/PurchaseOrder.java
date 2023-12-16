package com.cg.order.entity;

import com.cg.commons.event.OrderStatus;
import com.cg.commons.event.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "PURCHASE_ORDERS_TBL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {
	
	@Id
	private Integer id;
	private Integer userId;
	private Integer productId;
	private Integer price;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

}
