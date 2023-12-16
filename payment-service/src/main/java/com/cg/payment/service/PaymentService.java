package com.cg.payment.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.commons.dto.OrderRequestDto;
import com.cg.commons.dto.PaymentRequestDto;
import com.cg.commons.event.OrderEvent;
import com.cg.commons.event.PaymentEvent;
import com.cg.commons.event.PaymentStatus;
import com.cg.payment.entity.UserBalance;
import com.cg.payment.entity.UserTransaction;
import com.cg.payment.repository.UserBalanceRepository;
import com.cg.payment.repository.UserTransactionRepository;

import jakarta.annotation.PostConstruct;


@Service
public class PaymentService {

	@Autowired
	private UserBalanceRepository userBalanceRepository;
	@Autowired
	private UserTransactionRepository userTransactionRepository;

	@PostConstruct
	public void initUserBalanceinDb() {
		UserBalance ub1 = new UserBalance(101, 3000);
		UserBalance ub2 = new UserBalance(102, 2000);
		UserBalance ub3 = new UserBalance(103, 4000);
		UserBalance ub4 = new UserBalance(104, 6000);

		List<UserBalance> list = Arrays.asList(ub1, ub2, ub3, ub4);
		userBalanceRepository.saveAll(list);
	}

	@Transactional
	public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
		// TODO Auto-generated method stub
		OrderRequestDto orderRequestDto = orderEvent.getOrderRequestDto();
		PaymentRequestDto paymentRequestDto = new PaymentRequestDto(orderRequestDto.getUserId(),orderRequestDto.getOrderId(),orderRequestDto.getAmount());
		return userBalanceRepository.findById(orderRequestDto.getUserId()).filter((ub)->ub.getPrice()>orderRequestDto.getAmount())
				.map((ub)->{
					ub.setPrice(ub.getPrice()-orderRequestDto.getAmount());
					userTransactionRepository.save(new UserTransaction(orderRequestDto.getOrderId(),orderRequestDto.getUserId(),orderRequestDto.getAmount()));
					return new PaymentEvent(paymentRequestDto,PaymentStatus.PAYMENT_COMPLETED);
				}).orElse(new PaymentEvent(paymentRequestDto,PaymentStatus.PAYMENT_FAILED));
						
	
	}

	@Transactional
	public void cancelOrderEvent(OrderEvent orderEvent) {
		// TODO Auto-generated method stub
		userTransactionRepository.findById(orderEvent.getOrderRequestDto().getOrderId())
			.ifPresent(ut->{
				userTransactionRepository.delete(ut);
				userTransactionRepository.findById(ut.getUserId()).ifPresent((u)->u.setAmount(u.getAmount()+ut.getAmount()));
					
			});
		
	}

}
