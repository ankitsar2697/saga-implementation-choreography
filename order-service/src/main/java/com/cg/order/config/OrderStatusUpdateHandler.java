package com.cg.order.config;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.cg.commons.dto.OrderRequestDto;
import com.cg.commons.event.OrderStatus;
import com.cg.commons.event.PaymentStatus;
import com.cg.order.entity.PurchaseOrder;
import com.cg.order.repository.OrderRepository;
import com.cg.order.service.OrderStatusPublisher;

@Configuration
public class OrderStatusUpdateHandler {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderStatusPublisher publisher;
	
	public void updateOrder(int orderId,Consumer<PurchaseOrder> consumer) {
		orderRepository.findById(orderId).ifPresent((c)->this.updateOrder(c));
	}

	@Transactional
	private void updateOrder(PurchaseOrder c) {
		// TODO Auto-generated method stub
		//if paymentStatus is successful then update order status as completed otherwise failure
	boolean isPaymentComplete =	PaymentStatus.PAYMENT_COMPLETED.equals(c.getPaymentStatus());
	OrderStatus orderStatus = isPaymentComplete? OrderStatus.ORDER_COMPLETED:OrderStatus.ORDER_FAILED;
	c.setOrderStatus(orderStatus);
	if(!isPaymentComplete) {
		publisher.publishOrderEvent(convertEntityToDto(c),orderStatus);
	}
		
	}
	
	public OrderRequestDto convertEntityToDto(PurchaseOrder order) {
		OrderRequestDto dto = new OrderRequestDto();
		dto.setOrderId(order.getId());
		dto.setAmount(order.getPrice());
		dto.setProductId(order.getProductId());
		dto.setUserId(order.getUserId());
		
		return dto;
	}

}
