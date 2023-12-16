package com.cg.commons.event;

import java.util.Date;
import java.util.UUID;

import com.cg.commons.dto.OrderRequestDto;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class OrderEvent implements Event{
	
	private UUID eventId=UUID.randomUUID();
	private Date date =new Date();
	
	private OrderRequestDto orderRequestDto;
	private OrderStatus orderStatus;
	@Override
	public Date getEventDate() {
		// TODO Auto-generated method stub
		return date;
	}
	@Override
	public UUID getEventId() {
		// TODO Auto-generated method stub
		return eventId;
	}
	public OrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
		super();
		this.orderRequestDto = orderRequestDto;
		this.orderStatus = orderStatus;
	}
	
	
	

}
