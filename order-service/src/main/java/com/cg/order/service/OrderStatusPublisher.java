package com.cg.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.commons.dto.OrderRequestDto;
import com.cg.commons.event.OrderEvent;
import com.cg.commons.event.OrderStatus;

import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {
	
	@Autowired
	private Sinks.Many<OrderEvent> orderSinks;
	
	public void publishOrderEvent(OrderRequestDto orderRequestDto , OrderStatus orderStatus) {
		OrderEvent orderEvent = new OrderEvent(orderRequestDto,orderStatus);
		orderSinks.tryEmitNext(orderEvent);
	}

}
