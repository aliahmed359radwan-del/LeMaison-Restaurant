package com.lemaison.leMaison.repository;

import com.lemaison.leMaison.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {}
