package com.cg.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.payment.entity.UserTransaction;

public interface UserTransactionRepository extends JpaRepository<UserTransaction, Integer> {

}
