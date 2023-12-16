package com.cg.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.commons.dto.OrderRequestDto;
import com.cg.order.entity.PurchaseOrder;
import com.cg.order.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	@PostMapping("/create")
	PurchaseOrder createOrder(@RequestBody OrderRequestDto orderRequestDto) {
		return orderService.createOrder(orderRequestDto);
	}
	
	@GetMapping
	List<PurchaseOrder> getAllOrders(){
		return orderService.getAllOrders();
	}
	

}
