package com.cg.payment.config;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cg.commons.event.OrderEvent;
import com.cg.commons.event.OrderStatus;
import com.cg.commons.event.PaymentEvent;
import com.cg.payment.service.PaymentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class PaymentConsumerConfig {
	@Autowired
	private PaymentService paymentService;
	
	//it will consume the order event,then validate the data and publish the payment event
	
	@Bean
	public Function<Flux<OrderEvent>,Flux<PaymentEvent>> paymentProcessor(){
		return orderEventFlux -> orderEventFlux.flatMap((x)->this.processPayment(x));
		
	}
	
	private Mono<PaymentEvent> processPayment(OrderEvent orderEvent){
		//get the user id
		//check balance availability
		//if balance suficient payment completed and deduct amount from db
		//if payment not sufficient then simply cancel the ordeevent and update the amount in db
		
		if(OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())) {
			return Mono.fromSupplier(()->this.paymentService.newOrderEvent(orderEvent));
		}else {
			return Mono.fromRunnable(()->this.paymentService.cancelOrderEvent(orderEvent));
		} 
	}
	
	

}
