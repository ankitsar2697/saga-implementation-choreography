package com.cg.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {
	
	private Integer userId;
	private Integer orderId;
	private Integer amount;

}
