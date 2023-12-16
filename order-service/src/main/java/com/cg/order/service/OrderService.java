package com.cg.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.commons.dto.OrderRequestDto;
import com.cg.commons.event.OrderStatus;
import com.cg.order.entity.PurchaseOrder;
import com.cg.order.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderStatusPublisher orderStatusPublisher;

	@Transactional
	public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {
		// TODO Auto-generated method stub
		PurchaseOrder entity = new PurchaseOrder();
		entity.setUserId(orderRequestDto.getUserId());
		entity.setPrice(orderRequestDto.getAmount());
		entity.setProductId(orderRequestDto.getProductId());
		entity.setOrderStatus(OrderStatus.ORDER_CREATED);
		
		PurchaseOrder purchaseOrder= orderRepository.save(entity);
		orderRequestDto.setOrderId(purchaseOrder.getId());
		
		//produce kafka event with status order_created
		orderStatusPublisher.publishOrderEvent(orderRequestDto, OrderStatus.ORDER_CREATED);
		return purchaseOrder;
	}

	public List<PurchaseOrder> getAllOrders() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}
	
	

}
