package com.cg.commons.event;

import java.util.Date;
import java.util.UUID;

import com.cg.commons.dto.PaymentRequestDto;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class PaymentEvent implements Event{
	
	private UUID eventId=UUID.randomUUID();
	private Date date =new Date();
	
	private PaymentRequestDto paymentRequestDto;
	private PaymentStatus paymentStatus;
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
	public PaymentEvent(PaymentRequestDto paymentRequestDto, PaymentStatus paymentStatus) {
		super();
		this.paymentRequestDto = paymentRequestDto;
		this.paymentStatus = paymentStatus;
	}

}
