package com.cg.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.order.entity.PurchaseOrder;

public interface OrderRepository extends JpaRepository<PurchaseOrder, Integer>{

}
