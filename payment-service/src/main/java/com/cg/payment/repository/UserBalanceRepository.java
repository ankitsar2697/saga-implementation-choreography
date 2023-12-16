package com.cg.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.payment.entity.UserBalance;

public interface UserBalanceRepository extends  JpaRepository<UserBalance, Integer> {

}
